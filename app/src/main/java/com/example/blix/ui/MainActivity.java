package com.example.blix.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.blix.R;
import com.example.blix.ui.discover.DiscoverFragment;
import com.example.blix.ui.information.InformationActivity;
import com.example.blix.ui.search.SearchFragment;
import com.example.blix.util.Constants;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    final Fragment frDiscover = new DiscoverFragment();
    final Fragment frSearch = new SearchFragment();
    final FragmentManager fm = getSupportFragmentManager();
    private Fragment active = frDiscover;

    MenuItem searchItem;
    BottomNavigationView navView;

    private static long back_pressed;
    private static final long SECONDS_LAPSE = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI();
    }

    private void setupUI() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tbToolbarDiscover);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fm.beginTransaction().add(R.id.layout_content_main, frSearch, "2").hide(frSearch).commit();
        fm.beginTransaction().add(R.id.layout_content_main, frDiscover, "1").commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_action_search, menu);

        searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchFragment searchFragment = (SearchFragment) fm.getFragments().get(0);
                searchFragment.setQuery(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
/*
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        ComponentName componentName = new ComponentName(getApplicationContext(), MainActivity.class);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName));
  */
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_info:
                startActivity(new Intent(this, InformationActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    showDiscover();
                    return true;
                case R.id.navigation_search:
                    showSearch(Constants.CAT_EMPTY);
                    return true;
            }
            return false;
        }
    };

    public void setSerchTitleOnClick(int id) {
        String category = Constants.CAT_EMPTY;
        switch (id) {
            case R.id.lt_title_popular:
                category = Constants.CAT_POPULAR;
                break;
            case R.id.lt_title_top:
                category = Constants.CAT_TOP;
                break;
            case R.id.lt_title_upcoming:
                category = Constants.CAT_UPCOMING;
                break;
        }

        showSearch(category);
    }

    private void showDiscover() {
        searchItem.setVisible(false);
        fm.beginTransaction().hide(active).show(frDiscover).commit();
        active = frDiscover;
    }

    private void showSearch(String category) {
        navView.getMenu().findItem(R.id.navigation_search).setChecked(true);
        searchItem.setVisible(true);
        SearchFragment searchFragment = (SearchFragment) fm.getFragments().get(0);
        searchFragment.setCategory(category);
        fm.beginTransaction().hide(active).show(frSearch).commit();
        active = frSearch;
    }

    @Override
    public void onBackPressed() {
        if (back_pressed + SECONDS_LAPSE > System.currentTimeMillis()) super.onBackPressed();
        else
            Toast.makeText(getBaseContext(), getText(R.string.msj_exit_app), Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();
    }
}
