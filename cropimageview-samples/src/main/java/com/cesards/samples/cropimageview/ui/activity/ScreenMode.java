package com.cesards.samples.cropimageview.ui.activity;

import android.app.Activity;
import com.cesards.samples.cropimageview.util.SystemUiHelper;

public class ScreenMode {

  private Activity activity;

  ScreenMode(Activity activity) {
    this.activity = activity;
  }

  public void loadLowProfileMode() {
    new SystemUiHelper(activity, SystemUiHelper.LEVEL_LOW_PROFILE, SystemUiHelper.FLAG_IMMERSIVE_STICKY).hide();
  }


}
