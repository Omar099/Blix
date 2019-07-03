package com.example.blix.di.module;

import android.util.Log;


import com.example.blix.api.ApiClient;
import com.example.blix.app.BaseApplication;
import com.example.blix.di.scope.ApplicationScope;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.blix.api.ApiConstants.BASE_URL;
import static com.example.blix.api.ApiConstants.CACHE_SIZE;
import static com.example.blix.api.ApiConstants.HEADER_CACHE_CONTROL;
import static com.example.blix.api.ApiConstants.HEADER_PRAGMA;


@Module
public class RetrofitModule {
    private static String TAG = RetrofitModule.class.getSimpleName();

    @Provides
    @ApplicationScope
    GsonConverterFactory providesGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @ApplicationScope
    Cache cache() {
        return new Cache(new File(BaseApplication.getInstance().getCacheDir(), "someIdentifier"), CACHE_SIZE);
    }

    @Provides
    @ApplicationScope
    HttpLoggingInterceptor provideHttpLoginInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Named("online")
    @Provides
    @ApplicationScope
    Interceptor networkInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());

                CacheControl cacheControl = new CacheControl.Builder()
                        .maxAge(5, TimeUnit.SECONDS)
                        .build();

                return response.newBuilder()
                        .removeHeader(HEADER_PRAGMA)
                        .removeHeader(HEADER_CACHE_CONTROL)
                        .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                        .build();
            }
        };
    }

    @Named("offline")
    @Provides
    @ApplicationScope
    Interceptor offlineInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Log.d(TAG, "offline interceptor: called.");
                Request request = chain.request();

                if (!BaseApplication.getConnectionToNetwork()) {
                    CacheControl cacheControl = new CacheControl.Builder()
                            .maxStale(7, TimeUnit.DAYS)
                            .build();

                    request = request.newBuilder()
                            .removeHeader(HEADER_PRAGMA)
                            .removeHeader(HEADER_CACHE_CONTROL)
                            .cacheControl(cacheControl)
                            .build();
                }

                return chain.proceed(request);
            }
        };
    }

    @Provides
    @ApplicationScope
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor, @Named("online") Interceptor onlineInterceptor, @Named("offline") Interceptor offlineInterceptor) {
        return new OkHttpClient.Builder()
                .cache(cache())
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(onlineInterceptor)
                .addInterceptor(offlineInterceptor)
                .build();
    }

    @Provides
    @ApplicationScope
    Retrofit provideRetrofit(OkHttpClient client, GsonConverterFactory converterFactory) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(converterFactory)
                .client(client)
                .build();
    }

    @Provides
    @ApplicationScope
    ApiClient provideApiCliente(Retrofit retrofit) {
        return retrofit.create(ApiClient.class);
    }
}
