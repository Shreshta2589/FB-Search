package com.example.alladishreshta.assignment9;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.example.alladishreshta.assignment9.R.id.next;
import static com.example.alladishreshta.assignment9.R.id.previous;

public class TabFragment1 extends Fragment{

    String result,json_string,strurl;
    JSONObject jsonObject;
    JSONArray jsonArray;
    EntryAdapter entryAdapter;
    String type,k,nextURL,previousURL;
    ArrayList<Entries> list1,list2,list3;
    ListView listView;
    Boolean isNextPresent,isPreviousPresent;
    public static Button Button_1;
    public static Button Button_2;
    private int current;
    Set<String> jsonPreferences;
    Boolean on;
    LocationManager mLocationManager;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        k = ((DisplayMessageActivity)getActivity()).message;
        k=k.trim();
        current=1;


        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getString("type");
            System.out.println(type);
        }
        mLocationManager = (LocationManager) getActivity().getSystemService(getActivity().LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, ((DisplayMessageActivity) getActivity()).mLocationListener);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //View rootView = inflater.inflate(R.layout.tab_fragment_1, container, false);
        View rootView = inflater.inflate(R.layout.activity_display_message, container, false);
        listView = (ListView) rootView.findViewById(R.id.listview);
        Button_1=(Button)getActivity().findViewById(R.id.next);
        Button_2=(Button)getActivity().findViewById(R.id.previous);


        Button_2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                current--;
                Log.d("test inside button 2", current + "");

                if(current==1){
                    Button_1.setEnabled(true);
                    Button_2.setEnabled(false);
                }
                if(current==2){
                    Button_1.setEnabled(true);
                    Button_2.setEnabled(true);
                }
                if(current==3){
                    Button_1.setEnabled(false);
                    Button_2.setEnabled(true);
                }

                if(current==1){
                    entryAdapter = new EntryAdapter(getActivity(), list1);
                }
                if(current==2) {
                    entryAdapter = new EntryAdapter(getActivity(), list2);
                }
                if(current==3){
                    entryAdapter = new EntryAdapter(getActivity(), list3);
                }
//                ((DisplayMessageActivity)getActivity()).listView.setAdapter(entryAdapter);
                listView.setAdapter(entryAdapter);
                entryAdapter.notifyDataSetChanged();
            }
        });

        Button_1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                current++;
                Log.d("test inside button 1", current + "");

                if(current==1){
                    Button_1.setEnabled(true);
                    Button_2.setEnabled(false);
                }
                if(current==2){
                    Log.d("test inside button 1", "" + current);
                    Button_1.setEnabled(true);
                    Button_2.setEnabled(true);
                }
                if(current==3){
                    Button_1.setEnabled(false);
                    Button_2.setEnabled(true);
                }

                if(current==1){
                    entryAdapter = new EntryAdapter(getActivity(), list1);
                }
                if(current==2) {
                    entryAdapter = new EntryAdapter(getActivity(), list2);
                }
                if(current==3){
                    entryAdapter = new EntryAdapter(getActivity(), list3);
                }
//                ((DisplayMessageActivity)getActivity()).listView.setAdapter(entryAdapter);
                listView.setAdapter(entryAdapter);
                entryAdapter.notifyDataSetChanged();
            }
        });

        if(current==1){
            Button_1.setEnabled(true);
            Button_2.setEnabled(true);
        }
        if(current==2){
            Button_1.setEnabled(true);
            Button_2.setEnabled(true);
        }
        if(current==3){
            Button_1.setEnabled(false);
            Button_2.setEnabled(true);
        }
        new GetJsonData().execute(type);

        //Here starts the new code:

