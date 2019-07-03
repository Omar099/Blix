package com.example.blix.ui.discover;

import com.example.blix.model.ResponseDiscover;

public interface DiscoverListener {
    interface View {
        void showDiscoverList(ResponseDiscover itemsMedia);

        void showPopularList(ResponseDiscover itemsMedia);

        void showTopRatedList(ResponseDiscover itemsMedia);

        void showUpcomingList(ResponseDiscover itemsMedia);
    }

    interface Presenter {
        void setView(DiscoverListener.View view);

        void getDiscoverRequest(String apiKey, String lang, int pag);

        void getPopularRequest(String apiKey, String lang, int pag);

        void getTopRatedRequest(String apiKey, String lang, int pag);

        void getUpcomingRequest(String apiKey, String lang, int pag);
    }
}
