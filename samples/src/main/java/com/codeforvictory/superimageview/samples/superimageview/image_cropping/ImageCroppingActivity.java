package com.codeforvictory.superimageview.samples.superimageview.image_cropping;

import android.os.Bundle;

import com.codeforvictory.superimageview.samples.superimageview.R;
import com.codeforvictory.superimageview.samples.superimageview.shared.ImageLocalDataSource;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public final class ImageCroppingActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_image_cropping);
    ((ViewPager) findViewById(R.id.cropped_images)).setAdapter(new ImagesPagerAdapter(ImageLocalDataSource.localImages()));
  }
}
