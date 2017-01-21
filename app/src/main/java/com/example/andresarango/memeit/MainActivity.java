package com.example.andresarango.memeit;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.andresarango.memeit.viewpager.ViewPagerAdapter;
import com.example.andresarango.memeit.viewpager.tabfragments.HomeFragment;
import com.example.andresarango.memeit.viewpager.tabfragments.MemeListFragment;
import com.example.andresarango.memeit.viewpager.tabfragments.StockPicsFragment;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tablayout; // Import design in build.gradle
    private ViewPager pager;
    private ViewPagerAdapter adapter;
    private int mToolbarTitleTextColor = 0xFFFFFFFF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }


    private void initialize() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tablayout = (TabLayout) findViewById(R.id.tab_layout);
        pager = (ViewPager) findViewById(R.id.viewpager);

        setupToolbar(toolbar);
        setupTabLayout(tablayout);
        setupViewPager(pager);
        tablayout.setupWithViewPager(pager);
    }

    private void setupToolbar(Toolbar toolbar) {
        toolbar.setTitle("Placeholder Title");
        toolbar.setTitleTextColor(mToolbarTitleTextColor);
        setSupportActionBar(toolbar);
    }

    private void setupTabLayout(TabLayout tablayout) {
        tablayout.setTabTextColors(mToolbarTitleTextColor, mToolbarTitleTextColor);
        tablayout.setSelectedTabIndicatorColor(mToolbarTitleTextColor);
    }

    private void setupViewPager(ViewPager pager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment(), "Create");
        adapter.addFragment(new MemeListFragment(), "History");
        pager.setAdapter(adapter);
    }

    // Overriding to change the fragment inside the viewpager adapter
    @Override
    public void onBackPressed() {
        if(pager.getCurrentItem() == 0) {
            if (adapter.getItem(0) instanceof StockPicsFragment) {
                ViewPagerAdapter.vpInstance.setHomeFragment();
            } else {
                super.onBackPressed();
            }
        } else {
            super.onBackPressed();
        }
    }
}
