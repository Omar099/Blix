package com.example.blix.api;

import com.example.blix.model.ResponseDiscover;
import com.example.blix.model.ResponseVideo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.example.blix.api.ApiConstants.API_KEY;
import static com.example.blix.api.ApiConstants.DISCOVER_MOVIES;
import static com.example.blix.api.ApiConstants.LANG;
import static com.example.blix.api.ApiConstants.PARAM_PAGE;
import static com.example.blix.api.ApiConstants.POPULAR_MOVIES;
import static com.example.blix.api.ApiConstants.QUERY_P;
import static com.example.blix.api.ApiConstants.SEARCH_MOVIE;
import static com.example.blix.api.ApiConstants.TOP_MOVIES;
import static com.example.blix.api.ApiConstants.UPCOMING_MOVIES;
import static com.example.blix.api.ApiConstants.VIDEO_MOVIE;

public interface ApiClient {
    @GET(DISCOVER_MOVIES)
    Call<ResponseDiscover> getDiscoverMovies(
            @Query(API_KEY) String apiKey,
            @Query(LANG) String lang,
            @Query(PARAM_PAGE) int page
    );

    @GET(POPULAR_MOVIES)
    Call<ResponseDiscover> getPopularMovies(
            @Query(API_KEY) String apiKey,
            @Query(LANG) String lang,
            @Query(PARAM_PAGE) int page
    );

    @GET(TOP_MOVIES)
    Call<ResponseDiscover> getTopMovies(
            @Query(API_KEY) String apiKey,
            @Query(LANG) String lang,
            @Query(PARAM_PAGE) int page
    );

    @GET(UPCOMING_MOVIES)
    Call<ResponseDiscover> getUpcomingMovies(
            @Query(API_KEY) String apiKey,
            @Query(LANG) String lang,
            @Query(PARAM_PAGE) int page
    );

    @GET(VIDEO_MOVIE)
    Call<ResponseVideo> getVideoMovie(
            @Path("movie_id") int movieId,
            @Query(API_KEY) String apiKey,
            @Query(LANG) String lang
    );

    @GET(SEARCH_MOVIE)
    Call<ResponseDiscover> getSearchMovie(
            @Query(API_KEY) String apiKey,
            @Query(LANG) String lang,
            @Query(QUERY_P) String query,
            @Query(PARAM_PAGE) int page
    );
}