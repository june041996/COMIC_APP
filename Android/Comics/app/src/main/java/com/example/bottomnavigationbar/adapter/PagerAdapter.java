package com.example.bottomnavigationbar.adapter;





import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapter extends FragmentPagerAdapter {

  private final List<Fragment> mFragments = new ArrayList<>();
  private final List<String> mFragmentTitles = new ArrayList<>();

  public PagerAdapter(FragmentManager fm,int i) {
    super(fm,i);
  }

  public void addFragment(Fragment fragment, String title) {
    mFragments.add(fragment);
    mFragmentTitles.add(title);
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
  public CharSequence getPageTitle(int position) {
    return mFragmentTitles.get(position);
  }


}