package com.example.yum.rudefarm;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class StoreCardSpacesItemDecoration extends RecyclerView.ItemDecoration {

    private int spanCount;
    private int spacing;
    private boolean includeEdge;
    private int topSpacing;

    public StoreCardSpacesItemDecoration(int spanCount, int spacing, int topSpacing, boolean includeEdge) {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
        this.topSpacing = topSpacing;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildPosition(view); // item position
        int column = position % spanCount; // item column
        if (includeEdge) {
            outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
            outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

            if (position < spanCount) { // top edge
                outRect.top = topSpacing;
            }

        } else {
            if (position < spanCount)
                outRect.top = topSpacing;
            outRect.left = spacing;
            outRect.right = spacing;
            outRect.bottom = spacing;
            //outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
            //outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            if (position >= spanCount) {
                outRect.top = spacing; // item top
            }
        }
    }
}