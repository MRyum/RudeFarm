package com.example.yum.rudefarm;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Clothes tab1 = new Clothes();
                return tab1;
            case 1:
                Hair tab2 = new Hair();
                return tab2;
            case 2:
                Shoes tab3 = new Shoes();
                return tab3;
            case 3:
                Myroom tab4 = new Myroom();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}