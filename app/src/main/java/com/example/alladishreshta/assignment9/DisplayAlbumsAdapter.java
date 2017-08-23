package com.example.alladishreshta.assignment9;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DisplayAlbumsAdapter extends BaseExpandableListAdapter {
    Context context;
    ArrayList<String> list;
    HashMap<String, ArrayList<Images>> map;
    DisplayAlbumsAdapter(Context context, ArrayList<String> list, HashMap<String, ArrayList<Images>> map){
        this.context = context;
        this.list = list;
        this.map = map;

    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return map.get(list.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return (map.get(list.get(groupPosition))).get(childPosition);

    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View row=layoutInflater.inflate(R.layout.header_layout,parent,false);
        TextView txt = (TextView)row.findViewById(R.id.heading);
        //txt.setText((Names) );
        String l = list.get(groupPosition);
        txt.setText(l);
        return row;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=layoutInflater.inflate(R.layout.body_layout,parent,false);
        ImageView img1 = (ImageView) row.findViewById(R.id.pic1);

//        Picasso.with(context)
//                .load((map.get(list.get(groupPosition))).get(childPosition).getImage())
//                .into(img1);

        Picasso.with(context)
                .load("https://graph.facebook.com/v2.8/"+(map.get(list.get(groupPosition))).get(childPosition).getImage()+"/picture?access_token=EAAKkHcvuKecBAGiyPfxz4szyRl9pyN4mNPehZCyMoTVSkOpoWOO5Oe5Ylijr61NlbPNCmZAyM2OX94nZB8llHwiE7i2YKZAu3uMfuraw2jtUXpBIZA3ZAneZCsQS5l7xZCE7sxEUX8ZCUaY1sQ9F08jpz")
                .into(img1);
        return row;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
