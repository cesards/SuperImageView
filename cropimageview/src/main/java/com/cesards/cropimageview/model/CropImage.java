package com.cesards.cropimageview.model;

import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import com.cesards.cropimageview.CropImageView;

public abstract class CropImage implements Transformation {

  @NonNull
  final CropImageView cropImageView;

  public CropImage(@NonNull CropImageView cropImageView) {
    this.cropImageView = cropImageView;
  }

  public void computeImageTransformation() {
    int viewWidth = cropImageView.getWidth() - cropImageView.getPaddingLeft() - cropImageView.getPaddingRight();
    int viewHeight = cropImageView.getHeight() - cropImageView.getPaddingTop() - cropImageView.getPaddingBottom();

    int cropType = cropImageView.getCropType();
    if (cropType != CropType.NONE && viewHeight > 0 && viewWidth > 0) {
      Matrix matrix = getMatrix();

      Drawable drawable = cropImageView.getDrawable();
      int drawableWidth = drawable.getIntrinsicWidth();
      int drawableHeight = drawable.getIntrinsicHeight();

      float scaleY = (float) viewHeight / (float) drawableHeight;
      float scaleX = (float) viewWidth / (float) drawableWidth;
      float scale = scaleX > scaleY ? scaleX : scaleY;
      matrix.setScale(scale, scale); // Same as doing matrix.reset() and matrix.preScale(...)

      boolean verticalImageMode = scaleX > scaleY;

      float postDrawableWidth = drawableWidth * scale;
      float xTranslation = getXTranslation(cropType, viewWidth, postDrawableWidth, verticalImageMode);
      float postDrawabeHeigth = drawableHeight * scale;
      float yTranslation = getYTranslation(cropType, viewHeight, postDrawabeHeigth, verticalImageMode);

      matrix.postTranslate(xTranslation, yTranslation);
      cropImageView.setImageMatrix(matrix);
    }
  }

  private float getYTranslation(@CropType int cropType,
                                int viewHeight,
                                float postDrawabeHeigth,
                                boolean verticalImageMode) {
    if (verticalImageMode) {
      switch (cropType) {
        case CropType.CENTER_BOTTOM:
        case CropType.LEFT_BOTTOM:
        case CropType.RIGHT_BOTTOM:
          return viewHeight - postDrawabeHeigth;
        case CropType.LEFT_CENTER:
        case CropType.RIGHT_CENTER:
          // View in the middle of the screen
          return (viewHeight - postDrawabeHeigth) / 2f;
      }
    }

    // All other cases we don't need to translate
    return 0f;
  }

  private float getXTranslation(@CropType int cropType,
                                int viewWidth,
                                float postDrawableWidth,
                                boolean verticalImageMode) {
    if (!verticalImageMode) {
      switch (cropType) {
        case CropType.RIGHT_TOP:
        case CropType.RIGHT_CENTER:
        case CropType.RIGHT_BOTTOM:
          return viewWidth - postDrawableWidth;
        case CropType.CENTER_TOP:
        case CropType.CENTER_BOTTOM:
          // View in the middle of the screen
          return (viewWidth - postDrawableWidth) / 2f;
      }
    }
    // All other cases we don't need to translate
    return 0f;
  }
}
