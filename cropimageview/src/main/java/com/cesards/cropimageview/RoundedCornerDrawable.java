package com.cesards.cropimageview;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;

import java.util.HashSet;
import java.util.Set;

public class RoundedCornerDrawable extends Drawable {

  public static final int DEFAULT_RADIUS = 0;
  public static final int CORNER_TOP_LEFT = 0;
  public static final int CORNER_TOP_RIGHT = 1;
  public static final int CORNER_BOTTOM_RIGHT = 2;
  public static final int CORNER_BOTTOM_LEFT = 3;

  public static RoundedCornerDrawable fromBitmap(Bitmap bitmap) {
    if (bitmap != null) {
      return new RoundedCornerDrawable(bitmap);
    } else {
      return null;
    }
  }

  public static Drawable fromDrawable(Drawable drawable) {
    if (drawable != null) {
      if (drawable instanceof RoundedCornerDrawable) {
        // Just return if it's already a RoundedCornerDrawable
        return drawable;
      } else if (drawable instanceof LayerDrawable) {
        LayerDrawable ld = (LayerDrawable) drawable;
        int num = ld.getNumberOfLayers();

        // Loop through layers to and change to RounderCornerDrawable if possible
        for (int i = 0; i < num; i++) {
          Drawable d = ld.getDrawable(i);
          ld.setDrawableByLayerId(ld.getId(i), fromDrawable(d));
        }
        return ld;
      }

      // Try to get a bitmap from the drawable
      Bitmap bm = drawableToBitmap(drawable);
      if (bm != null) {
        return new RoundedCornerDrawable(bm);
      }
    }
    return drawable;
  }

  private final boolean[] cornersRounded = new boolean[] { true, true, true, true };
  private int cornerRadius = DEFAULT_RADIUS;
  private Bitmap mBitmap;
  private Paint mBitmapPaint;

  public RoundedCornerDrawable(Bitmap bitmap) {
    mBitmap = bitmap;

    mBitmapPaint = new Paint();
    mBitmapPaint.setStyle(Paint.Style.FILL);
    mBitmapPaint.setAntiAlias(true);

//    mShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
  }

  /**
   * Sets the corner radius of all the corners.
   *
   * @param topLeft top left corner radius.
   * @param topRight top right corner radius
   * @param bottomRight bototm right corner radius.
   * @param bottomLeft bottom left corner radius.
   * @return the {@link RoundedCornerDrawable} for chaining.
   */
  public RoundedCornerDrawable setCornerRadius(int topLeft, int topRight, int bottomRight, int bottomLeft) {
    Set<Integer> nonDefaultCorners = getNonDefaultCorners(topLeft, topRight, bottomRight, bottomLeft);

    if (nonDefaultCorners.size() > 1) {
      throw new IllegalArgumentException("Multiple nonzero corner radius not yet supported.");
    }

    if (!nonDefaultCorners.isEmpty()) {
      int radius = nonDefaultCorners.iterator().next();
      if (Float.isInfinite(radius) || Float.isNaN(radius) || radius < 0) {
        throw new IllegalArgumentException("Invalid radius value: " + radius);
      }

      cornerRadius = radius;
    } else {
      cornerRadius = DEFAULT_RADIUS;
    }

    cornersRounded[CORNER_TOP_LEFT] = topLeft > DEFAULT_RADIUS;
    cornersRounded[CORNER_TOP_RIGHT] = topRight > DEFAULT_RADIUS;
    cornersRounded[CORNER_BOTTOM_RIGHT] = bottomRight > DEFAULT_RADIUS;
    cornersRounded[CORNER_BOTTOM_LEFT] = bottomLeft > DEFAULT_RADIUS;

    return this;
  }

  private Set<Integer> getNonDefaultCorners(int topLeft, int topRight, int bottomRight, int bottomLeft) {
    final Set<Integer> radiusSet = new HashSet<>(4);
    radiusSet.add(topLeft);
    radiusSet.add(topRight);
    radiusSet.add(bottomRight);
    radiusSet.add(bottomLeft);

    radiusSet.remove(DEFAULT_RADIUS);

    return radiusSet;
  }














    private RectF mDrawableRect = new RectF();

  private BitmapShader mShader;































  @Override
  protected void onBoundsChange(Rect bounds) {
    super.onBoundsChange(bounds);
    mDrawableRect.set(bounds);
  }

  @Override
  public void draw(Canvas canvas) {
    mBitmapPaint.setShader(mShader);
    canvas.drawRoundRect(mDrawableRect, cornerRadius, cornerRadius, mBitmapPaint);
  }

  @Override
  public void setAlpha(int alpha) {
    mBitmapPaint.setAlpha(alpha);
    invalidateSelf();
  }

  @Override
  public void setColorFilter(ColorFilter cf) {
    mBitmapPaint.setColorFilter(cf);
    invalidateSelf();
  }

  @Override
  public int getOpacity() {
    return PixelFormat.TRANSLUCENT;
  }

  public void setMatrix(Matrix matrix){
    mShader.setLocalMatrix(matrix);
    invalidateSelf();
  }

  public int getBitmapWidth(){
    return mBitmap.getWidth();
  }

  public int getBitmapHeight(){
    return mBitmap.getHeight();
  }



  public static Bitmap drawableToBitmap(Drawable drawable) {
    if (drawable instanceof BitmapDrawable) {
      return ((BitmapDrawable) drawable).getBitmap();
    }

    Bitmap bitmap;
    int width = Math.max(drawable.getIntrinsicWidth(), 2);
    int height = Math.max(drawable.getIntrinsicHeight(), 2);
    try {
      bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
      Canvas canvas = new Canvas(bitmap);
      drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
      drawable.draw(canvas);
    } catch (Exception e) {
      e.printStackTrace();
      bitmap = null;
    }

    return bitmap;
  }
}