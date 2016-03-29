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

package com.cesards.samples.cropimageview.ui.activity;

import android.os.Bundle;
import com.cesards.cropimageview.model.CropType;
import com.cesards.samples.cropimageview.R;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
  }

  @OnClick(R.id.main_button_crop_bottom)
  void onCropBottomClick() {
    CropActivity.show(this, CropType.BOTTOM);
  }

  @OnClick(R.id.main_button_crop_left)
  void onCropLeftClick() {
    CropActivity.show(this, CropType.LEFT);
  }

  @OnClick(R.id.main_button_crop_right)
  void onCropRightClick() {
    CropActivity.show(this, CropType.RIGHT);
  }

  @OnClick(R.id.main_button_crop_top)
  void onCropTopClick() {
    CropActivity.show(this, CropType.TOP);
  }
}
