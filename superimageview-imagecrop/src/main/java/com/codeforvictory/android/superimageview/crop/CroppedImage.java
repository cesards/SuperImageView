package com.codeforvictory.android.superimageview.crop;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.codeforvictory.android.superimageview.Crop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class CroppedImage implements Crop {

    @CropType private int cropType = CropType.NONE;
    private final ImageTransformation imageTransformation;
    private final View view;

    public CroppedImage(View view) {
        this.view = view;
        imageTransformation = new ImageTransformation(view);
    }

    /**
     * Set crop type for the {@link ImageView}
     *
     * @param cropType A {@link CropType} desired scaling mode.
     */
    public void setCropType(@CropType int cropType) {
        if (cropType == this.cropType) {
            return;
        }

        this.cropType = cropType;
        setupScaleType();
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

    //
//    public void crop(@CropType int cropType) {
//        withCropType(cropType);
//        cropImageView.requestLayout();
//        cropImageView.invalidate();
//    }
//
//

    @Override
    public void setup(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }

        final TypedArray a = context.obtainStyledAttributes(attributeSet, R.styleable.siv_ImageCrop);
        cropType = a.getInt(R.styleable.siv_SuperImageView_siv_cropImplementation, CropType.NONE);
        a.recycle();

        setupScaleType();
    }

    @Override
    public void onScaleTypeChanged(ImageView.ScaleType scaleType) {
        if (scaleType != ImageView.ScaleType.MATRIX) {
            cropType = CropType.NONE;
        }
    }

    @Override
    public void onFrameChanged() {
        if (!view.isInEditMode() && view.getDrawable() != null && cropType != CropType.NONE) {
            imageTransformation.compute(cropType);
        }
    }

    private void setupScaleType() {
        if (cropType != CropType.NONE) {
            view.setScaleType(ImageView.ScaleType.MATRIX);
        }
    }
}
