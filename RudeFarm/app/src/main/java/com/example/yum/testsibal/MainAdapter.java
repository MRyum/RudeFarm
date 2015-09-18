package com.example.yum.testsibal;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.poliveira.parallaxrecyclerview.ParallaxRecyclerAdapter;

import java.util.List;


public class MainAdapter extends RecyclerView.Adapter {

    private static final int TYPE_FIRST = 1;
    private static final int TYPE_ITEM = 2;

    private List<String> stringList;
    private List<Integer> imageList;

    public MainAdapter(List data, List Image) {
        stringList = data;
        imageList = Image;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        final static int HEADER = 0;
        final static int ITEM = 1;


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
                

                cast = (TextView) itemView.findViewById(R.id.cast_text);
                explain = (TextView) itemView.findViewById(R.id.card_img);
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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
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
