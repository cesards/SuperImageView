package com.cesards.samples.cropimageview.activity;

import android.content.res.Resources;
import android.widget.ImageView;
import com.cesards.samples.cropimageview.R;
import com.cesards.samples.cropimageview.widget.TestForegroundImageView;

public class SimpleCropActivity extends CropActivity {

  private int[] images = {
      R.drawable.zombie,
      R.drawable.ball_centered_bottom_ball,
  };

  @Override protected int getImagesCount() {
    return images.length;
  }

  @Override protected ImageView instantiatePagerItem(int position) {
    TestForegroundImageView testForegroundImageView = new TestForegroundImageView(SimpleCropActivity.this);
    final Resources res = getResources();
    testForegroundImageView.setImageDrawable(res.getDrawable(images[position]));
    testForegroundImageView.setForeground(res.getDrawable(R.drawable.shape_grad_black_transp_70));
    testForegroundImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

    return testForegroundImageView;
  }
}
