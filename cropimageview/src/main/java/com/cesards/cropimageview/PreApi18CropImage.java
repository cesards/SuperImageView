package com.cesards.cropimageview;

import android.graphics.Matrix;

public class PreApi18CropImage extends CropImage implements ImageMaths {

  private Matrix matrix;

  public PreApi18CropImage(CropImageView imageView) {
    super(imageView);

    init(imageView);
  }

  private void init(CropImageView imageView) {
    if (imageView.getCropType() != CropImageView.CropType.NONE) {
      matrix = new Matrix();
    }
  }

  @Override
  public Matrix getMatrix() {
    return matrix == null ? imageView.getImageMatrix() : matrix;
  }
}
