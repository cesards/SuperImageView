package com.cesards.cropimageview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewOutlineProvider;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;

public class CropImageView extends AppCompatImageView {

  private final CroppedImage croppedImage = new CroppedImage(this);
  private final RoundedCornerImage roundedImage = new RoundedCornerImage(this);

  public CropImageView(Context context) {
    super(context);
    roundedImage.setup(context, null);
    croppedImage.setupCrop(context, null);
  }

  public CropImageView(Context context, AttributeSet attrs) {
    super(context, attrs, 0);
    roundedImage.setup(context, attrs);
    croppedImage.setupCrop(context, attrs);
  }

  public CropImageView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    roundedImage.setup(context, attrs);
    croppedImage.setupCrop(context, attrs);
  }

  /**
   * Returns {@link RoundedCornerImage} so the user can retrieve and set information related to cropping
   * the image.
   */
  @NonNull
  public CroppedImage croppedImage() {
    return croppedImage;
  }

  /**
   * Returns {@link RoundedCornerImage} so the user can retrieve and set information related to rounding
   * the image corners.
   */
//  @NonNull
//  RoundedCornerImage roundedImage() {
//    return roundedImage;
//  }

  @Override
  protected boolean setFrame(int l, int t, int r, int b) {
    final boolean changed = super.setFrame(l, t, r, b);
    if (changed) {
      croppedImage.applyTransformation();
    }

    return changed;
  }

  @Override
  protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    Log.d("", "");
    super.onLayout(changed, left, top, right, bottom);
    if (changed) {
      roundedImage.requiresShapeUpdate();
    }
  }


  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    Log.d("onDraw", toString());
    roundedImage.dispatchOnDraw(canvas);
  }


  @Override
  public void setImageDrawable(Drawable drawable) {
    Log.d("", "");
    super.setImageDrawable(drawable);
    roundedImage.requiresShapeUpdate();


//    if (drawable instanceof RoundedBitmapDrawable) {
//      super.setImageDrawable(drawable);
//      return;
//    }
//    RoundedBitmapDrawable dr = RoundedBitmapDrawableFactory.create(getResources(), ((BitmapDrawable) drawable).getBitmap());
//    mBitmapShader = new BitmapShader(dr.getBitmap(), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

//    dr.setCornerRadius(10f);
//    super.setImageDrawable(dr);
//    this.requiersShapeUpdate = true;
//    super.setImageDrawable(drawable);
//    postInvalidate();
  }


  @Override
  public ViewOutlineProvider getOutlineProvider() {
    Log.d("", "");
//    return super.getOutlineProvider();
    return roundedImage.getOutlineProvider();
  }

  @Override
  protected void drawableStateChanged() {
    Log.d("", "");
    super.drawableStateChanged();
    roundedImage.notifyDrawableStateChanges(); // is it really necessary?
  }

  @Override
  public void setScaleType(ScaleType scaleType) {
    Log.d("", "");
    croppedImage.delegateSetScaleType(scaleType);
//    roundedImage.delegateScaleType(scaleType);
    super.setScaleType(scaleType);
  }










//  @Override
//  protected void onDraw(Canvas canvas) {
//    super.onDraw(canvas);
//    if (getDrawable() instanceof RoundedBitmapDrawable) {
//      canvas.drawRoundRect(mDstRectF, mCornerRadius, mCornerRadius, paint);
//    }
//  }









  //  @Override
//  public void setImageBitmap(Bitmap bm) {
//    super.setImageDrawable(roundedImage.modifiedDrawable(bm));
//    // I think it would be better not to modify this method because we are breaking Liskov principle?
//    // so we could use Bitmap modifiedBitmap(Bitmap bitmap);
//    // as
//    // super.setImageBitmap(roundedImage.modifiedBitmap(bm));
    // bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
//  }
//

//  @Override
//  protected void onDraw(Canvas canvas) {
//    super.onDraw(canvas);
//    rect.bottom = getHeight();
//    rect.right = getWidth();
//    rect.left = 0;
//    rect.top = 0;
//
//    canvas.drawRoundRect(rect, 10f, 10f, paint);
//
//    if (requiersShapeUpdate) {
//      calculateLayout(canvas.getWidth(), canvas.getHeight());
//      requiersShapeUpdate = false;
//    }
//
//  }






}
