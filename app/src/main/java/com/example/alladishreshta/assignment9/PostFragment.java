package com.example.alladishreshta.assignment9;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

public class PostFragment extends Fragment {

    ArrayList<Posts> list;
    String result,json_string;
    JSONObject jsonObject;
    ListView listView;
    View rootView;
    JSONArray jsonArray;
    DisplayPostAdapter displayPostAdapter;

    public void onCreate (@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.post_fragment, container, false);
        //what should i do here??????????
        listView = (ListView)rootView.findViewById(R.id.list_view);
        new GetJsonDetail().execute();

        list = new ArrayList<Posts>();
        return rootView;
    }

    public class GetJsonDetail extends AsyncTask<String,String,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            // super.onPostExecute(s);
            json_string = s;
            try {

                jsonObject = new JSONObject(s);
                String name = jsonObject.getString("name");
                String image = jsonObject.getJSONObject("picture").getJSONObject("data").getString("url");
                jsonArray = jsonObject.getJSONObject("posts").getJSONArray("data");
                if(jsonArray.length()==0){

                    TextView t = (TextView) rootView.findViewById(R.id.noposttext);
                    t.setVisibility(View.VISIBLE);
                    ListView e = (ListView) rootView.findViewById(R.id.list_view);
                    e.setVisibility(View.GONE);
                }
                else {
                    TextView t = (TextView) rootView.findViewById(R.id.noposttext);
                    t.setVisibility(View.GONE);
                    ListView e = (ListView) rootView.findViewById(R.id.list_view);
                    e.setVisibility(View.VISIBLE);
                }
                int count=0;
                String message,date;
                while(count<jsonArray.length()){
                    JSONObject JO= jsonArray.getJSONObject(count);
                    message=JO.getString("message");
                    date = JO.getString("created_time");
                    Posts posts = new Posts(name,date,image,message);
                    list.add(posts);
                    count++;

                }

                displayPostAdapter = new DisplayPostAdapter(getActivity(), list);
                listView.setAdapter(displayPostAdapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        @Override
        protected String doInBackground(String... params) {
            try {
                String k;
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