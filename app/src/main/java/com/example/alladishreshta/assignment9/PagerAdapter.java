package com.example.alladishreshta.assignment9;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle b;

        switch (position) {
            case 0:
                TabFragment1 tab1 = new TabFragment1();
                b =new Bundle();
                b.putString("type","user");
                tab1.setArguments(b);
                return tab1;
            case 1:
                TabFragment1 tab2 = new TabFragment1();
                b =new Bundle();
                b.putString("type","page");
                tab2.setArguments(b);
                return tab2;
            case 2:
                TabFragment1 tab3 = new TabFragment1();
                b =new Bundle();
                b.putString("type","event");
                tab3.setArguments(b);
                return tab3;
            case 3:
                TabFragment1 tab4 = new TabFragment1();
                b =new Bundle();
                b.putString("type","place");
                tab4.setArguments(b);
                return tab4;
            case 4:
                TabFragment1 tab5 = new TabFragment1();
                b =new Bundle();
                b.putString("type","group");
                tab5.setArguments(b);
                return tab5;
        }
        return null;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}