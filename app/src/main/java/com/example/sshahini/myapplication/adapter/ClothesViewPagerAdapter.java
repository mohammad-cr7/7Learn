package com.example.sshahini.myapplication.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.sshahini.myapplication.view.fragment.ClothesFragment;

/**
 * Created by Saeed shahini on 7/30/2016.
 */
public class ClothesViewPagerAdapter extends FragmentPagerAdapter {

    public ClothesViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return ClothesFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "مشاهده شده ها";
            case 1:
                return "پربازدیدترین ها";
            case 2:
                return "جدیدترین ها";
            default:
                return "";
        }
    }
}
