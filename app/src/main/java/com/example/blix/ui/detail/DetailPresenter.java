package com.example.blix.ui.detail;

import android.util.Log;

import com.example.blix.api.ApiClient;
import com.example.blix.model.ResponseVideo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPresenter implements DetailListener.Presenter {
    private static String TAG = DetailPresenter.class.getSimpleName();
    ResponseVideo itemsVideos;
    private ApiClient apiClient;
    private DetailListener.View view;

    public DetailPresenter(ResponseVideo itemsMedia, ApiClient apiClient) {
        this.itemsVideos = itemsMedia;
        this.apiClient = apiClient;
    }

    @Override
    public void setView(DetailListener.View view) {
        this.view = view;
        itemsVideos = new ResponseVideo();
    }

    @Override
    public void getVideosRequest(String apiKey, String lang, int movieId) {
        Call<ResponseVideo> call = apiClient.getVideoMovie(movieId, apiKey, lang);
        call.enqueue(new Callback<ResponseVideo>() {
            @Override
            public void onResponse(Call<ResponseVideo> call, Response<ResponseVideo> response) {
                if (response.isSuccessful()) {
                    itemsVideos = response.body();
                    view.showVideosList(itemsVideos);
                }
            }

            @Override
            public void onFailure(Call<ResponseVideo> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }
}
