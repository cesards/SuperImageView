package com.codeforvictory.android.superimageview;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public interface Crop {
    void setup(@NonNull Context context, @Nullable AttributeSet attributeSet);
    void onScaleTypeChanged(ImageView.ScaleType scaleType);
    void onFrameChanged();

    // We shouldn't get COnfiguration because we make the interface based on the implementation, and that can't happen

    interface View {
        Drawable getDrawable();
        Matrix getImageMatrix();
        void setImageMatrix(Matrix matrix);
        int getWidth();
        int getHeight();
        int getPaddingLeft();
        int getPaddingRight();
        int getPaddingTop();
        int getPaddingBottom();
        @NonNull Context getContext();
        boolean isInEditMode();
        void setScaleType(ImageView.ScaleType scaleType);
        void requestLayout();
        void invalidate();
    }





}
