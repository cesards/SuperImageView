/*
 * Copyright 2015 Cesar Diez Sanchez
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cesards.cropimageview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;
import java.util.HashMap;
import java.util.Map;

public class CropImageView extends ImageView {

  private ImageMaths imageMaths;
  private CropType cropType = CropType.NONE;

  public CropImageView(Context context) {
    super(context);

    initImageView();
  }

  public CropImageView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public CropImageView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);

    parseAttributes(attrs);
    initImageView();
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public CropImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);

    parseAttributes(attrs);
    initImageView();
  }

  /**
   * Set crop type for the {@link ImageView}
   *
   * @param cropType A {@link CropType} desired scaling mode.
   */
  public void setCropType(CropType cropType) {
    if (cropType == null) {
      throw new NullPointerException();
    }

    if (this.cropType != cropType) {
      this.cropType = cropType;

      setWillNotCacheDrawing(false);

      requestLayout();
      invalidate();
    }
  }

  /**
   * Return the current crop type in use by this ImageView.
   *
   * @return a {@link CropType} in use by this ImageView
   */
  public CropType getCropType() {
    return cropType;
  }

  private void initImageView() {
    imageMaths = new ImageMathsFactory().getImageMaths(this);
  }

  private void parseAttributes(AttributeSet attrs) {
    final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CropImageView);

    final int crop = a.getInt(R.styleable.CropImageView_crop, CropType.NONE.getCrop());
    if (crop >= 0) {
      setScaleType(ScaleType.MATRIX);
      cropType = CropType.get(crop);
    }
    a.recycle();
  }

  @Override
  protected boolean setFrame(int l, int t, int r, int b) {
    final boolean changed = super.setFrame(l, t, r, b);
    if (!isInEditMode()) {
      computeImageMatrix();
    }
    return changed;
  }

  private void computeImageMatrix() {
    final int viewWidth = getWidth() - getPaddingLeft() - getPaddingRight();
    final int viewHeight = getHeight() - getPaddingTop() - getPaddingBottom();

    if (cropType != CropType.NONE && viewHeight > 0 && viewWidth > 0) {
      final Matrix matrix = imageMaths.getMatrix();

      final int drawableWidth = getDrawable().getIntrinsicWidth();
      final int drawableHeight = getDrawable().getIntrinsicHeight();

      final float scaleY = (float) viewHeight / (float) drawableHeight;
      final float scaleX = (float) viewWidth / (float) drawableWidth;
      final float scale = scaleX > scaleY ? scaleX : scaleY;
      matrix.setScale(scale, scale); // Same as doing matrix.reset() and matrix.preScale(...)

      final boolean verticalImageMode = scaleX > scaleY;

      final float postDrawableWidth = drawableWidth * scale;
      final float xTranslation = getXTranslation(cropType, viewWidth, postDrawableWidth, verticalImageMode);
      final float postDrawabeHeigth = drawableHeight * scale;
      final float yTranslation = getYTranslation(cropType, viewHeight, postDrawabeHeigth, verticalImageMode);

      matrix.postTranslate(xTranslation, yTranslation);
      setImageMatrix(matrix);
    }
  }

  private float getYTranslation(CropType cropType, int viewHeight, float postDrawabeHeigth, boolean verticalImageMode) {
    if (verticalImageMode) {
      switch (cropType) {
        case CENTER_BOTTOM:
        case LEFT_BOTTOM:
        case RIGHT_BOTTOM:
          return viewHeight - postDrawabeHeigth;
        case LEFT_CENTER:
        case RIGHT_CENTER:
          // View in the middle of the screen
          return (viewHeight - postDrawabeHeigth) / 2f;
      }
    }

    // All other cases we don't need to translate
    return 0;
  }

  private float getXTranslation(CropType cropType, int viewWidth, float postDrawableWidth, boolean verticalImageMode) {
    if (!verticalImageMode) {
      switch (cropType) {
        case RIGHT_TOP:
        case RIGHT_CENTER:
        case RIGHT_BOTTOM:
          return viewWidth - postDrawableWidth;
        case CENTER_TOP:
        case CENTER_BOTTOM:
          // View in the middle of the screen
          return (viewWidth - postDrawableWidth) / 2f;
      }
    }
    // All other cases we don't need to translate
    return 0;
  }

  /**
   * Options for cropping the bounds of an image to the bounds of this view.
   */
  public enum CropType {
    NONE(-1),
    LEFT_TOP(0),
    LEFT_CENTER(1),
    LEFT_BOTTOM(2),
    RIGHT_TOP(3),
    RIGHT_CENTER(4),
    RIGHT_BOTTOM(5),
    CENTER_TOP(6),
    CENTER_BOTTOM(7);

    final int cropType;

    // Reverse-lookup map for getting a day from an abbreviation
    private static final Map<Integer, CropType> codeLookup = new HashMap<>();

    static {
      for (CropType ft : CropType.values()) {
        codeLookup.put(ft.getCrop(), ft);
      }
    }

    CropType(int ct) {
      cropType = ct;
    }

    public int getCrop() {
      return cropType;
    }

    public static CropType get(int number) {
      return codeLookup.get(number);
    }
  }
}
