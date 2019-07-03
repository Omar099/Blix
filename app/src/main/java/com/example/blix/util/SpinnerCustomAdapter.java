package com.example.blix.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.blix.R;

import java.util.ArrayList;

public class SpinnerCustomAdapter extends ArrayAdapter<SpinnerCustomItem> {


    public SpinnerCustomAdapter(@NonNull Context context, ArrayList<SpinnerCustomItem> itemList) {
        super(context, 0, itemList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.layout_item_spinner, parent, false
            );
        }

        TextView tvTexto = convertView.findViewById(R.id.tv_item_spinner);

        SpinnerCustomItem item = getItem(position);

        if (item != null) {
            tvTexto.setText(item.getText());
        }

        return convertView;
    }
}
