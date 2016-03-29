
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

package com.cesards.cropimageview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.cesards.cropimageview.model.CropImage;
import com.cesards.cropimageview.model.CropImageFactory;
import com.cesards.cropimageview.model.CropType;

public class CropImageView extends ImageView {

  private CropImage cropImage;
  private int cropType = CropType.NONE;

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
  protected boolean setFrame(int left, int top, int right, int bottom) {
    final boolean changed = super.setFrame(left, top, right, bottom);
    if (!isInEditMode()) {
      cropImage.computeImageTransformation();
    }
    return changed;
  }

  private void parseAttributes(AttributeSet attrs) {
    final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.civ_CropImageView);

    cropType = a.getInt(R.styleable.civ_CropImageView_civ_crop, CropType.NONE);

    a.recycle();
  }

  private void initImageView() {
    setScaleType(ScaleType.MATRIX);

    cropImage = new CropImageFactory().getCropImage(this);
  }
}
