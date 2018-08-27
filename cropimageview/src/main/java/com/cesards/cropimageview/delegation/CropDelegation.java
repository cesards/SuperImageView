package com.cesards.cropimageview.delegation;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.cesards.cropimageview.R;
import com.cesards.cropimageview.model.CropImage;
import com.cesards.cropimageview.model.CropImageFactory;
import com.cesards.cropimageview.model.CropType;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class CropDelegation implements Crop {

    @NonNull private final ImageView imageView;
    @Nullable private CropImage cropImage;
    private int cropType = CropType.NONE;

    public CropDelegation(@NonNull ImageView imageView) {
        this.imageView = imageView;
    }

    /**
     * Set crop type for the {@link ImageView}
     *
     * @param cropType A {@link CropType} desired scaling mode.
     */
    public void setCropType(@CropType int cropType) {
        this.cropType = cropType;

        imageView.setWillNotCacheDrawing(false);

        imageView.requestLayout();
        imageView.invalidate();
    }

    @Override
    public void initialize(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        if (attributeSet != null) {
            parseAttributes(attributeSet);
        }
        setup();
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
    public boolean delegateSetFrame(boolean setFrameResult) {
        if (!imageView.isInEditMode() && cropImage != null && imageView.getDrawable() != null) {
            cropImage.computeImageTransformation();
        }
        return setFrameResult;
    }

    @Override
    public void delegateSetImageBitmap(Bitmap bitmap) {
        setup();
    }

    @Override
    public void delegateSetImageDrawable(Drawable drawable) {
        setup();
    }

    @Override
    public void delegateSetImageResource(int resId) {
        setup();
    }

    private void setup() {
        imageView.setScaleType(ImageView.ScaleType.MATRIX);

        if (imageView.getDrawable() != null) {
            cropImage = new CropImageFactory().getCropImage(imageView, cropType);
        }
    }

    private void parseAttributes(AttributeSet attrs) {
        final TypedArray a = imageView.getContext().obtainStyledAttributes(attrs, R.styleable.civ_CropImageView);
        cropType = a.getInt(R.styleable.civ_CropImageView_civ_crop, CropType.NONE);
        a.recycle();
    }
}
