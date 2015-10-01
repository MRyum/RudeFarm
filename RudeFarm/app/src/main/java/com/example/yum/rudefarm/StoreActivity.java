package com.example.yum.rudefarm;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;


public class StoreActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PagerAdapter adapter;
    private Toolbar toolbar;

    private ImageView eventBanner;

    public void initTabBarAndPager() {
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.button_clothes));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.button_hair));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.button_shoes));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.button_myroom));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        eventBanner = (ImageView) findViewById(R.id.eventBanner);

        viewPager = (ViewPager) findViewById(R.id.pager);
        adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setOffscreenPageLimit(4);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 3) {
                    TranslateAnimation moveAnimation = new TranslateAnimation(0, -2000, 0, 0);
                    moveAnimation.setDuration(100);
                    eventBanner.setAnimation(moveAnimation);
                    eventBanner.setVisibility(View.GONE);

                } else
                    eventBanner.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    public void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView toolbarText = ((TextView) toolbar.getChildAt(1));
        ImageView backArrow = (ImageView) toolbar.getChildAt(0);

        Typefaces.setFont("KoPubDotumLight.ttf", toolbarText, toolbar);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store);

        initToolbar();
        initTabBarAndPager();


    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return false;
    }
}