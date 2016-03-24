package com.cesards.cropimageview.model;

import android.graphics.Matrix;
import com.cesards.cropimageview.CropImageView;

public class API18Image extends CropImage {

  API18Image(CropImageView imageView) {
    super(imageView);
  }

  @Override
  public Matrix getMatrix() {
    return cropImageView.getImageMatrix();
  }
}