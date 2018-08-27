package com.cesards.cropimageview.model;

import android.graphics.Matrix;
import android.widget.ImageView;

import com.cesards.cropimageview.CropImageView;

public class API18Image extends CropImage {

  API18Image(ImageView imageView, @CropType int cropType) {
    super(imageView, cropType);
  }

  @Override
  public Matrix getMatrix() {
    return imageView.getImageMatrix();
  }
}