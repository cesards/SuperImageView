package com.cesards.cropimageview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public interface LifecycleCroppedImage {
    void setupCrop(@NonNull Context context, @Nullable AttributeSet attributeSet);

    /**
     * Call super first and return the result
     * */
    void applyTransformation();
    void delegateSetScaleType(ImageView.ScaleType scaleType);
}
