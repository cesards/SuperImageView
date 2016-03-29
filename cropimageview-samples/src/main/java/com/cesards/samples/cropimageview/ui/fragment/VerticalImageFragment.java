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

package com.cesards.samples.cropimageview.ui.fragment;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.view.View;
import com.cesards.cropimageview.model.CropType;
import com.cesards.samples.cropimageview.R;

public class VerticalImageFragment extends BaseFragment {

  public static VerticalImageFragment newInstance(@CropType int cropType) {
    VerticalImageFragment fragment = new VerticalImageFragment();
    setArguments(fragment, cropType);
    return fragment;
  }

  public VerticalImageFragment() {
    // Required empty public constructor
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

  }

  @DrawableRes
  @Override
  protected int getBallResource() {
    return R.drawable.ball_vertical;
  }
}
