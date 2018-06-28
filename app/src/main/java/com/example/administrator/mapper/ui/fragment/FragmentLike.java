package com.example.administrator.mapper.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.mapper.R;
import com.example.administrator.mapper.weight.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/24.
 */

public class FragmentLike extends Fragment {
    private PagerSlidingTabStrip pagerSlidingTabStrip;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_like, container, false);
        pagerSlidingTabStrip = inflate.findViewById(R.id.pagersliding);
        viewPager = inflate.findViewById(R.id.viewpager);
        pagerSlidingTabStrip.setIndicatorHeight(10);
        pagerSlidingTabStrip.setIndicatorColor(0xffFFD802);
        pagerSlidingTabStrip.setDividerColor(0xffffffff);
        pagerSlidingTabStrip.setDividerPadding(30);
        pagerSlidingTabStrip.setShouldExpand(true);
        viewPager.setCurrentItem(0);
        List<Fragment> fragments=new ArrayList<>();
        fragments.add(new FragmentLikeUsage());
        fragments.add(new FragmentLikeUser());
        String [] title={"收藏的文章","喜欢的作者"};
        MyAdapter adapter=new MyAdapter(getActivity().getSupportFragmentManager(),title,fragments);
        viewPager.setAdapter(adapter);
        pagerSlidingTabStrip.setViewPager(viewPager);
        return inflate;

    }

    private class MyAdapter extends FragmentPagerAdapter {

        private String[] title;
        private List<Fragment> fragments;

        public MyAdapter(FragmentManager fm, String[] title, List<Fragment> fragments) {
            super(fm);
            this.title = title;
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
