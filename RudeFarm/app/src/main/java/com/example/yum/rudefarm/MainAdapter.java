package com.example.yum.rudefarm;


import android.support.v7.widget.RecyclerView;
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

    public MainAdapter(List data, List Image) {
        stringList = data;
        imageList = Image;
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


        View itemview;


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
                itemview = itemView;
            } else if (viewType == ITEM) {
                textView = (TextView) itemView.findViewById(
                        R.id.card_text);
                imageView = (ImageView) itemView.findViewById(R.id.card_img);
                itemview = itemView;
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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }
}