//        String url;
//        if (type.equals("place")) {
//            if (((DisplayMessageActivity) getActivity()).lat == null) {
//                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                    // TODO: Consider calling
//                    //    ActivityCompat#requestPermissions
//                    // here to request the missing permissions, and then overriding
//                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                    //                                          int[] grantResults)
//                    // to handle the case where the user grants the permission. See the documentation
//                    // for ActivityCompat#requestPermissions for more details.
//                    return null;
//                }
//                Location location =  mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//                ((DisplayMessageActivity) getActivity()).lat = location.getLatitude()+"";
//                ((DisplayMessageActivity) getActivity()).longitude = location.getLatitude()+"";
//            }
//
//            url = "http://proud-climber-162901.appspot.com/?keyword="+k+"&type="+type+"&center="+((DisplayMessageActivity)getActivity()).lat+","+((DisplayMessageActivity)getActivity()).longitude;
//        }else
//            url = "http://proud-climber-162901.appspot.com/?keyword="+k+"&type="+type;
//        new GetJsonData().execute(url);


        //Ends her

        list1 = new ArrayList<Entries>();
        list2 = new ArrayList<Entries>();
        list3 = new ArrayList<Entries>();
        return rootView;
    }

    public class GetJsonData extends AsyncTask<String,String,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {

            json_string = s;
            try {

                jsonObject = new JSONObject(s);
//                Log.d("JSON", "" + jsonObject.toString());

                jsonArray = jsonObject.getJSONArray("data");
                SharedPreferences sharedPref = getActivity().getSharedPreferences("favourites", getActivity().MODE_PRIVATE);
                jsonPreferences = sharedPref.getStringSet("total_entries", null);


                int count=0;
                String name,image,id;
                for(int i=0;i<10;i++){
                    JSONObject JO= jsonArray.getJSONObject(i);
                    name=JO.getString("name");
                    id=JO.getString("id");
                    image = JO.getJSONObject("picture").getJSONObject("data").getString("url");
                    Entries entries = new Entries(name);
                    entries.setImage(image);
                    entries.setId(id);
                    entries.setType(type);
                    list1.add(entries);
                }
                for(int i=10;i<20;i++){
                    JSONObject JO= jsonArray.getJSONObject(i);
                    name=JO.getString("name");
                    id=JO.getString("id");
                    image = JO.getJSONObject("picture").getJSONObject("data").getString("url");
                    Entries entries = new Entries(name);
                    entries.setImage(image);
                    entries.setId(id);
                    entries.setType(type);
                    list2.add(entries);
                }
                for(int i=20;i<jsonArray.length();i++){
                    JSONObject JO= jsonArray.getJSONObject(i);
                    name=JO.getString("name");
                    id=JO.getString("id");
                    image = JO.getJSONObject("picture").getJSONObject("data").getString("url");
                    Entries entries = new Entries(name);
                    entries.setImage(image);
                    entries.setId(id);
                    entries.setType(type);
                    list3.add(entries);
                }
                Log.d("test", current + "");
                if(current==1){
                    entryAdapter = new EntryAdapter(getActivity(), list1);
                }
                if(current==2) {
                    entryAdapter = new EntryAdapter(getActivity(), list2);
                }
                if(current==3){
                    entryAdapter = new EntryAdapter(getActivity(), list3);
                }
//                ((DisplayMessageActivity)getActivity()).listView.setAdapter(entryAdapter);
                listView.setAdapter(entryAdapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        @Override
        protected String doInBackground(String... params) {
            try {
                if (type.equals("place")) {
//                    if (((DisplayMessageActivity) getActivity()).lat == null) {
//                        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                            return null;
//                        }
//                        Location location =  mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//                        ((DisplayMessageActivity) getActivity()).lat = location.getLatitude()+"";
//                        ((DisplayMessageActivity) getActivity()).longitude = location.getLatitude()+"";
//                    }
                    strurl = "http://rayappan.us-west-2.elasticbeanstalk.com/fb.php?key="+k+"&type="+type+"&latVal=34.052235&lngVal=-118.243683";
                }else
                    strurl = "http://rayappan.us-west-2.elasticbeanstalk.com/fb.php?key="+k+"&type="+type;
                //strurl="http://rayappan.us-west-2.elasticbeanstalk.com/fb.php?key="+k+"&type="+type;
                URL url = new URL(strurl);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.connect();

                BufferedReader bf = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String value = bf.readLine();
                //System.out.println(value);

                result = value;

            }
            catch (Exception e){
                System.out.println(e);
            }
            return result;
        }
    }
}
