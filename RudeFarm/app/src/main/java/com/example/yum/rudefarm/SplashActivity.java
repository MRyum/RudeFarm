package com.example.yum.rudefarm;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class SplashActivity extends ActionBarActivity {

    Handler mHandler;
    Runnable mRunable;

    public void floatSplash() {
        mHandler = new Handler();
        mRunable = new Runnable() {
            @Override
            public void run() {
                finish();
            }
        };
        mHandler.postDelayed(mRunable, 2000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        floatSplash();

    }
}
