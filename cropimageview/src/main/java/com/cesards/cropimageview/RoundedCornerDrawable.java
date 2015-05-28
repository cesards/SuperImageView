package com.cesards.cropimageview;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
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
import android.util.Log;
import android.view.View;

/**
 * Created by victorcassone on 5/23/15.
 */
public class RoundedCornerDrawable extends Drawable {

  private RectF mDrawableRect = new RectF();
  private Bitmap mBitmap;
  private Paint mBitmapPaint;
  private float mCornerRadius;
  private BitmapShader mShader;

  public RoundedCornerDrawable(Bitmap bitmap) {
    mBitmap = bitmap;

    mBitmapPaint = new Paint();
    mBitmapPaint.setStyle(Paint.Style.FILL);
    mBitmapPaint.setAntiAlias(true);

    mShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
  }

  public void setCornerRadius(float radius) {
    mCornerRadius = radius;
  }

  @Override
  protected void onBoundsChange(Rect bounds) {
    super.onBoundsChange(bounds);
    mDrawableRect.set(bounds);
  }

  @Override
  public void draw(Canvas canvas) {
    mBitmapPaint.setShader(mShader);
    canvas.drawRoundRect(mDrawableRect, mCornerRadius, mCornerRadius, mBitmapPaint);
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

  public static Drawable fromDrawable(Drawable drawable) {
    if (drawable != null) {
      if (drawable instanceof RoundedCornerDrawable) {
        return drawable;
      } else if (drawable instanceof LayerDrawable) {
        LayerDrawable ld = (LayerDrawable) drawable;
        int num = ld.getNumberOfLayers();

        for (int i = 0; i < num; i++) {
          Drawable d = ld.getDrawable(i);
          ld.setDrawableByLayerId(ld.getId(i), fromDrawable(d));
        }
        return ld;
      }

      Bitmap bm = drawableToBitmap(drawable);
      if (bm != null) {
        return new RoundedCornerDrawable(bm);
      }
    }
    return drawable;
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