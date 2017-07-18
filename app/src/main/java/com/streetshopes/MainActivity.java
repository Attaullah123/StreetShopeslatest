package com.streetshopes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import Fragments.ProfileFragment;
import Fragments.SearchKeywordFragment;
import Fragments.SellerReviewsFragment;
import Fragments.ShoppingCartFragment;
import Fragments.StoreListFragment;
import Fragments.StoreProductReviewsFragment;
import Fragments.StoreCurrencies;
import utils.AppConstant;
import utils.UtilitySharedPreferences;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public TextView mTitle;
    public static FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.left_panel);

        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(Gravity.LEFT);

            }
        });
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        navigateToReplace(new StoreListFragment(), null, false);
        //navigateToReplace(new ProfileFragment(), null, false);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_currencies)
//        {
//            navigateToReplace(new StoreCurrencies(), null, true);
//            return true;
//        }

        if (id == R.id.action_cart)
        {
            navigateToReplace(new ShoppingCartFragment(), null, true);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            navigateToReplace(new StoreListFragment(), null, true);
        } else if (id == R.id.nav_search_by_keyword) {
            navigateToReplace(new SearchKeywordFragment(), null, true);
        } else if (id == R.id.nav_push_notification) {

        } else if (id == R.id.nav_profile) {
            //navigateToReplace(new ProfileFragment(), null, true);
            Intent intentLogin = new Intent(this,LoginActivity.class);
            startActivity(intentLogin);
            finish();

        } else if (id == R.id.nav_transactions_history) {

        } else if (id == R.id.nav_my_favourite_items) {

        } else if (id == R.id.nav_logout) {
            UtilitySharedPreferences.clearSharedPrefData(MainActivity.this, AppConstant.PRF_SIGNUP_USERID);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public  void navigateToReplace(Fragment newFragment, Bundle bundle, boolean addStack) {
        //newFragment.setArguments(bundle);
        manager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.content_frame, newFragment);
        if (addStack)
            ft.addToBackStack(newFragment.getClass().getName());
        ft.commit();

    }
}
