package com.cesards.cropimageview.image_crop;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;

/**
 * Options for cropping the bounds of an image to the bounds of the ImageView.
 */
@Retention(RetentionPolicy.SOURCE)
@IntDef({
    CropType.NONE,
    CropType.LEFT_TOP,
    CropType.LEFT_CENTER,
    CropType.LEFT_BOTTOM,
    CropType.RIGHT_TOP,
    CropType.RIGHT_CENTER,
    CropType.RIGHT_BOTTOM,
    CropType.CENTER_TOP,
    CropType.CENTER_BOTTOM,
})
public @interface CropType {

  int NONE = -1;
  int LEFT_TOP = 0;
  int LEFT_CENTER = 1;
  int LEFT_BOTTOM = 2;
  int RIGHT_TOP = 3;
  int RIGHT_CENTER = 4;
  int RIGHT_BOTTOM = 5;
  int CENTER_TOP = 6;
  int CENTER_BOTTOM = 7;
}