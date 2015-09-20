package com.example.yum.rudefarm;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

/**
 * Created by YUM on 2015-09-11.
 */
public class Typefaces {

    static Typeface typeface;

    static public void setFont(String fontName, TextView tv, View view) {
        typeface = Typeface.createFromAsset(view.getContext().getAssets(), fontName);
        tv.setTypeface(typeface);
    }

}
