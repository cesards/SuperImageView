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

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.cesards.cropimageview.CropImageView;
import com.cesards.cropimageview.model.CropType;
import com.cesards.samples.cropimageview.R;
import com.cesards.samples.cropimageview.ui.widget.CroppedAreaImageView;
import butterknife.Bind;
import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {

  protected static final String ARG_CROP = "arg_crop";

  @Bind(R.id.crop_image_original) CroppedAreaImageView croppedAreaImageView;
  @Bind(R.id.crop_image_cropped) CropImageView croppedImage;

  protected int cropType;

  protected static void setArguments(Fragment fragment, @CropType int cropType) {
    Bundle args = new Bundle();
    args.putInt(ARG_CROP, cropType);
    fragment.setArguments(args);
  }

  @DrawableRes
  protected abstract int getBallResource();

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    final Bundle arguments = getArguments();
    if (arguments == null) {
      throw new IllegalStateException();
    }

    cropType = arguments.getInt(ARG_CROP);
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater,
      ViewGroup container,
      Bundle savedInstanceState
  ) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_crop, container, false);
    ButterKnife.bind(this, view);
    return view;
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    final Drawable image = ContextCompat.getDrawable(getActivity(), getBallResource());
    croppedAreaImageView.setImageDrawable(image);
    croppedImage.setImageDrawable(image);
    croppedImage.setCropType(cropType);
    croppedAreaImageView.setCropType(cropType);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }
}
