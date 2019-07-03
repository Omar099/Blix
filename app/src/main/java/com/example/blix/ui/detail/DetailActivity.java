package com.example.blix.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ablanco.zoomy.Zoomy;
import com.bumptech.glide.Glide;
import com.example.blix.BuildConfig;
import com.example.blix.R;
import com.example.blix.app.BaseApplication;
import com.example.blix.model.ItemMedia;
import com.example.blix.model.ItemVideo;
import com.example.blix.model.ResponseVideo;
import com.example.blix.util.Constants;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.List;

import javax.inject.Inject;

import static com.example.blix.api.ApiConstants.BASE_IMG;

public class DetailActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener, DetailListener.View {
    private static final String TAG = DetailActivity.class.getSimpleName();
    Bundle parametros;
    ItemMedia itemMedia;
    ItemVideo itemVideo;
    YouTubePlayerView ytpThriler;
    ImageView ivImageDefault;

    @Inject
    DetailListener.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setupDagger();
        setupUI();
    }

    private void setupUI() {
        this.parametros = getIntent().getExtras();
        if (this.parametros != null) {
            this.itemMedia = this.parametros.getParcelable(Constants.PARAM_MOVIE);
            Log.d(TAG, itemMedia.toString());
        }

        ivImageDefault = findViewById(R.id.iv_image_default);
        Zoomy.Builder builder = new Zoomy.Builder(this).target(ivImageDefault);
        builder.register();

        TextView tvTitle = findViewById(R.id.tv_title);
        tvTitle.setText(itemMedia.getTitle());

        TextView tvDate = findViewById(R.id.tv_date);
        tvDate.setText(String.format("%s%s", getString(R.string.constant_date_name), itemMedia.getReleaseDate()));

        TextView tvDescription = findViewById(R.id.tv_description);
        tvDescription.setText(itemMedia.getOverview());

        Button btBackNav = findViewById(R.id.bt_back_nav);
        btBackNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        presenter.setView(this);
        presenter.getVideosRequest(BuildConfig.TMDB_ApiKey, getString(R.string.lang), Integer.parseInt(itemMedia.getId()));

        ytpThriler = findViewById(R.id.ytpvThriller);

    }

    private void setupDagger() {
        ((BaseApplication) getApplication()).plusPresenterSubComponent().inject(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (!b) {
            youTubePlayer.cueVideo(itemVideo.getKey());
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(this, 1).show();
        } else {
            String error = "Error al inicializar Youtube" + youTubeInitializationResult.toString();
            Toast.makeText(getApplication(), error, Toast.LENGTH_LONG).show();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 1) {
            getYoutubePlayerProvider().initialize(BuildConfig.YT_ApiKey, this);
        }
    }

    protected YouTubePlayer.Provider getYoutubePlayerProvider() {
        return ytpThriler;
    }

    @Override
    public void showVideosList(ResponseVideo itemsVideos) {
        List<ItemVideo> item = itemsVideos.getResults();
        Log.d(TAG, item.toString());

        if (item.size() > 0) {
            itemVideo = item.get(0);
            ytpThriler.initialize(BuildConfig.YT_ApiKey, this);

        } else {
            String urlPoster = BASE_IMG + itemMedia.getPosterPath();

            ytpThriler.setVisibility(View.GONE);
            ivImageDefault.setVisibility(View.VISIBLE);

            Glide.with(this).load(urlPoster).into(ivImageDefault);

        }
    }
}
