package com.example.blix.ui.detail;

import com.example.blix.model.ResponseVideo;

public interface DetailListener {
    interface View {
        void showVideosList(ResponseVideo itemsMedia);

    }

    interface Presenter {
        void setView(DetailListener.View view);

        void getVideosRequest(String apiKey, String lang, int movieId);
    }
}
