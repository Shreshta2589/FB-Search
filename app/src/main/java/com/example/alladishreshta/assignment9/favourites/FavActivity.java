package com.example.alladishreshta.assignment9.favourites;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alladishreshta.assignment9.EntryAdapter;
import com.example.alladishreshta.assignment9.PagerAdapter;
import com.example.alladishreshta.assignment9.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FavActivity extends AppCompatActivity {
    String strurl;
    String result,json_string,message;
    JSONObject jsonObject;
    JSONArray jsonArray;
    EntryAdapter entryAdapter;
    ListView listView1;
    ViewPager viewPager;
    Toolbar toolbar;
    FavAdapter adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        //toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle("favorites");
       // getActionBar().setTitle("Favourites");

        listView1 = (ListView) findViewById(R.id.listview);

        TabLayout tabLayout1 = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout1.addTab(tabLayout1.newTab().setText("Users").setIcon(R.drawable.users));
        tabLayout1.addTab(tabLayout1.newTab().setText("Pages").setIcon(R.drawable.pages));
        tabLayout1.addTab(tabLayout1.newTab().setText("Events").setIcon(R.drawable.events));
        tabLayout1.addTab(tabLayout1.newTab().setText("Places").setIcon(R.drawable.places));
        tabLayout1.addTab(tabLayout1.newTab().setText("Groups").setIcon(R.drawable.groups));
        tabLayout1.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.pager);
        adapter = new FavAdapter(getSupportFragmentManager(), 5);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout1));



    }
}