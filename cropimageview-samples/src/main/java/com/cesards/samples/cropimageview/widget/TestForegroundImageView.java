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

package com.cesards.samples.cropimageview.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import com.cesards.android.foregroundviews.ForegroundImageView;
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
