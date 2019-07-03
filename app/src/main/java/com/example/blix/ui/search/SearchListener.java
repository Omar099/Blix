package com.example.blix.ui.search;

import com.example.blix.model.ResponseDiscover;

public interface SearchListener {
    interface View {
        void showSearchList(ResponseDiscover itemsMedia);

        void showPopularList(ResponseDiscover itemsMedia);

        void showTopRatedList(ResponseDiscover itemsMedia);

        void showUpcomingList(ResponseDiscover itemsMedia);
    }

    interface Presenter {
        void setView(SearchListener.View view);

        void getSearchRequest(String apiKey, String lang, String query, int pag);

        void getPopularRequest(String apiKey, String lang, int pag);

        void getTopRatedRequest(String apiKey, String lang, int pag);

        void getUpcomingRequest(String apiKey, String lang, int pag);
    }
}
