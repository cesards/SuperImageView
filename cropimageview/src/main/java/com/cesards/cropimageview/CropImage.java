package com.cesards.cropimageview;

import android.support.annotation.NonNull;

public abstract class CropImage {

    protected final @NonNull CropImageView imageView;

    public CropImage(@NonNull CropImageView imageView) {
        this.imageView = imageView;
    }
}
