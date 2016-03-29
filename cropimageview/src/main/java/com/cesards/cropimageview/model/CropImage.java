
/*
 * Copyright (c) 2016 César Díez Sánchez
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.cesards.cropimageview.model;

import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import com.cesards.cropimageview.CropImageView;

public abstract class CropImage implements Transformation {

  @NonNull final CropImageView cropImageView;

  public CropImage(@NonNull CropImageView cropImageView) {
    this.cropImageView = cropImageView;
  }

  public void computeImageTransformation() {
    final int viewWidth = cropImageView.getWidth() - cropImageView.getPaddingLeft()
        - cropImageView.getPaddingRight();
    final int viewHeight = cropImageView.getHeight() - cropImageView.getPaddingTop()
        - cropImageView.getPaddingBottom();
    final Drawable drawable = cropImageView.getDrawable();

    final int cropType = cropImageView.getCropType();
    if (cropType != CropType.NONE && viewHeight > 0 && viewWidth > 0 && drawable != null) {
      Matrix matrix = getMatrix();

      final int drawableWidth = drawable.getIntrinsicWidth();
      final int drawableHeight = drawable.getIntrinsicHeight();

      final float yScale = (float) viewHeight / (float) drawableHeight;
      final float xScale = (float) viewWidth / (float) drawableWidth;
      final float scale = xScale > yScale ? xScale : yScale;
      matrix.setScale(scale, scale); // Same as doing matrix.reset() and matrix.preScale(...)

      final boolean verticalImageMode = xScale > yScale;

      final float postDrawableWidth = drawableWidth * scale;
      final float xTranslation = getXTranslation(
          cropType,
          viewWidth,
          postDrawableWidth,
          verticalImageMode
      );
      final float postDrawabeHeigth = drawableHeight * scale;
      final float yTranslation = getYTranslation(
          cropType,
          viewHeight,
          postDrawabeHeigth,
          verticalImageMode
      );

      matrix.postTranslate(xTranslation, yTranslation);
      cropImageView.setImageMatrix(matrix);
    }
  }

  private float getYTranslation(
      @CropType int cropType,
      int viewHeight,
      float postDrawabeHeigth,
      boolean verticalImageMode
  ) {
    if (verticalImageMode && cropType == CropType.BOTTOM) {
      return viewHeight - postDrawabeHeigth;
    } else if (verticalImageMode && (cropType == CropType.LEFT || cropType == CropType.RIGHT)) {
      return (viewHeight - postDrawabeHeigth) / 2;
    }

    // All other cases we don't need to translate
    return 0f;
  }

  private float getXTranslation(
      @CropType int cropType,
      int viewWidth,
      float postDrawableWidth,
      boolean verticalImageMode
  ) {
    if (!verticalImageMode && cropType == CropType.RIGHT) {
      return viewWidth - postDrawableWidth;
    } else if (!verticalImageMode && (cropType == CropType.BOTTOM || cropType == CropType.TOP)) {
      return (viewWidth - postDrawableWidth) / 2;
    }

    // All other cases we don't need to translate
    return 0f;
  }
}
