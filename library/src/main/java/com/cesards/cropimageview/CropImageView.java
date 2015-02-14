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

/**
 * @author cesards
 */
public class CropImageView extends ImageView {

  private CropType cropType = CropType.NONE;

  public CropImageView(Context context) {
    super(context);
  }

  public CropImageView(Context context, AttributeSet attrs) {
    super(context, attrs);
    parseAttributes(attrs);
  }

  public CropImageView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    parseAttributes(attrs);
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP) public CropImageView(Context context, AttributeSet attrs, int defStyleAttr,
      int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    parseAttributes(attrs);
  }

  public void setCropType(CropType cropType) {
    this.cropType = cropType;
  }

  public CropType getCropType() {
    return cropType;
  }

  private void parseAttributes(AttributeSet attrs) {
    TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CropImageView);

    final int crop = a.getInt(R.styleable.CropImageView_crop, CropType.NONE.getCrop());
    if (crop >= 0) {
      setScaleType(ScaleType.MATRIX);
      cropType = CropType.get(crop);
    }
    a.recycle();
  }

  @Override
  protected boolean setFrame(int l, int t, int r, int b) {
    if (!isInEditMode()) {
      computeImageMatrix();
    }
    return super.setFrame(l, t, r, b);
  }

  @Override
  protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    super.onLayout(changed, left, top, right, bottom);
    if (!isInEditMode()) {
      computeImageMatrix();
    }
  }

  private void computeImageMatrix() {
    final int viewWidth = getWidth() - getPaddingLeft() - getPaddingRight();
    final int viewHeight = getHeight() - getPaddingTop() - getPaddingBottom();

    if (cropType != CropType.NONE && viewHeight > 0 && viewWidth > 0) {
      final Matrix matrix = getImageMatrix();

      int drawableWidth = getDrawable().getIntrinsicWidth();
      int drawableHeight = getDrawable().getIntrinsicHeight();

      final float scaleY = (float) viewHeight / (float) drawableHeight;
      final float scaleX = (float) viewWidth / (float) drawableWidth;
      final float scale = scaleX > scaleY ? scaleX : scaleY;
      matrix.setScale(scale, scale); // Same as doing matrix.reset() and matrix.preScale(...)

      final boolean verticalImageMode = scaleX > scaleY;

      final float xTranslation = getXTranslation(cropType, scale, viewWidth, drawableWidth, verticalImageMode);
      final float yTranslation = getYTranslation(cropType, scale, viewHeight, drawableHeight, verticalImageMode);

      matrix.postTranslate(xTranslation, yTranslation);
      setImageMatrix(matrix);
    }
  }

  private float getYTranslation(CropType cropType, float scale, int viewHeight, int drawableHeight,
      boolean verticalImageMode) {
    if (verticalImageMode) {
      switch (cropType) {
        case CENTER_BOTTOM:
        case LEFT_BOTTOM:
        case RIGHT_BOTTOM:
          return viewHeight - (scale * drawableHeight);
        case LEFT_CENTER:
        case RIGHT_CENTER:
          // View in the middle of the screen
          return (float) (((float) viewHeight / 2.0) - ((float) drawableHeight / 2.0));
      }
    }

    // All other cases we don't need to translate
    return 0;
  }

  private float getXTranslation(CropType cropType, float scale, int viewWidth, int drawableWidth,
      boolean verticalImageMode) {
    if (!verticalImageMode) {
      switch (cropType) {
        case RIGHT_TOP:
        case RIGHT_CENTER:
        case RIGHT_BOTTOM:
          return viewWidth - (drawableWidth * scale);
        case CENTER_TOP:
        case CENTER_BOTTOM:
          // View in the middle of the screen
          return (float) (((float) viewWidth / 2.0) + ((float) drawableWidth / 2.0));
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
