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
