package com.codeforvictory.android.superimageview.crop;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;

/**
 * Options for cropping the bounds of an image to the bounds of the ImageView.
 */
@Retention(RetentionPolicy.SOURCE)
@IntDef({
    CropType.NONE,
    CropType.TOP_LEFT,
    CropType.TOP_RIGHT,
    CropType.BOTTOM_LEFT,
    CropType.BOTTOM_RIGHT,
    CropType.LEFT,
    CropType.TOP,
    CropType.RIGHT,
    CropType.BOTTOM,
})
public @interface CropType {
  int NONE = -1;
  int TOP_LEFT = 0;
  int LEFT = 1;
  int BOTTOM_LEFT = 2;
  int TOP_RIGHT = 3;
  int RIGHT = 4;
  int BOTTOM_RIGHT = 5;
  int TOP = 6;
  int BOTTOM = 7;
}