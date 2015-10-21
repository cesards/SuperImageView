package com.cesards.cropimageview;

import android.graphics.Matrix;
import android.widget.ImageView;

public class API18Image extends CropImage implements ImageMaths {

    public API18Image(CropImageView imageView) {
        super(imageView);
    }

    @Override
    public Matrix getMatrix() {
        return imageView.getImageMatrix();
    }
}