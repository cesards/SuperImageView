package com.cesards.samples.cropimageview.widget;

import android.content.Context;
import android.util.AttributeSet;
import hugo.weaving.DebugLog;

/**
 * @author cesards
 */
public class TestForegroundCropImageView extends ForegroundCropImageView {

  public TestForegroundCropImageView(Context context) {
    super(context);
  }

  public TestForegroundCropImageView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public TestForegroundCropImageView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  public TestForegroundCropImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @DebugLog
  @Override protected boolean setFrame(int l, int t, int r, int b) {
    return super.setFrame(l, t, r, b);
  }
}
