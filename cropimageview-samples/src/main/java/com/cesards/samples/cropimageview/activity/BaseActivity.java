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

import android.app.Activity;
import android.os.Bundle;
import com.cesards.samples.cropimageview.util.SystemUiHelper;

/**
 * @author cesards
 */
public abstract class BaseActivity extends Activity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    this.switchToLowProfile();
  }

  private void switchToLowProfile() {
    final SystemUiHelper systemUiHelper =
        new SystemUiHelper(this, SystemUiHelper.LEVEL_LOW_PROFILE, SystemUiHelper.FLAG_IMMERSIVE_STICKY);
    systemUiHelper.hide();
  }
}
