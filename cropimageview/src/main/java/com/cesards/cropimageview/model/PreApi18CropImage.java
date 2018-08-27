package com.cesards.cropimageview.model;

import android.graphics.Matrix;
import android.widget.ImageView;

import com.cesards.cropimageview.CropImageView;

public final class PreApi18CropImage extends CropImage {

  private Matrix matrix;

  PreApi18CropImage(final ImageView imageView, final @CropType int cropType) {
    super(imageView, cropType);
    init(cropType);
  }

  private void init(@CropType int cropType) {
    if (cropType != CropType.NONE) {
      matrix = new Matrix();
    }
  }

  @Override
  public Matrix getMatrix() {
    return matrix == null ? imageView.getImageMatrix() : matrix;
  }
}
