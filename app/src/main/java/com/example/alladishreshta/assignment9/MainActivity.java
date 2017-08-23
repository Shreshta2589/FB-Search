package com.example.alladishreshta.assignment9;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alladishreshta.assignment9.favourites.FavFragment;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView mNavigationView;

    private Toolbar mToolbar;

    public static final String EXTRA_MESSAGE = "com.example.alladishreshta.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        SharedPreferences sharedPref = getSharedPreferences("favourites", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor= sharedPref.edit() ;
//        editor.clear();
//        editor.commit();
//        scanner =(Button)findViewById(R.id.buttonScanQR);
//        scanner.setOnClickListener(this);

        mToolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);


        mDrawerLayout =(DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.nav_id);

        initNavigationView();

        MainFragment mainFragment= new MainFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction =  getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frames,mainFragment);
        fragmentTransaction.commit();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public void onBackPressed() {
        if (isNavDrawerOpen()) {
            closeNavDrawer();
        } else {
            super.onBackPressed();
        }
    }

    protected boolean isNavDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(GravityCompat.START);
    }

    protected void closeNavDrawer() {
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void initNavigationView(){
        mNavigationView = (NavigationView) findViewById(R.id.nav_id);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        //setting up selected item listener
        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        //Checking if the item is in checked state or not, if not make it in checked state
                        if(menuItem.isChecked()) menuItem.setChecked(false);
                        else menuItem.setChecked(true);

                        //Closing drawer on item click
                        mDrawerLayout.closeDrawers();

                        //Check to see which item was being clicked and perform appropriate action
                        int id = menuItem.getItemId();
                        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        switch (id) {



                            case R.id.nav_home:
                                MainFragment mainfragment=new MainFragment();
                                fragmentTransaction.replace(R.id.frames,mainfragment);
                                fragmentTransaction.commit();
                                getSupportActionBar().setTitle("Search on FB");
                                return true;

                            case R.id.nav_favorites:
                                FavFragment favfragment=new FavFragment();
                                fragmentTransaction.replace(R.id.frames, favfragment);
                                getSupportActionBar().setTitle("Favorites");
                                fragmentTransaction.commit();
//                                Toast.makeText(getApplicationContext(),"fav",Toast.LENGTH_SHORT).show();
                                return true;

                            case R.id.nav_About:
                                AboutFragment abtfragment = new AboutFragment();
                                fragmentTransaction.replace(R.id.frames,abtfragment);
                                fragmentTransaction.commit();
                                return true;


                        }

                        return true;

                    }
                });
    }
}
