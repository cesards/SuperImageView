package com.cesards.cropimageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.cesards.cropimageview.model.CropImage;
import com.cesards.cropimageview.model.CropImageFactory;
import com.cesards.cropimageview.model.CropType;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

public class CropImageView extends AppCompatImageView {

  @Nullable private CropImage cropImage;
  @CropType private int cropType = CropType.NONE;

  public CropImageView(Context context) {
    super(context);
    setup();
  }

  public CropImageView(Context context, AttributeSet attrs) {
    super(context, attrs, 0);
    parseAttributes(attrs);
    setup();
  }

  public CropImageView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    parseAttributes(attrs);
    setup();
  }

  /**
   * Set crop type for the {@link ImageView}
   *
   * @param cropType A {@link CropType} desired scaling mode.
   */
  public void setCropType(@CropType int cropType) {
    this.cropType = cropType;

    setWillNotCacheDrawing(false);

    requestLayout();
    invalidate();
  }

  /**
   * Return the current crop type in use by this ImageView.
   *
   * @return a {@link CropType} in use by this ImageView
   */
  @CropType
  public int getCropType() {
    return cropType;
  }

  @Override
  protected boolean setFrame(int l, int t, int r, int b) {
    final boolean changed = super.setFrame(l, t, r, b);
    if (!isInEditMode() && cropImage != null && getDrawable() != null) {
      cropImage.computeImageTransformation();
    }
    return changed;
  }

  @Override
  public void setImageBitmap(Bitmap bm) {
    super.setImageBitmap(bm);
    setup();
  }

  @Override
  public void setImageDrawable(Drawable drawable) {
    super.setImageDrawable(drawable);
    setup();
  }

  @Override
  public void setImageResource(int resId) {
    super.setImageResource(resId);
    setup();
  }


  private void setup() {
    setScaleType(ScaleType.MATRIX);

    if (getDrawable() != null) {
      cropImage = new CropImageFactory().getCropImage(this, cropType);
    }
  }

  private void parseAttributes(AttributeSet attrs) {
    final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.civ_CropImageView);
    cropType = a.getInt(R.styleable.civ_CropImageView_civ_crop, CropType.NONE);
    a.recycle();
  }
}
