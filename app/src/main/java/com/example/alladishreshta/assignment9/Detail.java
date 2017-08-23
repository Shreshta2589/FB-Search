package com.example.alladishreshta.assignment9;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.alladishreshta.assignment9.favourites.FavActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static android.R.attr.key;
import static android.R.id.list;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class Detail extends AppCompatActivity {
    ViewPager viewPager;
    DetailAdapter adapter;
    ListView listView1;
    ShareDialog shareDialog;
    CallbackManager callbackManager;
    String id,name1,image1;
    Set<String> set;
    Entries entry;
    List<String> id_list = new ArrayList<String>();
    List<Entries> fav_list = new ArrayList<Entries>();
    Boolean on;
    private Menu menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("More Details");
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_black_24dp); // your drawable
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Implemented by activity
            }
        });
//        Toolbar.showOverflowMenu();

        listView1 = (ListView) findViewById(R.id.listview1);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout1);
        tabLayout.addTab(tabLayout.newTab().setText("Albums").setIcon(R.drawable.albums));
        tabLayout.addTab(tabLayout.newTab().setText("Posts").setIcon(R.drawable.posts));
        callbackManager = CallbackManager.Factory.create();
        FacebookSdk.setApplicationId("743397835811303");
        FacebookSdk.sdkInitialize(getApplicationContext());
        shareDialog = new ShareDialog(this);
        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>(){
            @Override
            public void onSuccess(Sharer.Result result) {
                Toast.makeText(Detail.this, "Sharing ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(Detail.this, "Did not share ", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(Detail.this, "Did not share ", Toast.LENGTH_SHORT).show();
            }
        });
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.pager1);
        adapter = new DetailAdapter
                (getSupportFragmentManager(), 2);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        name1 = getIntent().getStringExtra("name");
        image1 = getIntent().getStringExtra("image");
        entry = (Entries)intent.getSerializableExtra("entry");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
                getMenuInflater().inflate(R.menu.share_menu, menu);
        MenuItem item= menu.findItem(R.id.threedot);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        SharedPreferences sharedPref = getSharedPreferences("favourites", MODE_PRIVATE);
        set = sharedPref.getStringSet("total_entries", null);
        Gson gson = new Gson();
        String json = gson.toJson(entry);
        if(set == null){
            set = new HashSet<String>();
        }
        if(set.contains(json)){
            menu.findItem(R.id.fav).setTitle("Remove from Favourites");
        }
        else{
            menu.findItem(R.id.fav).setTitle("Add to Favourites");
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int menuid = item.getItemId();
        switch (menuid) {
            case R.id.fav:
                //add to fav list and save in shared preference
//                id_list.add(id);
                SharedPreferences sharedPref = getSharedPreferences("favourites", MODE_PRIVATE);
                SharedPreferences.Editor prefs = getSharedPreferences("favourites",MODE_PRIVATE).edit();
                set = sharedPref.getStringSet("total_entries", null);
                if(set == null){
                    set = new HashSet<String>();
                }

                Gson gson = new Gson();
                String json = gson.toJson(entry);
                if(set.contains(json)){
//                    set.remove(this.id);
//                    Entries staff = gson.fromJson(json, Entries.class);
//                    staff.setOn("false");
//                    String json1 = gson.toJson(staff);
                    set.remove(json);
                    Toast.makeText(Detail.this, "Removed from Favourites",Toast.LENGTH_LONG).show();
                }else {
//                    set.add(this.id);
//                    Entries staff = gson.fromJson(json, Entries.class);
////                    staff.setOn("true");
//                    String json1 = gson.toJson(staff);
                    set.add(json);
                    Toast.makeText(Detail.this, "Added to Favourites",Toast.LENGTH_LONG).show();
                }


                prefs.putStringSet("total_entries", set);

                Log.v("json string:",json);
                prefs.commit();
                invalidateOptionsMenu();
                return true;

            case R.id.share:
                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setImageUrl(Uri.parse(entry.getImage()))
                            .setContentTitle(entry.getName())
                            .setContentDescription("FB SEARCH FROM USC CSCI571...")
                            .setContentUrl(Uri.parse(entry.getImage()))
                            .build();
                    shareDialog.show(linkContent);
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}

