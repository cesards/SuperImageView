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
