package com.example.andresarango.memeit;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
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
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

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
        verifyStoragePermissions(this);
    }

    private void setupToolbar(Toolbar toolbar) {
        toolbar.setTitle("Create a meme");
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

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 0) {
                    toolbar.setTitle("Create a meme");
                } else if (position == 1) {
                    toolbar.setTitle("Your created memes");
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    // Overriding to change the fragment inside the viewpager adapter
    @Override
    public void onBackPressed() {
        if (pager.getCurrentItem() == 0) {
            if (adapter.getItem(0) instanceof StockPicsFragment) {
                ViewPagerAdapter.vpInstance.setHomeFragment();
            } else {
                super.onBackPressed();
            }
        } else {
            super.onBackPressed();
        }
    }

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have read or write permission
        int writePermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int readPermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (writePermission != PackageManager.PERMISSION_GRANTED || readPermission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);


        }
    }
}
