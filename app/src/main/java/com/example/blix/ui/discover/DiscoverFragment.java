package com.example.blix.ui.discover;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;

import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blix.BuildConfig;
import com.example.blix.R;
import com.example.blix.adapter.ItemMediaAdapter;
import com.example.blix.adapter.ItemMediaAdapterListener;
import com.example.blix.app.BaseApplication;
import com.example.blix.model.ItemMedia;
import com.example.blix.model.ResponseDiscover;
import com.example.blix.ui.MainActivity;
import com.example.blix.ui.detail.DetailActivity;
import com.example.blix.util.CarrouselOnScrollListener;
import com.example.blix.util.Constants;
import com.example.blix.util.DiscoverOnScrollListener;
import com.example.blix.util.SpeedyGridLinearLayoutManager;
import com.example.blix.util.SpeedyLinearLayoutManager;

import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Named;

public class DiscoverFragment extends Fragment implements View.OnClickListener, DiscoverListener.View, ItemMediaAdapterListener.View {
    public static final String TAG = DiscoverFragment.class.getSimpleName();

    private View v;

    private int pageDiscover = 1;
    private int pagePopular = 1;
    private int pageTopRated = 1;
    private int pageUpcoming = 1;

    @Inject
    DiscoverListener.Presenter presenter;

    @Inject
    @Named("discoverAdapter")
    ItemMediaAdapter discoverAdapter;

    @Inject
    @Named("popularAdapter")
    ItemMediaAdapter popularAdapter;

    @Inject
    @Named("topRatedAdapter")
    ItemMediaAdapter topRatedAdapter;

    @Inject
    @Named("upcomingAdapter")
    ItemMediaAdapter upcomingAdapter;

    public DiscoverFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_discover, container, false);
        setupDagger();
        setupUI();
        return v;
    }

    private void setupDagger() {
        ((BaseApplication) getActivity().getApplication()).plusPresenterSubComponent().inject(this);
    }

    private void setupUI() {
        Display display = getActivity().getWindowManager().getDefaultDisplay();//SizeScreen, obtenemos las medidas del dispositivo para asignar la altura
        Point sizeScreen = new Point();
        display.getSize(sizeScreen);

        discoverAdapter.setView(this, sizeScreen.x, 3);//Funge como presenter ya que ahí mismo se encuentra su funcionamiento
        GridLayoutManager layoutManager = new SpeedyGridLinearLayoutManager(getActivity(), 3);//new GridLayoutManager(getContext(), 3);//Setup de layout
        DiscoverOnScrollListener scrollDiscover = new DiscoverOnScrollListener(this, layoutManager);
        RecyclerView rvDiscoverMovies = v.findViewById(R.id.rv_discover_movies);
        rvDiscoverMovies.setAdapter(discoverAdapter);
        rvDiscoverMovies.setLayoutManager(layoutManager);
        rvDiscoverMovies.addOnScrollListener(scrollDiscover);
        rvDiscoverMovies.getLayoutParams().height = sizeScreen.y * 9 / 11;


        popularAdapter.setView(this, sizeScreen.x);
        LinearLayoutManager layoutPopular = new SpeedyLinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        CarrouselOnScrollListener scrollPopular = new CarrouselOnScrollListener(this, layoutPopular, "popular");
        RecyclerView rvPopular = v.findViewById(R.id.rv_popular);
        rvPopular.setAdapter(popularAdapter);
        rvPopular.setLayoutManager(layoutPopular);
        rvPopular.addOnScrollListener(scrollPopular);


        topRatedAdapter.setView(this, sizeScreen.x);
        LinearLayoutManager layoutTop = new SpeedyLinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        CarrouselOnScrollListener scrollTop = new CarrouselOnScrollListener(this, layoutTop, "top");
        RecyclerView rvTop = v.findViewById(R.id.rv_top_rated);
        rvTop.setAdapter(topRatedAdapter);
        rvTop.setLayoutManager(layoutTop);
        rvTop.addOnScrollListener(scrollTop);


        upcomingAdapter.setView(this, sizeScreen.x);
        LinearLayoutManager layoutUpcoming = new SpeedyLinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        CarrouselOnScrollListener scrolllUpcoming = new CarrouselOnScrollListener(this, layoutUpcoming, "upcoming");
        RecyclerView rvUpcoming = v.findViewById(R.id.rv_upcoming);
        rvUpcoming.setAdapter(upcomingAdapter);
        rvUpcoming.setLayoutManager(layoutUpcoming);
        rvUpcoming.addOnScrollListener(scrolllUpcoming);

        presenter.setView(this);
        presenter.getDiscoverRequest(BuildConfig.TMDB_ApiKey, getString(R.string.lang), pageDiscover);
        presenter.getPopularRequest(BuildConfig.TMDB_ApiKey, getString(R.string.lang), pagePopular);
        presenter.getTopRatedRequest(BuildConfig.TMDB_ApiKey, getString(R.string.lang), pageTopRated);
        presenter.getUpcomingRequest(BuildConfig.TMDB_ApiKey, getString(R.string.lang), pageUpcoming);

        LinearLayout ltTitlePopular = v.findViewById(R.id.lt_title_popular);
        LinearLayout ltTitleTop = v.findViewById(R.id.lt_title_top);
        LinearLayout ltTitleUpcoming = v.findViewById(R.id.lt_title_upcoming);

        ltTitlePopular.setOnClickListener(this);
        ltTitleTop.setOnClickListener(this);
        ltTitleUpcoming.setOnClickListener(this);

    }

    @Override
    public void showDiscoverList(ResponseDiscover itemsMedia) {
        if (itemsMedia != null)
            discoverAdapter.setData(itemsMedia.getResults());
    }

    @Override
    public void showPopularList(ResponseDiscover itemsMedia) {
        if (itemsMedia != null)
            popularAdapter.setData(itemsMedia.getResults());
    }

    @Override
    public void showTopRatedList(ResponseDiscover itemsMedia) {
        if (itemsMedia != null)
            topRatedAdapter.setData(itemsMedia.getResults());
    }

    @Override
    public void showUpcomingList(ResponseDiscover itemsMedia) {
        if (itemsMedia != null)
            upcomingAdapter.setData(itemsMedia.getResults());
    }

    @Override
    public void setDiscoverRequest() {
        presenter.getDiscoverRequest(BuildConfig.TMDB_ApiKey, getString(R.string.lang), pageDiscover);
        pageDiscover++;
        if (pageDiscover >= 40)
            pageDiscover = 1;

    }

    @Override
    public void setPopularRequest() {
        presenter.getPopularRequest(BuildConfig.TMDB_ApiKey, getString(R.string.lang), pagePopular);
        pagePopular++;
        if (pagePopular >= 40)
            pagePopular = 1;

    }

    @Override
    public void setTopRatedRequest() {
        presenter.getTopRatedRequest(BuildConfig.TMDB_ApiKey, getString(R.string.lang), pageTopRated);
        pageTopRated++;
        if (pageTopRated >= 40)
            pageTopRated = 1;
    }

    @Override
    public void setUpcomingRequest() {
        presenter.getUpcomingRequest(BuildConfig.TMDB_ApiKey, getString(R.string.lang), pageUpcoming);
        pageUpcoming++;
        if (pageUpcoming >= 40)
            pageUpcoming = 1;
    }

    @Override
    public void setItemOnClick(ItemMedia item) {
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra(Constants.PARAM_MOVIE, item);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        ((MainActivity) Objects.requireNonNull(getActivity())).setSerchTitleOnClick(v.getId());
    }
}
