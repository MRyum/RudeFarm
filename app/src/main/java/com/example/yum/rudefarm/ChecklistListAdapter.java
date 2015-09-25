package com.example.yum.rudefarm;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ChecklistListAdapter extends RecyclerView.Adapter {

    final static int HEADER_TYPE = 0;
    final static int ITEM_TYPE = 1;

    String question;
    List<String> answerList;

    public ChecklistListAdapter(String question, List answerList) {
        this.question = new String();
        this.answerList = new ArrayList<>();

        this.question = question;
        this.answerList = answerList;
    }



    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv;
        ImageView imageView;
        View itemView;
        int type;

        public ViewHolder(View itemView, int viewType) {
            super(itemView);
            if (viewType == HEADER_TYPE) {
                tv = (TextView) itemView.findViewById(R.id.question);
                this.itemView = itemView;
                type = HEADER_TYPE;
            } else if (viewType == ITEM_TYPE) {
                tv = (TextView) itemView.findViewById(R.id.answer);
                imageView = (ImageView) itemView.findViewById(R.id.check);
                this.itemView = itemView;
                type = ITEM_TYPE;
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER_TYPE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chkl_header, parent, false);
            RecyclerView.ViewHolder viewHolder = new ViewHolder(view, HEADER_TYPE);

            return viewHolder;

        } else if (viewType == ITEM_TYPE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chkl_item, parent, false);
            RecyclerView.ViewHolder viewHolder = new ViewHolder(view, ITEM_TYPE);

            return viewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ViewHolder h = (ViewHolder) holder;
        if (h.type == HEADER_TYPE) {
            Typefaces.setFont("KoPubDotumMedium.ttf", h.tv, h.itemView);
            h.tv.setText(question);

        } else if (h.type == ITEM_TYPE) {
            Typefaces.setFont("KoPubDotumLight.ttf", h.tv, h.itemView);
            h.tv.setText(answerList.get(position));
            h.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    h.imageView.setImageResource(R.mipmap.check);

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return answerList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return 0;
        return 1;
    }
}
