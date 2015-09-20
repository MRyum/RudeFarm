package com.example.yum.rudefarm;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;

import java.util.List;


public class MainAdapter extends UltimateViewAdapter<MainAdapter.ViewHolder> {
    private List<String> stringList;
    private List<Integer> imageList;
    private String farmId_data;
    private String level_data;

    private View header;

    public MainAdapter(List<String> stringList, List<Integer> imageList, String farmId, int level) {
        this.stringList = stringList;
        this.imageList = imageList;
        this.farmId_data = farmId;
        this.level_data = "Lv." + String.valueOf(level);
    }

    public class ViewHolder extends UltimateRecyclerviewViewHolder {

        TextView textView;
        ImageView imageView;
        View itemview;

        TextView cast;
        TextView explain;

        public ViewHolder(View itemView, boolean isItem) {
            super(itemView);
            if (isItem) {
                textView = (TextView) itemView.findViewById(
                        R.id.card_text);
                imageView = (ImageView) itemView.findViewById(R.id.card_img);
                itemview = itemView;
            }

        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_row, parent, false);
        ViewHolder vh = new ViewHolder(v, true);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (position < getItemCount() && (customHeaderView != null ? position <= stringList.size() : position < stringList.size()) && (customHeaderView != null ? position > 0 : true)) {
            try {
                ((ViewHolder) holder).textView.setText(stringList.get(customHeaderView != null ? position - 1 : position));
                ((ViewHolder) holder).imageView.setImageResource(imageList.get(customHeaderView != null ? position - 1 : position));
            } catch (Exception e) {
                Log.e("insert failed", e.toString());
            }

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup viewGroup) {
//        View view = LayoutInflater.from(viewGroup.getContext())
//                .inflate(R.layout.fir_item, viewGroup, false);
//        return new RecyclerView.ViewHolder(view) {
//        };
        return null;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

//        TextView cast = (TextView) viewHolder.itemView.findViewById(R.id.cast_text);
//        Typefaces.setFont("BM-JOA.ttf", cast, viewHolder.itemView);
//        TextView explain = (TextView) viewHolder.itemView.findViewById(R.id.explain);
//        Typefaces.setFont("KoPubDotumLight.ttf", explain, viewHolder.itemView);
////        viewHolder.itemView.setBackgroundColor(Color.parseColor("#AA70DB93"));
//        viewHolder.itemView.setBackgroundColor(Color.parseColor("#AAffffff"));
    }


    @Override
    public int getAdapterItemCount() {
        return stringList.size();
    }

    @Override
    public ViewHolder getViewHolder(View view) {
        return new ViewHolder(view, true);
    }


    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        swapPositions(fromPosition, toPosition);
//        notifyItemMoved(fromPosition, toPosition);
        super.onItemMove(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        remove(position);
        // notifyItemRemoved(position);
//        notifyDataSetChanged();
        super.onItemDismiss(position);
    }

    public void setOnDragStartListener(OnStartDragListener dragStartListener) {
        mDragStartListener = dragStartListener;

    }

    public void insert(String string, int position) {
        try {
            insert(stringList, string, position);
        } catch (Exception e) {
            Log.e("Append error", e.toString());
        }
    }

    public void insert(String string, int image_res, int position) {
        try {
            insert(stringList, string, position);
            insert(imageList, image_res, position);
        } catch (Exception e) {
            Log.e("Append error", e.toString());
        }
    }

    public void remove(int position) {
        remove(stringList, position);
    }

    public void clear() {
        clear(stringList);
    }

    @Override
    public void toggleSelection(int pos) {
        super.toggleSelection(pos);
    }

    @Override
    public void setSelected(int pos) {
        super.setSelected(pos);
    }

    @Override
    public void clearSelection(int pos) {
        super.clearSelection(pos);
    }


    public void swapPositions(int from, int to) {
        swapPositions(stringList, from, to);
    }


    @Override
    public long generateHeaderId(int position) {
        // URLogs.d("position--" + position + "   " + getItem(position));
        if (getItem(position).length() > 0)
            return getItem(position).charAt(0);
        else return -1;
    }


    public String getItem(int position) {
        if (customHeaderView != null)
            position--;
        if (position < stringList.size())
            return stringList.get(position);
        else return "";
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return 0;
        return 1;
    }

    public boolean isPositionHeader(int position) {
        return position == 0;
    }
}