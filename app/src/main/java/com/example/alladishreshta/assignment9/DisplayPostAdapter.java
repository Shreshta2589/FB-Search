package com.example.alladishreshta.assignment9;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DisplayPostAdapter extends BaseAdapter {

    List list = new ArrayList<Posts>();
    Context c;
    public DisplayPostAdapter(Context context, ArrayList<Posts> e) {
//        super(context);

        c = context;
        list = e;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row = convertView;
        PostHolder postHolder;
        if(row == null){
            LayoutInflater layoutInflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            row=layoutInflater.inflate(R.layout.post_list_item,parent,false);
            postHolder = new DisplayPostAdapter.PostHolder();
            postHolder.imagePostView = (ImageView) row.findViewById(R.id.imagePostView);
            postHolder.name = (TextView) row.findViewById(R.id.name);
            postHolder.date = (TextView) row.findViewById(R.id.date);
            postHolder.message = (TextView) row.findViewById(R.id.message);
            row.setTag(postHolder);

        }
        else{
            postHolder = (PostHolder) row.getTag();
        }
        postHolder.name.setText(((Posts)getItem(position)).getName());
        postHolder.date.setText(((Posts)getItem(position)).getDate());
        postHolder.message.setText(((Posts)getItem(position)).getMessage());
        Picasso.with(c)
                .load(((Posts)getItem(position)).getImage())
                .into(postHolder.imagePostView);

        return row;
    }

    static class PostHolder {
        ImageView imagePostView;
        TextView name,date,message;

    }
}
