package com.example.alladishreshta.assignment9.favourites;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.DropBoxManager;
import android.support.annotation.Nullable;
import android.support.constraint.solver.SolverVariable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.alladishreshta.assignment9.DisplayMessageActivity;
import com.example.alladishreshta.assignment9.Entries;
import com.example.alladishreshta.assignment9.EntryAdapter;
import com.example.alladishreshta.assignment9.MainActivity;
import com.example.alladishreshta.assignment9.PagerAdapter;
import com.example.alladishreshta.assignment9.R;
import com.example.alladishreshta.assignment9.TabFragment1;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static android.R.attr.fragment;
import static android.R.attr.type;
import static com.example.alladishreshta.assignment9.R.id.toolbar;
import static com.facebook.FacebookSdk.getApplicationContext;

public class FavFragment extends Fragment {

    String s,ids;
    Set<String> jsonPreferences;
    ArrayList<Entries> list;
    ViewPager viewPager;
    FavAdapter adapter;
    ListView listView1;
    Activity activity;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Favorites");
        SharedPreferences sharedPref = getActivity().getSharedPreferences("favourites", getActivity().MODE_PRIVATE);
        activity = getActivity();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        jsonPreferences = sharedPref.getStringSet("total_entries", null);
//        Log.v("same_json:",jsonPreferences);
//        System.out.println(productFromShared);
        Bundle bundle = getArguments();
        if (bundle != null) {

            s = bundle.getString("type");
            System.out.println(s);
        }
        list=new ArrayList<>();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //View rootView = inflater.inflate(R.layout.tab_fragment_1, container, false);
        Log.d("hello", "abd");
        View rootView = inflater.inflate(R.layout.fav_display_message, container, false);
        Toolbar mToolbar = (Toolbar) rootView.findViewById(toolbar);
        mToolbar.setTitle("Favorites");
        mToolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_black_24dp); // your drawable
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity, MainActivity.class);
                startActivity(intent);
            }
        });

//      ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Favorites");
//
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listView1 = (ListView) rootView.findViewById(R.id.listview);
        Gson gson = new Gson();
        list = new ArrayList<>();

        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Users").setIcon(R.drawable.users));
        tabLayout.addTab(tabLayout.newTab().setText("Pages").setIcon(R.drawable.pages));
        tabLayout.addTab(tabLayout.newTab().setText("Events").setIcon(R.drawable.events));
        tabLayout.addTab(tabLayout.newTab().setText("Places").setIcon(R.drawable.places));
        tabLayout.addTab(tabLayout.newTab().setText("Groups").setIcon(R.drawable.groups));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//        List<Entries> productFromShared = new ArrayList<>();
//        Type type = new TypeToken<List<Entries>>() {}.getType();
//        productFromShared = gson.fromJson(jsonPreferences, type);
        viewPager = (ViewPager) rootView.findViewById(R.id.pager);
        adapter = new FavAdapter
                (getActivity().getSupportFragmentManager(), 5);
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
        if(jsonPreferences!=null) {
            for (String sb : jsonPreferences) {
                Log.d("hello", "" + sb);

                Entries staff = gson.fromJson(sb, Entries.class);
                if (staff.getType().equals(s)) {
                    if (!list.contains(staff))
                        list.add(staff);
                }
            }
        }



        EntryAdapter entryAdapter = new EntryAdapter(getActivity(), list);
//                ((DisplayMessageActivity)getActivity()).listView.setAdapter(entryAdapter);
        listView1.setAdapter(entryAdapter);

        return rootView;
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        //getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }



}
