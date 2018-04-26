package com.icono.organicanimal;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alfo6-10 on 2018-03-21.
 */

public class a_Adapter_fragment extends FragmentPagerAdapter {
    private final List<Fragment> mFragments = new ArrayList<>();
    private final List<String> mFragmentTitles = new ArrayList<>();
    private final List<FragmentInfo> mFragmentInfoList = new ArrayList<>();

    public a_Adapter_fragment(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String title){
        FragmentInfo info = new FragmentInfo(title, fragment);
        mFragments.add(fragment);
        mFragmentTitles.add(title);
        mFragmentInfoList.add(info);
    }

    public FragmentInfo getFragmentInfo(int position){
        return mFragmentInfoList.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position){
        return mFragmentTitles.get(position);
    }
}

class FragmentInfo{
    private String title;
    private Fragment fragment;

    public FragmentInfo(String title, Fragment fragment) {
        this.title = title;
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public Fragment getFragment() {
        return fragment;
    }
}
