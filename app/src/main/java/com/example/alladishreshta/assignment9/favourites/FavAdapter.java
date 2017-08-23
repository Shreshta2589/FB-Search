package com.example.alladishreshta.assignment9.favourites;

import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.alladishreshta.assignment9.TabFragment1;


public class FavAdapter extends FragmentPagerAdapter {
    int mNumOfTabs1;

    public FavAdapter(FragmentManager fm1, int NumOfTabs1) {
        super(fm1);
        this.mNumOfTabs1 = NumOfTabs1;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle b;

        switch (position) {
            case 0:
                FavFragment tab1 = new FavFragment();
                b =new Bundle();
                b.putString("type","user");
                tab1.setArguments(b);
                return tab1;
            case 1:
                FavFragment tab2 = new FavFragment();
                b =new Bundle();
                b.putString("type","page");
                tab2.setArguments(b);
                return tab2;
            case 2:
                FavFragment tab3 = new FavFragment();
                b =new Bundle();
                b.putString("type","event");
                tab3.setArguments(b);
                return tab3;
            case 3:
                FavFragment tab4 = new FavFragment();
                b =new Bundle();
                b.putString("type","place");
                tab4.setArguments(b);
                return tab4;
            case 4:
                FavFragment tab5 = new FavFragment();
                b =new Bundle();
                b.putString("type","group");
                tab5.setArguments(b);
                return tab5;
        }
        return null;
    }

    @Override
    public int getCount() {
        return mNumOfTabs1;
    }
}

