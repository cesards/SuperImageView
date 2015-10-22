package com.cesards.cropimageview;

import android.os.Build;
import android.support.annotation.NonNull;

public class ImageMathsFactory {

  public ImageMaths getImageMaths(@NonNull CropImageView imageView) {
    final boolean preApi18 = Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2;
    return preApi18 ? new PreApi18CropImage(imageView) : new API18Image(imageView);
  }
}
