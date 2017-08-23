package com.example.alladishreshta.assignment9;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class DetailAdapter extends FragmentPagerAdapter{
    int mNumOfTabs;

    public DetailAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    public Fragment getItem(int position){
        switch (position){
            case 0:
                AlbumFragment album = new AlbumFragment();
                return album;
            case 1:
                PostFragment post = new PostFragment();
                return post;


        }
        return null;
    }
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
