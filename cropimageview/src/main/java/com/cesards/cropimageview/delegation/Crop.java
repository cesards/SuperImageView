package com.cesards.cropimageview.delegation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public interface Crop {
    void initialize(@NonNull Context context, @Nullable AttributeSet attributeSet);
    boolean delegateSetFrame(boolean setFrameResult);
    void delegateSetImageBitmap(Bitmap bitmap);
    void delegateSetImageDrawable(Drawable drawable);
    void delegateSetImageResource(int resId);
}
