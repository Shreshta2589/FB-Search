package com.example.alladishreshta.assignment9;

import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class AlbumFragment extends Fragment {

    ExpandableListView listView;
    ArrayList<String> list;
    ArrayList<Images> imageList;
    HashMap<String, ArrayList<Images>> map;
    String result,json_string;
    JSONArray jsonArray,jsonPhotoArray;
    JSONObject jsonObject;
    DisplayAlbumsAdapter displayAlbumAdapter;
    View rootView;

    public void onCreate (@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.album_fragment, container, false);
        listView = (ExpandableListView) rootView.findViewById(R.id.exp_listview);
        new AlbumFragment.GetJsonAlbum().execute();

//        list = new ArrayList<String>();
//        imageList = new ArrayList<Images>();
//        map = new HashMap<String, ArrayList<Images>>();
        return rootView;
    }

    public class GetJsonAlbum extends AsyncTask<String,String,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            //super.onPostExecute(s);
            json_string = s;
            try {
                String name,imageurl;
                jsonObject = new JSONObject(s);
                jsonArray = jsonObject.getJSONObject("albums").getJSONArray("data");
                int count = 0;
                int photocount;
                list = new ArrayList<String>();
                if(jsonArray.length()==0){

                    TextView t = (TextView) rootView.findViewById(R.id.noalbumtext);
                    t.setVisibility(View.VISIBLE);
                    ExpandableListView e = (ExpandableListView) rootView.findViewById(R.id.exp_listview);
                    e.setVisibility(View.GONE);
                }
                else {
                    TextView t = (TextView) rootView.findViewById(R.id.noalbumtext);
                    t.setVisibility(View.GONE);
                    ExpandableListView e = (ExpandableListView) rootView.findViewById(R.id.exp_listview);
                    e.setVisibility(View.VISIBLE);
                }

                map = new HashMap<String, ArrayList<Images>>();
                while(count<jsonArray.length()){

                    imageList = new ArrayList<Images>();
                    photocount = 0;
                    JSONObject JO= jsonArray.getJSONObject(count);
                    name = JO.getString("name");
                    //Names n = new Names(name);
                    list.add(name);
                    if(JO.getJSONObject("photos")!=null) {
                    jsonPhotoArray = JO.getJSONObject("photos").getJSONArray("data");

                        while (photocount < jsonPhotoArray.length()) {
                            JSONObject JP = jsonPhotoArray.getJSONObject(photocount);
                            imageurl = JP.getString("id");
                            Images images = new Images(imageurl);
                            imageList.add(images);
                            photocount++;
                        }

                        map.put(name, imageList);

                        count++;

                    }
                    else {
                        continue;
                    }
//                    displayAlbumAdapter = new DisplayAlbumsAdapter(getActivity(),list,map);
//                    listView.setAdapter(displayAlbumAdapter);
                }
                displayAlbumAdapter = new DisplayAlbumsAdapter(getActivity(),list,map);
                listView.setAdapter(displayAlbumAdapter);
            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("ninja",  e.getMessage());
            }
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                String strurl = "http://rayappan.us-west-2.elasticbeanstalk.com/fb.php?id="+((Detail)getActivity()).id;
                URL url = new URL(strurl);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.connect();

                BufferedReader bf = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String value = bf.readLine();
                result = value;
            }
            catch (Exception e){
                System.out.println(e);
            }
            return result;
        }
    }

}

