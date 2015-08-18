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
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.Map;

import static com.cesards.cropimageview.RoundedCornerDrawable.*;

/**
 * @author cesards
 */
public class CropImageView extends ImageView {

  private CropType cropType = CropType.NONE;
  private final int[] cornerRadius = new int[4];
  private boolean specificCornerRadiusSet = true;

  public CropImageView(Context context) {
    super(context);
  }

  public CropImageView(Context context, AttributeSet attrs) {
    super(context, attrs);
    this.parseAttributes(attrs);
  }

  public CropImageView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    this.parseAttributes(attrs);
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public CropImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    this.parseAttributes(attrs);
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
    return this.cropType;
  }

  /**
   * @return the largest corner radius.
   */
  public float getCornerRadius() {
    return getMaxCornerRadius();
  }

  /**
   * @return the largest corner radius.
   */
  public int getMaxCornerRadius() {
    int maxRadius = DEFAULT_RADIUS;
    for (int i = cornerRadius.length - 1; i >= 0; i--) {
      maxRadius = Math.max(cornerRadius[i], maxRadius);
    }

    return maxRadius;
  }

  /**
   * Get the corner radius of a specified corner.
   *
   * @param corner the corner.
   * @return the radius.
   */
  public int getCornerRadius(@Corner int corner) {
    return cornerRadius[corner];
  }

  /**
   * Set the corner radii of all corners in px.
   *
   * @param radius the radius to set.
   */
  public void setCornerRadius(int radius) {
    setCornerRadius(radius, radius, radius, radius);
  }

  /**
   * Set the corner radius of a specific corner from a dimension resource id.
   *
   * @param corner the corner to set.
   * @param resId the dimension resource id of the corner radius.
   */
  public void setCornerRadiusDimen(@Corner int corner, @DimenRes int resId) {
    setCornerRadius(corner, getResources().getDimensionPixelSize(resId));
  }

  /**
   * Set all the corner radii from a dimension resource id.
   *
   * @param resId dimension resource id of radii.
   */
  public void setCornerRadiusDimen(@DimenRes int resId) {
    final int radius = getResources().getDimensionPixelOffset(resId);
    setCornerRadius(radius, radius, radius, radius);
  }

  /**
   * Set the corner radius of a specific corner in px.
   *
   * @param corner the corner to set.
   * @param radius the corner radius to set in px.
   */
  public void setCornerRadius(@Corner int corner, int radius) {
    if (radius < DEFAULT_RADIUS) throw new IllegalArgumentException("Non negative radius supported");

    if (cornerRadius[corner] == radius) return;

    cornerRadius[corner] = radius;

    setDrawableCorners(getDrawable());
    invalidate();
  }

  /**
   * Set the corner radii of each corner individually. Currently only one unique nonzero value is
   * supported.
   *
   * @param topLeft radius of the top left corner in px.
   * @param topRight radius of the top right corner in px.
   * @param bottomRight radius of the bottom right corner in px.
   * @param bottomLeft radius of the bottom left corner in px.
   */
  public void setCornerRadius(int topLeft, int topRight, int bottomLeft, int bottomRight) {
    if (topLeft < DEFAULT_RADIUS || topRight < DEFAULT_RADIUS || bottomLeft < DEFAULT_RADIUS || bottomRight < DEFAULT_RADIUS) throw new IllegalArgumentException("Non negative radius supported");

    if (cornerRadius[CORNER_TOP_LEFT] == topLeft && cornerRadius[CORNER_TOP_RIGHT] == topRight && cornerRadius[CORNER_BOTTOM_RIGHT] == bottomRight && cornerRadius[CORNER_BOTTOM_LEFT] == bottomLeft) return;

    cornerRadius[CORNER_TOP_LEFT] = topLeft;
    cornerRadius[CORNER_TOP_RIGHT] = topRight;
    cornerRadius[CORNER_BOTTOM_LEFT] = bottomLeft;
    cornerRadius[CORNER_BOTTOM_RIGHT] = bottomRight;

    setDrawableCorners(getDrawable());
    invalidate();
  }

  @Override
  public void setImageDrawable(Drawable drawable) {
    final Drawable roundedDrawable = RoundedCornerDrawable.fromDrawable(drawable);
    setDrawableCorners(roundedDrawable);

    super.setImageDrawable(roundedDrawable);
  }

  @Override
  public void setImageBitmap(Bitmap bm) {
    final Drawable roundedDrawable = RoundedCornerDrawable.fromBitmap(bm);
    setDrawableCorners(roundedDrawable);

    super.setImageDrawable(roundedDrawable);
  }

  // TODO: Not sure if this is necessary
  @Override
  protected void drawableStateChanged() {
    super.drawableStateChanged();

    invalidate();
  }

  @Override
  protected boolean setFrame(int l, int t, int r, int b) {
    final boolean changed = super.setFrame(l, t, r, b);
    if (!isInEditMode()) {
      computeImageMatrix();
    }

    return changed;
  }

  private void parseAttributes(AttributeSet attrs) {
    TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CropImageView);

    final int crop = a.getInt(R.styleable.CropImageView_crop, CropType.NONE.getCrop());
    if (crop >= 0) {
      setScaleType(ScaleType.MATRIX);
      this.cropType = CropType.get(crop);
    }

    final int cornerRadiusOverride = a.getDimensionPixelSize(R.styleable.CropImageView_cornerRadius, DEFAULT_RADIUS);

    cornerRadius[CORNER_TOP_LEFT] = a.getDimensionPixelSize(R.styleable.CropImageView_cornerRadiusTopLeft, DEFAULT_RADIUS);
    cornerRadius[CORNER_TOP_RIGHT] = a.getDimensionPixelSize(R.styleable.CropImageView_cornerRadiusTopRight, DEFAULT_RADIUS);
    cornerRadius[CORNER_BOTTOM_LEFT] = a.getDimensionPixelSize(R.styleable.CropImageView_cornerRadiusBottomLeft, DEFAULT_RADIUS);
    cornerRadius[CORNER_BOTTOM_RIGHT] = a.getDimensionPixelSize(R.styleable.CropImageView_cornerRadiusBottomRight, DEFAULT_RADIUS);

    for (int i = 0; i < cornerRadius.length && specificCornerRadiusSet; i++) {
      specificCornerRadiusSet = cornerRadius[i] > DEFAULT_RADIUS;
    }

    if (cornerRadiusOverride > DEFAULT_RADIUS && !specificCornerRadiusSet) {
      cornerRadius[CORNER_TOP_LEFT] = cornerRadiusOverride;
      cornerRadius[CORNER_TOP_RIGHT] = cornerRadiusOverride;
      cornerRadius[CORNER_BOTTOM_RIGHT] = cornerRadiusOverride;
      cornerRadius[CORNER_BOTTOM_LEFT] = cornerRadiusOverride;

      specificCornerRadiusSet = true;
    }

    a.recycle();
  }

  private void setDrawableCorners(Drawable drawable) {
    if (drawable == null) return;

    if (drawable instanceof RoundedCornerDrawable && specificCornerRadiusSet) {
      ((RoundedCornerDrawable) drawable).setCornerRadius(cornerRadius[CORNER_TOP_LEFT], cornerRadius[CORNER_TOP_RIGHT], cornerRadius[CORNER_BOTTOM_LEFT], cornerRadius[CORNER_BOTTOM_RIGHT]);
    } else if (drawable instanceof LayerDrawable) {
      // Loop through layers to and set drawable attrs
      LayerDrawable ld = ((LayerDrawable) drawable);
      for (int i = 0, layers = ld.getNumberOfLayers(); i < layers; i++) {
        setDrawableCorners(ld.getDrawable(i));
      }
    }
  }






  private void computeImageMatrix() {
    final int viewWidth = getWidth() - getPaddingLeft() - getPaddingRight();
    final int viewHeight = getHeight() - getPaddingTop() - getPaddingBottom();

    if (cropType != CropType.NONE && viewHeight > 0 && viewWidth > 0) {

      Matrix matrix = getImageMatrix();

      int drawableWidth;
      int drawableHeight;
      if (specificCornerRadiusSet && getDrawable() instanceof RoundedCornerDrawable) {
        drawableWidth = ((RoundedCornerDrawable) getDrawable()).getBitmapWidth();
        drawableHeight = ((RoundedCornerDrawable) getDrawable()).getBitmapHeight();
      } else {
        drawableWidth = getDrawable().getIntrinsicWidth();
        drawableHeight = getDrawable().getIntrinsicHeight();
      }

      final float scaleY = (float) viewHeight / (float) drawableHeight;
      final float scaleX = (float) viewWidth / (float) drawableWidth;
      final float scale = scaleX > scaleY ? scaleX : scaleY;
      matrix.setScale(scale, scale); // Same as doing matrix.reset() and matrix.preScale(...)

      final boolean verticalImageMode = scaleX > scaleY;

      final float postDrawableWidth = drawableWidth * scale;
      final float xTranslation = getXTranslation(cropType, viewWidth, postDrawableWidth, verticalImageMode);
      final float postDrawableHeight = drawableHeight * scale;
      final float yTranslation = getYTranslation(cropType, viewHeight, postDrawableHeight, verticalImageMode);

      matrix.postTranslate(xTranslation, yTranslation);

      if (specificCornerRadiusSet && getDrawable() instanceof RoundedCornerDrawable) {
        ((RoundedCornerDrawable) getDrawable()).setMatrix(matrix);
        setImageMatrix(null);
      } else {
        setImageMatrix(matrix);
      }
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
