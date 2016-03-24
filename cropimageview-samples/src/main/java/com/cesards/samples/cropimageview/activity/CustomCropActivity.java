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

import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import com.cesards.cropimageview.model.CropType;
import com.cesards.samples.cropimageview.R;
import com.cesards.samples.cropimageview.widget.ForegroundCropImageView;

public class CustomCropActivity extends CropActivity {

  private static final int[] images = {
      CropType.NONE,
  };

  @Override
  protected int getImagesCount() {
    return images.length;
  }

  @Override
  protected ImageView instantiatePagerItem(int position) {
    ForegroundCropImageView testCropImageView = new ForegroundCropImageView(CustomCropActivity.this);

    int image = images[position];
    if (image != -1) {
      testCropImageView.setImageDrawable(ContextCompat.getDrawable(this, image));
      testCropImageView.setForeground(ContextCompat.getDrawable(this, R.drawable.shape_grad_black_transp_70));
      @CropType final int cropType = images[position];
      testCropImageView.setCropType(cropType);
      testCropImageView.setId(cropType);
    } else {
      testCropImageView.setImageDrawable(null);
    }

    return testCropImageView;
  }
}
