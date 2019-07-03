package com.example.blix.adapter;

import com.example.blix.model.ItemMedia;

public interface SearchMediaAdapterListener {
    interface View{
        void setItemOnClick(ItemMedia item);

        void chooseRequest();
    }
}
