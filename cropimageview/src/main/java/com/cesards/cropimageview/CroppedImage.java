package com.cesards.cropimageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.cesards.cropimageview.image_crop.CropType;
import com.cesards.cropimageview.image_crop.ImageTransformation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class CroppedImage implements LifecycleCroppedImage {

    private final ImageView imageView;
    private final ImageTransformation imageTransformation;
    @CropType private int cropType = CropType.NONE;

    public CroppedImage(@NonNull ImageView imageView) {
        this.imageView = imageView;
        imageTransformation = new ImageTransformation(imageView);
    }

    /**
     * Set crop type for the {@link ImageView}
     *
     * @param cropType A {@link CropType} desired scaling mode.
     */
    public void withCropType(@CropType int cropType) {
        if (cropType == this.cropType) {
            return;
        }

        this.cropType = cropType;
        setupScaleType();
    }

    public void crop(@CropType int cropType) {
        withCropType(cropType);
        imageView.requestLayout();
        imageView.invalidate();
    }

    /**
     * Return the current crop type in use by this ImageView.
     *
     * @return a {@link CropType} in use by this ImageView
     */
    @CropType
    public int getCropType() {
        return cropType;
    }

    @Override
    public void setupCrop(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        if (attributeSet != null) {
            parseAttributes(attributeSet);
        }

        setupScaleType();
    }

    private void setupScaleType() {
        if (cropType != CropType.NONE) {
            imageView.setScaleType(ImageView.ScaleType.MATRIX);
        }
    }

    @Override
    public void applyTransformation() {
        if (!imageView.isInEditMode() && imageView.getDrawable() != null && cropType != CropType.NONE) {
            imageTransformation.compute(cropType);
        }
    }

    @Override
    public void delegateSetScaleType(ImageView.ScaleType scaleType) {
        if (scaleType != ImageView.ScaleType.MATRIX) {
            cropType = CropType.NONE;
        }
    }

    private void parseAttributes(AttributeSet attrs) {
        final TypedArray a = imageView.getContext().obtainStyledAttributes(attrs, R.styleable.civ_CropImageView);
        cropType = a.getInt(R.styleable.civ_CropImageView_civ_crop, CropType.NONE);
        a.recycle();
    }
}
