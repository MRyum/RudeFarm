package com.example.yum.rudefarm;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.support.v7.widget.RecyclerView.*;

/**
 * Created by hp1 on 28-12-2014.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private String[] Text;
    private int Img[];

    private String name;
    private int profile;
    private String email;
    private static int point_score;
    private static int percent_score;
    private RecyclerView mRecyclerView;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        int Holderid;

        TextView textView;
        ImageView imageView;
        ImageView profile;
        TextView Name;
        TextView email;
        TextView percent;
        TextView point;
        View v;


        public ViewHolder(View itemView, int ViewType) {
            super(itemView);

            if (ViewType == TYPE_ITEM) {
                textView = (TextView) itemView.findViewById(R.id.rowText);
                imageView = (ImageView) itemView.findViewById(R.id.rowIcon);
                Holderid = 1; // setting holder id as 1 as the object being;
                v = itemView;
            } else if (ViewType == TYPE_HEADER) {

                percent = (TextView) itemView.findViewById(R.id.percent);
                point = (TextView) itemView.findViewById(R.id.point);
                Name = (TextView) itemView.findViewById(R.id.name);
                email = (TextView) itemView.findViewById(R.id.email);
                profile = (ImageView) itemView.findViewById(R.id.profile_img);

                point.setText(point_score + "P");
                percent.setText(percent_score + "%");
                v = itemView;
                Holderid = 0;

            }
        }
    }


    MyAdapter(String[] Text, int Img[], String Name, String Email, int Profile) { // MyAdapter Constructor with titles and icons parameter
        this.Text = Text;
        this.Img = Img;
        name = Name;
        email = Email;
        profile = Profile;
        point_score = 0;
        percent_score = 0;
        //in adapter


    }

    MyAdapter() {}


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false); //Inflating the layout

            ViewHolder vhItem = new ViewHolder(v, viewType);

            return vhItem;

        } else if (viewType == TYPE_HEADER) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header, parent, false);

            ViewHolder vhHeader = new ViewHolder(v, viewType);

            return vhHeader;


        }
        return null;

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (holder.Holderid == 1) {
            holder.textView.setText(Text[position - 1]);
//            Typeface typeface = Typeface.createFromAsset(holder.v.getContext().getAssets(), "BM-JUA.ttf");
//            holder.textView.setTypeface(typeface);

            Typefaces.setFont("KoPubDotumLight.ttf", holder.textView, holder.itemView);
            holder.imageView.setImageResource(Img[position - 1]);

        } else if (holder.Holderid == 0) {
            holder.profile.setImageResource(profile);
            holder.Name.setText(name);
            holder.email.setText(email);

        }

    }

    @Override
    public int getItemCount() {
        return Text.length + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }


}