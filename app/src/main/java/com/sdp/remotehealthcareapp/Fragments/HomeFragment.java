package com.sdp.remotehealthcareapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.sdp.remotehealthcareapp.Fragments.HomeFragment_Tabs.HomeFragment_budget;
import com.sdp.remotehealthcareapp.Fragments.HomeFragment_Tabs.HomeFragment_newest;
import com.sdp.remotehealthcareapp.Fragments.HomeFragment_Tabs.HomeFragment_one;
import com.sdp.remotehealthcareapp.R;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    ImageView img1,img2,img3,img4,img5;
    View v;
    ViewPager viewPager;
    TabLayout tabLayout;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_home, container, false);
        setHorizontalImages();
        //scroll tabs
        viewPager =v.findViewById(R.id.viewpager_home);
        setupViewPager(viewPager);
        tabLayout=v.findViewById(R.id.tabLayout_home);
        tabLayout.setupWithViewPager(viewPager);
        return  v;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new HomeFragment_one(), "Categories");
        adapter.addFragment(new HomeFragment_newest(), "Newest");
        adapter.addFragment(new HomeFragment_budget(), "Budget Friendly");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {

            // return null to display only the icon
            return mFragmentTitleList.get(position);
        }
    }
    public void setHorizontalImages()
    {
        LinearLayout gallery=v.findViewById(R.id.galley);
        LayoutInflater inf= LayoutInflater.from(getActivity());

        View view=inf.inflate(R.layout.images_list, gallery,false);
        img1=(view.findViewById(R.id.image_1));
        img1.setImageResource(R.drawable.five);

        img2=(view.findViewById(R.id.image_2));
        img2.setImageResource(R.drawable.two);

        img3=(view.findViewById(R.id.image_3));
        img3.setImageResource(R.drawable.three);

        img4=(view.findViewById(R.id.image_4));
        img4.setImageResource(R.drawable.four);

        img5=(view.findViewById(R.id.image_5));
        img5.setImageResource(R.drawable.one);

        //imageView.setImageResource(R.drawable.five);
        gallery.addView(view);
    }

}