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

package com.cesards.samples.cropimageview.activity;

import android.content.res.Resources;
import android.widget.ImageView;
import com.cesards.cropimageview.CropImageView;
import com.cesards.samples.cropimageview.R;
import com.cesards.samples.cropimageview.widget.TestForegroundCropImageView;

/**
 * @author cesards
 */
public class CustomCropActivity extends CropActivity {

  private CropImageView.CropType[] imageCrops = {
      // Zombie sample
      CropImageView.CropType.CENTER_TOP,
      CropImageView.CropType.LEFT_CENTER,
      CropImageView.CropType.CENTER_BOTTOM,
      // Ball sample
      CropImageView.CropType.LEFT_CENTER,
      CropImageView.CropType.CENTER_TOP,
      CropImageView.CropType.RIGHT_CENTER,
  };

  private int[] images = {
      R.drawable.zombie,
      R.drawable.zombie,
      R.drawable.zombie,
      R.drawable.ball_centered_bottom_ball,
      R.drawable.ball_centered_bottom_ball,
      R.drawable.ball_centered_bottom_ball,
  };

  @Override protected int getImagesCount() {
    return images.length;
  }

  @Override protected ImageView instantiatePagerItem(int position) {
    TestForegroundCropImageView testCropImageView = new TestForegroundCropImageView(CustomCropActivity.this);
    final Resources res = getResources();
    testCropImageView.setImageDrawable(res.getDrawable(images[position]));
    testCropImageView.setForeground(res.getDrawable(R.drawable.shape_grad_black_transp_70));
    final CropImageView.CropType cropType = imageCrops[position];
    testCropImageView.setCropType(cropType);
    testCropImageView.setId(cropType.getCrop());

    return testCropImageView;
  }
}
