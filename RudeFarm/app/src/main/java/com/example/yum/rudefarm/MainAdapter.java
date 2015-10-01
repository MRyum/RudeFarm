package com.example.yum.rudefarm;


import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class MainAdapter extends RecyclerView.Adapter {

    private static final int HEADER = 0;
    private static final int ITEM = 1;

    private List<String> stringList;
    private List<Integer> imageList;
    private String point;
    private String level;
    private String farm_name;
    private int animal;

    private DrawerLayout mDrawerLayout;
    private Activity activity;

    public MainAdapter(Activity activity, DrawerLayout mDrawerLayout, List castData, List castImage, String farmName, int point, int level, int animal) {
        stringList = castData;
        imageList = castImage;

        this.farm_name = farmName;
        this.point = String.valueOf(point);
        this.level = String.valueOf(level);
        this.animal = animal;

        this.mDrawerLayout = mDrawerLayout;
        this.activity = activity;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;

        TextView point;
        TextView level;
        TextView farm_id;

        ImageView store;
        ImageView animal;

        TextView cast;
        TextView explain;

        ImageView menuButton;

        View itemview;

        int type;

        public ViewHolder(View itemView, int viewType) {
            super(itemView);
            if (viewType == HEADER) {
                point = (TextView) itemView.findViewById(R.id.point);
                level = (TextView) itemView.findViewById(R.id.level);
                farm_id = (TextView) itemView.findViewById(R.id.farm_id);
                store = (ImageView) itemView.findViewById(R.id.store);
                animal= (ImageView) itemView.findViewById(R.id.animal);

                cast = (TextView) itemView.findViewById(R.id.cast_text);
                explain = (TextView) itemView.findViewById(R.id.explain);

                menuButton = (ImageView) itemView.findViewById(R.id.menuButton);

                itemview = itemView;

                type = HEADER;
            } else if (viewType == ITEM) {
                textView = (TextView) itemView.findViewById(
                        R.id.card_text);
                imageView = (ImageView) itemView.findViewById(R.id.card_img);
                itemview = itemView;

                type = ITEM;

            }

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.status, parent, false);
            ViewHolder VM = new ViewHolder(v, viewType);

            return VM;
        } else if (viewType == ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_row, parent, false);
            ViewHolder VM = new ViewHolder(v, viewType);

            return VM;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        final ViewHolder h = ((ViewHolder) holder);

        if (h.type == HEADER) {
            Typefaces.setFont("BM-JUA.ttf", h.cast, ((ViewHolder) holder).itemview);
            Typefaces.setFont("KoPubDotumLight.ttf", h.explain, ((ViewHolder) holder).itemview);
            Typefaces.setFont("BM-JUA.ttf", h.point, ((ViewHolder) holder).itemview);
            Typefaces.setFont("BM-JUA.ttf", h.level, ((ViewHolder) holder).itemview);
            Typefaces.setFont("BM-JUA.ttf", h.farm_id, ((ViewHolder) holder).itemview);

            h.point.setText(point);
            h.level.setText("Lv."+ level);
            h.farm_id.setText(farm_name);
            h.animal.setImageResource(animal);

            h.store.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), StoreActivity.class);
                    (v.getContext()).startActivity(i);
                }
            });

            h.menuButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity)activity).open();
                }
            });

        } else if (h.type == ITEM) {
            Typefaces.setFont("KoPubDotumLight.ttf", h.textView, ((ViewHolder) holder).itemview);

            h.textView.setText(stringList.get(position));
            h.imageView.setImageResource(imageList.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return 0;
        return 1;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }


}
