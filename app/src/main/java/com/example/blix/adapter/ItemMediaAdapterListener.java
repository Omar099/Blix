package com.example.blix.adapter;

import com.example.blix.model.ItemMedia;

public interface ItemMediaAdapterListener {

    interface View{
        void setItemOnClick(ItemMedia item);

        void setDiscoverRequest();

        void setPopularRequest();

        void setTopRatedRequest();

        void setUpcomingRequest();
    }
}
