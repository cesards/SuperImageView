package com.cesards.samples.cropimageview.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import hugo.weaving.DebugLog;

/**
 * @author cesards
 */
public class TestForegroundImageView extends ForegroundImageView {

  public TestForegroundImageView(Context context) {
    super(context);
  }

  public TestForegroundImageView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public TestForegroundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP) public TestForegroundImageView(Context context, AttributeSet attrs,
      int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @DebugLog @Override protected boolean setFrame(int l, int t, int r, int b) {
    return super.setFrame(l, t, r, b);
  }
}
