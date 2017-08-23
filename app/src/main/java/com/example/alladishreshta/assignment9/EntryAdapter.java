package com.example.alladishreshta.assignment9;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.example.alladishreshta.assignment9.R.id.imageView3;


public class EntryAdapter extends BaseAdapter {

    List list = new ArrayList<>();
    Context c;
    public EntryAdapter(Context context, ArrayList<Entries> e) {
//        super(context);

        c = context;
        list = e;
    }

    public void setData(ArrayList<Entries> newData) {
        list.clear();
        list = new ArrayList(newData);
        Log.d("test", "data size = " + list.size());
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row;
        row = convertView;
        EntryHolder entryHolder;
        if(row == null){

            LayoutInflater layoutInflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            row=layoutInflater.inflate(R.layout.row_layout,parent,false);
            entryHolder = new EntryHolder();
            entryHolder.tx_name = (TextView) row.findViewById(R.id.tx_name);
            entryHolder.imageView = (ImageView) row.findViewById(R.id.imageView);
            entryHolder.imageView2 = (ImageView) row.findViewById(R.id.imageView2);
            entryHolder.imageView3 = (ImageView) row.findViewById(imageView3);
//            ImageView imageView = (ImageView) row.findViewById(R.id.imageView);
//
//            Picasso.with(c)
//                    .load("https://cms-assets.tutsplus.com/uploads/users/21/posts/19431/featured_image/CodeFeature.jpg")
//                    .into(imageView);
        }
        else{
            entryHolder = (EntryHolder) row.getTag();
            if(entryHolder == null){
                entryHolder = new EntryHolder();
                entryHolder.tx_name = (TextView) row.findViewById(R.id.tx_name);
                entryHolder.imageView = (ImageView) row.findViewById(R.id.imageView);
                entryHolder.imageView2 = (ImageView) row.findViewById(R.id.imageView2);
                entryHolder.imageView3 = (ImageView) row.findViewById(imageView3);
            }
        }
        final Entries entries = (Entries) this.getItem(position);
        entryHolder.tx_name.setText(entries.getName());
        Picasso.with(c)
                .load(entries.getImage())
                .into(entryHolder.imageView);

        entryHolder.imageView3.setTag(entries.getId());
        row.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(c,Detail.class);
                intent.putExtra("id",entries.getId());
                intent.putExtra("entry",entries);
                c.startActivity(intent);
            }
        });

        return row;
    }

    static class EntryHolder{
        //Need to up date here -----------------------------------
        TextView tx_name;
        ImageView imageView,imageView2,imageView3;
    }
}

