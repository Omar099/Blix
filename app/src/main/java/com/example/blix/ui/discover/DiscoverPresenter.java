package com.example.blix.ui.discover;

import android.util.Log;

import com.example.blix.api.ApiClient;
import com.example.blix.model.ResponseDiscover;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiscoverPresenter implements DiscoverListener.Presenter{
    private static String TAG = DiscoverPresenter.class.getSimpleName();
    ResponseDiscover itemsMedia;
    private ApiClient apiClient;
    private DiscoverListener.View view;

    public DiscoverPresenter(ResponseDiscover itemsMedia, ApiClient apiClient) {
        this.itemsMedia = itemsMedia;
        this.apiClient = apiClient;
    }

    @Override
    public void getDiscoverRequest(String apiKey, String lang, int pag) {
        Call<ResponseDiscover> call = apiClient.getDiscoverMovies(apiKey, lang, pag);
        call.enqueue(new Callback<ResponseDiscover>() {
            @Override
            public void onResponse(Call<ResponseDiscover> call, Response<ResponseDiscover> response) {
                Log.e(TAG, "log: -----------------------------");
                Log.d(TAG, "onResponse: " + response.body());

                if (response.isSuccessful()){
                    itemsMedia = response.body();
                    view.showDiscoverList(itemsMedia);
                }
            }

            @Override
            public void onFailure(Call<ResponseDiscover> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    @Override
    public void getPopularRequest(String apiKey, String lang, int pag) {
        Call<ResponseDiscover> call = apiClient.getPopularMovies(apiKey, lang, pag);
        call.enqueue(new Callback<ResponseDiscover>() {
            @Override
            public void onResponse(Call<ResponseDiscover> call, Response<ResponseDiscover> response) {
                if (response.isSuccessful()){
                    itemsMedia = response.body();
                    view.showPopularList(itemsMedia);
                }
            }

            @Override
            public void onFailure(Call<ResponseDiscover> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    @Override
    public void getTopRatedRequest(String apiKey, String lang, int pag) {
        Call<ResponseDiscover> call = apiClient.getTopMovies(apiKey, lang, pag);
        call.enqueue(new Callback<ResponseDiscover>() {
            @Override
            public void onResponse(Call<ResponseDiscover> call, Response<ResponseDiscover> response) {
                if (response.isSuccessful()){
                    itemsMedia = response.body();
                    view.showTopRatedList(itemsMedia);
                }
            }

            @Override
            public void onFailure(Call<ResponseDiscover> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    @Override
    public void getUpcomingRequest(String apiKey, String lang, int pag) {
        Call<ResponseDiscover> call = apiClient.getUpcomingMovies(apiKey, lang, pag);
        call.enqueue(new Callback<ResponseDiscover>() {
            @Override
            public void onResponse(Call<ResponseDiscover> call, Response<ResponseDiscover> response) {
                if (response.isSuccessful()){
                    itemsMedia = response.body();
                    view.showUpcomingList(itemsMedia);
                }
            }

            @Override
            public void onFailure(Call<ResponseDiscover> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    @Override
    public void setView(DiscoverListener.View view) {
        this.view = view;
        itemsMedia = new ResponseDiscover();
    }
}
