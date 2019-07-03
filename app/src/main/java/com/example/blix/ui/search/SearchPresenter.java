package com.example.blix.ui.search;

import android.util.Log;

import com.example.blix.api.ApiClient;
import com.example.blix.model.ResponseDiscover;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPresenter implements SearchListener.Presenter {
    private static String TAG = SearchPresenter.class.getSimpleName();
    ResponseDiscover itemsMedia;
    private ApiClient apiClient;
    private SearchListener.View view;

    public SearchPresenter(ResponseDiscover itemsMedia, ApiClient apiClient) {
        this.itemsMedia = itemsMedia;
        this.apiClient = apiClient;
    }

    @Override
    public void setView(SearchListener.View view) {
        this.view = view;
        itemsMedia = new ResponseDiscover();
    }

    @Override
    public void getSearchRequest(String apiKey, String lang, String query, int pag) {
        Call<ResponseDiscover> call = apiClient.getSearchMovie(apiKey, lang, query, pag);
        call.enqueue(new Callback<ResponseDiscover>() {
            @Override
            public void onResponse(Call<ResponseDiscover> call, Response<ResponseDiscover> response) {
                if (response.isSuccessful()) {
                    itemsMedia = response.body();
                    //   view.showSearchList(itemsMedia);
                }
                view.showSearchList(itemsMedia);

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
                if (response.isSuccessful()) {
                    itemsMedia = response.body();

                }
                view.showPopularList(itemsMedia);
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
                if (response.isSuccessful()) {
                    itemsMedia = response.body();

                }
                view.showTopRatedList(itemsMedia);
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
                if (response.isSuccessful()) {
                    itemsMedia = response.body();

                }
                view.showUpcomingList(itemsMedia);
            }

            @Override
            public void onFailure(Call<ResponseDiscover> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }
}
