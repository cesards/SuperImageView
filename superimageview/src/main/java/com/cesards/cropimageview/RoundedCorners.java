package com.cesards.cropimageview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public interface RoundedCorners {
    void setup(@NonNull Context context, @Nullable AttributeSet attributeSet);
    void onLayoutChanged();
    void onDraw(Canvas canvas);

    interface View {
        @NonNull Context getContext();
        int getWidth();
        int getHeight();
        void postInvalidate();
        void setLayerType(int layerType, @Nullable Paint paint);
        void setWillNotDraw(boolean willNotDraw);
    }
}
