package com.example.blix.ui.information;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.blix.R;

public class InformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tbToolbarDiscover);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ImageView ivLogo = findViewById(R.id.iv_logo);
        ivLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToLink("https://www.themoviedb.org/");
            }
        });

        TextView tvLink = findViewById(R.id.tv_link);
        tvLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToLink("https://github.com/Omar099/Blix/blob/master/LICENSE");
            }
        });
    }

    private void sendToLink(String url){
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }
}
