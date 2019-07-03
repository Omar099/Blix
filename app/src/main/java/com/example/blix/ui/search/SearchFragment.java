package com.example.blix.ui.search;


import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blix.BuildConfig;
import com.example.blix.R;
import com.example.blix.adapter.SearchMediaAdapter;
import com.example.blix.adapter.SearchMediaAdapterListener;
import com.example.blix.app.BaseApplication;
import com.example.blix.model.ItemMedia;
import com.example.blix.model.ResponseDiscover;
import com.example.blix.util.Constants;
import com.example.blix.ui.detail.DetailActivity;
import com.example.blix.util.SearchOnScrollListener;
import com.example.blix.util.SpeedyGridLinearLayoutManager;
import com.example.blix.util.SpinnerCustomAdapter;
import com.example.blix.util.SpinnerCustomItem;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

public class SearchFragment extends Fragment implements SearchListener.View, SearchMediaAdapterListener.View {
    private static final String TAG = SearchFragment.class.getSimpleName();
    private View v;

    private int pageSearch = 1;
    private int pagePopular = 1;
    private int pageTopRated = 1;
    private int pageUpcoming = 1;

    Spinner spinner;
    private ArrayList<SpinnerCustomItem> items;
    private SpinnerCustomAdapter spinnerAdapter;

    private String category = Constants.CAT_EMPTY;
    private String query = "";

    @Inject
    SearchListener.Presenter presenter;

    @Inject
    @Named("searchAdapter")
    SearchMediaAdapter searchAdapter;

    public SearchFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_search, container, false);
        setupDagger();
        setupUI();
        return v;
    }

    private void setupUI() {
        items = new ArrayList<>();
        items.add(new SpinnerCustomItem(Constants.CAT_EMPTY));
        items.add(new SpinnerCustomItem(Constants.CAT_POPULAR));
        items.add(new SpinnerCustomItem(Constants.CAT_TOP));
        items.add(new SpinnerCustomItem(Constants.CAT_UPCOMING));

        spinner = v.findViewById(R.id.sp_filter_category);
        spinnerAdapter = new SpinnerCustomAdapter(getContext(), items);

        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerCustomItem itemClick = (SpinnerCustomItem) parent.getItemAtPosition(position);
                setCategory(itemClick.getText());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Display display = getActivity().getWindowManager().getDefaultDisplay();//SizeScreen, obtenemos las medidas del dispositivo para asignar la altura
        Point sizeScreen = new Point();
        display.getSize(sizeScreen);

        searchAdapter.setView(this, sizeScreen.x, 3);//Funge como presenter ya que ahí mismo se encuentra su funcionamiento
        GridLayoutManager layoutManager = new SpeedyGridLinearLayoutManager(getActivity(), 3);//new GridLayoutManager(getContext(), 3);//Setup de layout
        SearchOnScrollListener scrollDiscover = new SearchOnScrollListener(this, layoutManager);
        RecyclerView rvSearchMovies = v.findViewById(R.id.rv_search_movies);
        rvSearchMovies.setAdapter(searchAdapter);
        rvSearchMovies.setLayoutManager(layoutManager);
        rvSearchMovies.addOnScrollListener(scrollDiscover);
        rvSearchMovies.getLayoutParams().height = sizeScreen.y * 9 / 10;

        presenter.setView(this);

    }

    private void setupDagger() {
        ((BaseApplication) getActivity().getApplication()).plusPresenterSubComponent().inject(this);
    }

    public void setCategory(String category) {
        if (!this.category.equals(category)) {
            searchAdapter.clearList();
            clearPages();
        }

        this.category = category;
        int countItem = 0;
        for (SpinnerCustomItem myItem : items) {
            if (myItem.getText().equals(category)) {
                spinner.setSelection(countItem);
                break;
            }
            countItem++;
        }

        chooseRequest();
    }

    public void setQuery(String query) {
        if (!this.query.equals(query)) {
            searchAdapter.clearList();
            clearPages();
        }

        this.query = query;

        chooseRequest();

    }

    private void clearPages() {
        pageSearch = 1;
        pagePopular = 1;
        pageTopRated = 1;
        pageUpcoming = 1;
    }

    @Override
    public void showSearchList(ResponseDiscover itemsMedia) {
        if (!query.equals("")) {
            if (itemsMedia != null) {
                if (itemsMedia.getResults().size() > 0) {
                    searchAdapter.setData(itemsMedia.getResults());
                } else {
                    pageSearch = 1;
                }
            }
        } else {
            pageSearch = 1;
        }
    }

    @Override
    public void showPopularList(ResponseDiscover itemsMedia) {
        if (itemsMedia != null)
            searchAdapter.setData(itemsMedia.getResults());
    }

    @Override
    public void showTopRatedList(ResponseDiscover itemsMedia) {
        if (itemsMedia != null)
            searchAdapter.setData(itemsMedia.getResults());
    }

    @Override
    public void showUpcomingList(ResponseDiscover itemsMedia) {
        if (itemsMedia != null)
            searchAdapter.setData(itemsMedia.getResults());
    }

    @Override
    public void setItemOnClick(ItemMedia item) {
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra(Constants.PARAM_MOVIE, item);
        startActivity(intent);
    }

    @Override
    public void chooseRequest() {
        switch (category) {
            case Constants.CAT_EMPTY:
                setSearchRequest();
                break;
            case Constants.CAT_POPULAR:
                setPopularRequest();
                break;
            case Constants.CAT_TOP:
                setTopRatedRequest();
                break;
            case Constants.CAT_UPCOMING:
                setUpcomingRequest();
                break;
        }

        searchAdapter.setDataFilter(query);
    }

    private void setSearchRequest() {
        presenter.getSearchRequest(BuildConfig.TMDB_ApiKey, getString(R.string.lang), query, pageSearch);
        pageSearch++;
        if (pageSearch >= 40)
            pageSearch = 1;

    }

    private void setPopularRequest() {
        presenter.getPopularRequest(BuildConfig.TMDB_ApiKey, getString(R.string.lang), pagePopular);
        pagePopular++;
        if (pagePopular >= 40)
            pagePopular = 1;
    }

    private void setTopRatedRequest() {
        presenter.getTopRatedRequest(BuildConfig.TMDB_ApiKey, getString(R.string.lang), pageTopRated);
        pageTopRated++;
        if (pageTopRated >= 40)
            pageTopRated = 1;
    }

    private void setUpcomingRequest() {
        presenter.getUpcomingRequest(BuildConfig.TMDB_ApiKey, getString(R.string.lang), pageUpcoming);
        pageUpcoming++;
        if (pageUpcoming >= 40)
            pageUpcoming = 1;

    }
}
