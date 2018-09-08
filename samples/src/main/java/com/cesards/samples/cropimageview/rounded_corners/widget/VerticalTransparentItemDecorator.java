package com.cesards.samples.cropimageview.rounded_corners.widget;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class VerticalTransparentItemDecorator extends RecyclerView.ItemDecoration {

    private int space;

    public VerticalTransparentItemDecorator(int value) {
        this.space = value;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        // Skip first item in the list.
        if (parent.getChildAdapterPosition(view) != 0) {
            outRect.set(0, space, 0, 0);
        }
    }
}
