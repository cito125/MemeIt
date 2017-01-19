package com.example.andresarango.memeit;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.andresarango.memeit.tabfragments.HomeFragment;
import com.example.andresarango.memeit.tabfragments.MemeListFragment;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tablayout; // Import design in build.gradle
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        toolbar.setTitleTextColor(0xFFFFFFFF);
        setSupportActionBar(toolbar);
    }

    private void setupTabLayout(TabLayout tablayout) {
        tablayout.setTabTextColors(0xFFFFFFFF, 0xFFFFFFFF);
        tablayout.setSelectedTabIndicatorColor(0xFFFFFFFF);
    }

    private void setupViewPager(ViewPager pager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment(), "Home");
        adapter.addFragment(new MemeListFragment(), "Meme List");
        pager.setAdapter(adapter);
    }
}
