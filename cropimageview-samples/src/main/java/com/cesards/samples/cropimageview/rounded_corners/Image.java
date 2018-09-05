package com.cesards.samples.cropimageview.rounded_corners;

import com.cesards.cropimageview.crop.CropType;

import androidx.annotation.DrawableRes;

final class Image {

    @DrawableRes private final int drawableResource;
    private final boolean roundedCorners;
    @CropType private final int cropType;

    Image(@DrawableRes int drawableResource, boolean roundedCorners, int cropType) {
        this.drawableResource = drawableResource;
        this.roundedCorners = roundedCorners;
        this.cropType = cropType;
    }

    public int drawableResource() {
        return drawableResource;
    }

    public boolean roundedCorners() {
        return roundedCorners;
    }

    public int cropType() {
        return cropType;
    }
}