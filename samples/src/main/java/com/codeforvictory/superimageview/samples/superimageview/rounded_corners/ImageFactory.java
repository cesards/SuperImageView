package com.codeforvictory.superimageview.samples.superimageview.rounded_corners;

import com.codeforvictory.android.superimageview.crop.CropType;
import com.codeforvictory.superimageview.samples.superimageview.R;

import java.util.Arrays;
import java.util.List;

public final class ImageFactory {

  private ImageFactory() {
    throw new AssertionError("This shouldn't be initialized!");
  }

  public static List<Image> imagesWithoutRoundedCorners() {
    return Arrays.asList(
//                new Image(R.drawable.ball_vertical, false, CropType.NONE),
//                new Image(R.drawable.ball_horizontal, false, CropType.NONE),
        new Image(R.drawable.ball_vertical, false, CropType.TOP),
        new Image(R.drawable.ball_vertical, false, CropType.BOTTOM),
        new Image(R.drawable.ball_horizontal, false, CropType.TOP_LEFT),
        new Image(R.drawable.ball_horizontal, false, CropType.LEFT),
        new Image(R.drawable.ball_horizontal, false, CropType.BOTTOM_LEFT),
        new Image(R.drawable.ball_horizontal, false, CropType.TOP_RIGHT),
        new Image(R.drawable.ball_horizontal, false, CropType.BOTTOM_RIGHT),
        new Image(R.drawable.ball_horizontal, false, CropType.RIGHT),
        new Image(R.drawable.ball_vertical, false, CropType.TOP),
        new Image(R.drawable.ball_vertical, false, CropType.BOTTOM),
        new Image(R.drawable.ball_horizontal, false, CropType.TOP_LEFT),
        new Image(R.drawable.ball_horizontal, false, CropType.LEFT),
        new Image(R.drawable.ball_horizontal, false, CropType.BOTTOM_LEFT),
        new Image(R.drawable.ball_horizontal, false, CropType.TOP_RIGHT),
        new Image(R.drawable.ball_horizontal, false, CropType.BOTTOM_RIGHT),
        new Image(R.drawable.ball_horizontal, false, CropType.RIGHT),
        new Image(R.drawable.ball_vertical, false, CropType.TOP),
        new Image(R.drawable.ball_vertical, false, CropType.BOTTOM),
        new Image(R.drawable.ball_horizontal, false, CropType.TOP_LEFT),
        new Image(R.drawable.ball_horizontal, false, CropType.LEFT),
        new Image(R.drawable.ball_horizontal, false, CropType.BOTTOM_LEFT),
        new Image(R.drawable.ball_horizontal, false, CropType.TOP_RIGHT),
        new Image(R.drawable.ball_horizontal, false, CropType.BOTTOM_RIGHT),
        new Image(R.drawable.ball_horizontal, false, CropType.RIGHT),
        new Image(R.drawable.ball_vertical, false, CropType.TOP),
        new Image(R.drawable.ball_vertical, false, CropType.BOTTOM),
        new Image(R.drawable.ball_horizontal, false, CropType.TOP_LEFT),
        new Image(R.drawable.ball_horizontal, false, CropType.LEFT),
        new Image(R.drawable.ball_horizontal, false, CropType.BOTTOM_LEFT),
        new Image(R.drawable.ball_horizontal, false, CropType.TOP_RIGHT),
        new Image(R.drawable.ball_horizontal, false, CropType.BOTTOM_RIGHT),
        new Image(R.drawable.ball_horizontal, false, CropType.RIGHT)
    );
  }

  public static List<Image> imagesWithRoundedCorners() {
    return Arrays.asList(
//                new Image(R.drawable.ball_vertical, false, CropType.NONE),
//                new Image(R.drawable.ball_horizontal, false, CropType.NONE),
        new Image(R.drawable.ball_vertical, true, CropType.TOP),
        new Image(R.drawable.ball_vertical, true, CropType.BOTTOM),
        new Image(R.drawable.ball_horizontal, true, CropType.TOP_LEFT),
        new Image(R.drawable.ball_horizontal, true, CropType.LEFT),
        new Image(R.drawable.ball_horizontal, true, CropType.BOTTOM_LEFT),
        new Image(R.drawable.ball_horizontal, true, CropType.TOP_RIGHT),
        new Image(R.drawable.ball_horizontal, true, CropType.BOTTOM_RIGHT),
        new Image(R.drawable.ball_horizontal, true, CropType.RIGHT),
        new Image(R.drawable.ball_vertical, false, CropType.TOP),
        new Image(R.drawable.ball_vertical, false, CropType.BOTTOM),
        new Image(R.drawable.ball_horizontal, false, CropType.TOP_LEFT),
        new Image(R.drawable.ball_horizontal, false, CropType.LEFT),
        new Image(R.drawable.ball_horizontal, false, CropType.BOTTOM_LEFT),
        new Image(R.drawable.ball_horizontal, false, CropType.TOP_RIGHT),
        new Image(R.drawable.ball_horizontal, false, CropType.BOTTOM_RIGHT),
        new Image(R.drawable.ball_horizontal, false, CropType.RIGHT),
        new Image(R.drawable.ball_vertical, false, CropType.TOP),
        new Image(R.drawable.ball_vertical, false, CropType.BOTTOM),
        new Image(R.drawable.ball_horizontal, false, CropType.TOP_LEFT),
        new Image(R.drawable.ball_horizontal, false, CropType.LEFT),
        new Image(R.drawable.ball_horizontal, false, CropType.BOTTOM_LEFT),
        new Image(R.drawable.ball_horizontal, false, CropType.TOP_RIGHT),
        new Image(R.drawable.ball_horizontal, false, CropType.BOTTOM_RIGHT),
        new Image(R.drawable.ball_horizontal, false, CropType.RIGHT),
        new Image(R.drawable.ball_vertical, false, CropType.TOP),
        new Image(R.drawable.ball_vertical, false, CropType.BOTTOM),
        new Image(R.drawable.ball_horizontal, false, CropType.TOP_LEFT),
        new Image(R.drawable.ball_horizontal, false, CropType.LEFT),
        new Image(R.drawable.ball_horizontal, false, CropType.BOTTOM_LEFT),
        new Image(R.drawable.ball_horizontal, false, CropType.TOP_RIGHT),
        new Image(R.drawable.ball_horizontal, false, CropType.BOTTOM_RIGHT),
        new Image(R.drawable.ball_horizontal, false, CropType.RIGHT)
    );
  }
}
