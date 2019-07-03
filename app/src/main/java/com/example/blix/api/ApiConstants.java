package com.example.blix.api;

public class ApiConstants {
    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String HEADER_CACHE_CONTROL = "Cache-Control";
    public static final String HEADER_PRAGMA = "Pragma";

    public static final String BASE_IMG = "https://image.tmdb.org/t/p/w500";

    public static final long CACHE_SIZE = 5 * 1024 * 1024; // 5 MB

    //Params Constants
    static final String API_KEY = "api_key";
    static final String LANG = "language";
    static final String QUERY_P = "query";

    //EndPoints
    static final String DISCOVER_MOVIES = "discover/movie";

    static final String POPULAR_MOVIES = "movie/popular";

    static final String TOP_MOVIES = "movie/top_rated";

    static final String UPCOMING_MOVIES = "movie/upcoming";

    static final String PARAM_PAGE = "page";

    static final String VIDEO_MOVIE = "movie/{movie_id}/videos";

    static final String SEARCH_MOVIE = "search/movie";

}
