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

package com.cesards.samples.cropimageview.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.cesards.cropimageview.model.CropType;
import com.cesards.samples.cropimageview.R;

public class CroppedAreaImageView extends ImageView {

  private int parentWidth;
  private int parentHeight;
  @CropType private int cropType = CropType.NONE;
  private final Paint paint = new Paint();

  public CroppedAreaImageView(Context context) {
    super(context);
    onFinishInflate();
  }

  public CroppedAreaImageView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public CroppedAreaImageView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();

    paint.setColor(ContextCompat.getColor(getContext(), R.color.icons));
    paint.setStyle(Paint.Style.STROKE);
    paint.setStrokeWidth(4);
  }

  @Override
  protected boolean setFrame(int l, int t, int r, int b) {
    return super.setFrame(l, t, r, b);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    final Drawable drawable = getDrawable();
    if (parentWidth > 0 && parentHeight > 0 && cropType != CropType.NONE && drawable != null) {
      drawCroppedArea(canvas, drawable);
    }
  }

  /**
   * This method will be called from its parent during onLayout process. We don't need to call
   * invalidate().
   */
  void setParentBounds(int parentWidth, int parentHeight) {
    this.parentWidth = parentWidth;
    this.parentHeight = parentHeight;
  }

  /**
   * We need the view go through onDraw() again so we need to invalidate the entire view.
   */
  public void setCropType(@CropType int cropType) {
    this.cropType = cropType;
    invalidate();
  }

  /**
   * Image is centered by default. ScaleType.FIT_CENTER see {ImageView.setScaleType}
   */
  public void drawCroppedArea(Canvas canvas, Drawable drawable) {
    final int viewHeight = getHeight();
    final int viewWidth = getWidth();
    final int drawableWidth = drawable.getIntrinsicWidth();
    final int drawableHeight = drawable.getIntrinsicHeight();

    // Calculating drawable width and height after ScaleType.FIT_CENTER
    final float fittedXScale = (float) viewHeight / (float) drawableHeight;
    final float fittedYScale = (float) viewWidth / (float) drawableWidth;
    final float fittedDrawableScale = fittedXScale > fittedYScale ? fittedYScale : fittedXScale;
    final float fittedDrawableWidth = drawableWidth * fittedDrawableScale;
    final float fittedDrawableHeight = drawableHeight * fittedDrawableScale;

    // Calculating mirrored cropped drawable width and height
    final float croppedYScale = (float) parentHeight / (float) drawableHeight;
    final float croppedXScale = (float) parentWidth / (float) drawableWidth;
    final float croppedDrawableScale = croppedXScale > croppedYScale ? croppedXScale
        : croppedYScale;
    final float postCroppedDrawableWidth = drawableWidth * croppedDrawableScale;
    final float postCroppedDrawabeHeight = drawableHeight * croppedDrawableScale;
    final boolean verticalImageMode = croppedXScale > croppedYScale;

    final float xPercentCropped = (float) parentWidth / postCroppedDrawableWidth;
    final float yPercentCropped = (float) parentHeight / postCroppedDrawabeHeight;

    final float originalDrawableXTranslation = getOriginalDrawableXTranslation(
        cropType,
        parentWidth,
        postCroppedDrawableWidth,
        verticalImageMode
    );

    final float fittedDrawableXTranslation = getFittedDrawableTranslation(
        fittedDrawableWidth,
        postCroppedDrawableWidth,
        originalDrawableXTranslation
    );

    final float originalDrawableYTranslation = getOriginalDrawableYTranslation(
        cropType,
        parentHeight,
        postCroppedDrawabeHeight,
        verticalImageMode
    );

    final float fittedDrawableYTranslation = getFittedDrawableTranslation(
        fittedDrawableHeight,
        postCroppedDrawabeHeight,
        originalDrawableYTranslation
    );

    if (verticalImageMode) {
      canvas.drawRect(
          fittedDrawableXTranslation + ((viewWidth - fittedDrawableWidth) / (float) 2),
          fittedDrawableYTranslation,
          ((viewWidth - fittedDrawableWidth) / 2) + (fittedDrawableWidth * xPercentCropped),
          ((viewHeight - fittedDrawableHeight) / 2) + (fittedDrawableHeight * yPercentCropped)
              + fittedDrawableYTranslation,
          paint
      );
    } else {
      canvas.drawRect(
          fittedDrawableXTranslation,
          fittedDrawableYTranslation + ((viewHeight - fittedDrawableHeight) / (float) 2),
          ((viewWidth - fittedDrawableWidth) / 2) + (fittedDrawableWidth * xPercentCropped)
              + fittedDrawableXTranslation,
          ((viewHeight - fittedDrawableHeight) / 2) + (fittedDrawableHeight * yPercentCropped),
          paint
      );
    }
  }

  private float getFittedDrawableTranslation(
      float fittedDrawableSize,
      float postCroppedDrawabeSize,
      float originalDrawableTranslation
  ) {
    if (originalDrawableTranslation < 0) {
      return Math.abs(
          (originalDrawableTranslation / postCroppedDrawabeSize) * fittedDrawableSize
      );
    } else {
      return originalDrawableTranslation;
    }
  }

  private float getOriginalDrawableYTranslation(
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

  private float getOriginalDrawableXTranslation(
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
