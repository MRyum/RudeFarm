package com.example.yum.rudefarm;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by YUM on 2015-09-30.
 */
public class StoreAdapter extends RecyclerView.Adapter {

    List<String> titleList;
    List<Integer> goodsList;
    List<String> prizeList;

    public StoreAdapter(List titleList, List goodsList, List prizeList) {
        this.titleList = titleList;
        this.goodsList = goodsList;
        this.prizeList = prizeList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView goods;
        TextView prize;

        View itemview;

        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.item_title);
            goods = (ImageView) view.findViewById(R.id.goods);
            prize = (TextView) view.findViewById(R.id.prize);

            itemview = view;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_card_item, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder h = ((ViewHolder) holder);

        Typefaces.setFont("KoPubDotumLight.ttf", h.title, h.itemview);
        Typefaces.setFont("KoPubDotumLight.ttf", h.prize, h.itemview);

        h.title.setText(titleList.get(position));
        h.goods.setImageResource(goodsList.get(position));
        BitmapDrawable bitmapDrawable = (BitmapDrawable) h.goods.getDrawable();
        final Bitmap image = bitmapDrawable.getBitmap();

        h.prize.setText(prizeList.get(position));

        h.itemview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Click", String.valueOf(position));
                Bitmap newImage = Bitmap.createScaledBitmap(image, 320, 600, false);
                Dialog dialog = new DetailDialog(v.getContext(), h.title.getText().toString(),
                        h.prize.getText().toString(), position, newImage);
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return titleList.size();
    }
}
