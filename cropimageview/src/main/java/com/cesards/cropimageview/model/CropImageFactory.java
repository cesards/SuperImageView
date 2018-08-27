package com.cesards.cropimageview.model;

import android.os.Build;
import android.widget.ImageView;

import com.cesards.cropimageview.CropImageView;

import androidx.annotation.NonNull;

public class CropImageFactory {

  public CropImage getCropImage(@NonNull ImageView imageView, @CropType int cropType) {
    final boolean preApi18 = Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2;
    return preApi18 ? new PreApi18CropImage(imageView, cropType) : new API18Image(imageView, cropType);
  }
}
