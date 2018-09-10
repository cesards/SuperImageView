package com.codeforvictory.superimageview.samples.superimageview.rounded_corners;

import com.codeforvictory.android.superimageview.crop.CropType;
import com.codeforvictory.superimageview.samples.superimageview.R;

import java.util.Arrays;
import java.util.List;

final class ImageFactory {

    private ImageFactory() {
        throw new AssertionError("This shouldn't be initialized!");
    }

    static List<Image> imagesWithoutRoundedCorners() {
        return Arrays.asList(
//                new Image(R.drawable.ball_vertical, false, CropType.NONE),
//                new Image(R.drawable.ball_horizontal, false, CropType.NONE),
                new Image(R.drawable.ball_vertical, false, CropType.CENTER_TOP),
                new Image(R.drawable.ball_vertical, false, CropType.CENTER_BOTTOM),
                new Image(R.drawable.ball_horizontal, false, CropType.LEFT_TOP),
                new Image(R.drawable.ball_horizontal, false, CropType.LEFT_CENTER),
                new Image(R.drawable.ball_horizontal, false, CropType.LEFT_BOTTOM),
                new Image(R.drawable.ball_horizontal, false, CropType.RIGHT_TOP),
                new Image(R.drawable.ball_horizontal, false, CropType.RIGHT_BOTTOM),
                new Image(R.drawable.ball_horizontal, false, CropType.RIGHT_CENTER),
                new Image(R.drawable.ball_vertical, false, CropType.CENTER_TOP),
                new Image(R.drawable.ball_vertical, false, CropType.CENTER_BOTTOM),
                new Image(R.drawable.ball_horizontal, false, CropType.LEFT_TOP),
                new Image(R.drawable.ball_horizontal, false, CropType.LEFT_CENTER),
                new Image(R.drawable.ball_horizontal, false, CropType.LEFT_BOTTOM),
                new Image(R.drawable.ball_horizontal, false, CropType.RIGHT_TOP),
                new Image(R.drawable.ball_horizontal, false, CropType.RIGHT_BOTTOM),
                new Image(R.drawable.ball_horizontal, false, CropType.RIGHT_CENTER),
                new Image(R.drawable.ball_vertical, false, CropType.CENTER_TOP),
                new Image(R.drawable.ball_vertical, false, CropType.CENTER_BOTTOM),
                new Image(R.drawable.ball_horizontal, false, CropType.LEFT_TOP),
                new Image(R.drawable.ball_horizontal, false, CropType.LEFT_CENTER),
                new Image(R.drawable.ball_horizontal, false, CropType.LEFT_BOTTOM),
                new Image(R.drawable.ball_horizontal, false, CropType.RIGHT_TOP),
                new Image(R.drawable.ball_horizontal, false, CropType.RIGHT_BOTTOM),
                new Image(R.drawable.ball_horizontal, false, CropType.RIGHT_CENTER),
                new Image(R.drawable.ball_vertical, false, CropType.CENTER_TOP),
                new Image(R.drawable.ball_vertical, false, CropType.CENTER_BOTTOM),
                new Image(R.drawable.ball_horizontal, false, CropType.LEFT_TOP),
                new Image(R.drawable.ball_horizontal, false, CropType.LEFT_CENTER),
                new Image(R.drawable.ball_horizontal, false, CropType.LEFT_BOTTOM),
                new Image(R.drawable.ball_horizontal, false, CropType.RIGHT_TOP),
                new Image(R.drawable.ball_horizontal, false, CropType.RIGHT_BOTTOM),
                new Image(R.drawable.ball_horizontal, false, CropType.RIGHT_CENTER)
        );
    }

    static List<Image> imagesWithRoundedCorners() {
        return Arrays.asList(
//                new Image(R.drawable.ball_vertical, false, CropType.NONE),
//                new Image(R.drawable.ball_horizontal, false, CropType.NONE),
                new Image(R.drawable.ball_vertical, true, CropType.CENTER_TOP),
                new Image(R.drawable.ball_vertical, true, CropType.CENTER_BOTTOM),
                new Image(R.drawable.ball_horizontal, true, CropType.LEFT_TOP),
                new Image(R.drawable.ball_horizontal, true, CropType.LEFT_CENTER),
                new Image(R.drawable.ball_horizontal, true, CropType.LEFT_BOTTOM),
                new Image(R.drawable.ball_horizontal, true, CropType.RIGHT_TOP),
                new Image(R.drawable.ball_horizontal, true, CropType.RIGHT_BOTTOM),
                new Image(R.drawable.ball_horizontal, true, CropType.RIGHT_CENTER),
                new Image(R.drawable.ball_vertical, false, CropType.CENTER_TOP),
                new Image(R.drawable.ball_vertical, false, CropType.CENTER_BOTTOM),
                new Image(R.drawable.ball_horizontal, false, CropType.LEFT_TOP),
                new Image(R.drawable.ball_horizontal, false, CropType.LEFT_CENTER),
                new Image(R.drawable.ball_horizontal, false, CropType.LEFT_BOTTOM),
                new Image(R.drawable.ball_horizontal, false, CropType.RIGHT_TOP),
                new Image(R.drawable.ball_horizontal, false, CropType.RIGHT_BOTTOM),
                new Image(R.drawable.ball_horizontal, false, CropType.RIGHT_CENTER),
                new Image(R.drawable.ball_vertical, false, CropType.CENTER_TOP),
                new Image(R.drawable.ball_vertical, false, CropType.CENTER_BOTTOM),
                new Image(R.drawable.ball_horizontal, false, CropType.LEFT_TOP),
                new Image(R.drawable.ball_horizontal, false, CropType.LEFT_CENTER),
                new Image(R.drawable.ball_horizontal, false, CropType.LEFT_BOTTOM),
                new Image(R.drawable.ball_horizontal, false, CropType.RIGHT_TOP),
                new Image(R.drawable.ball_horizontal, false, CropType.RIGHT_BOTTOM),
                new Image(R.drawable.ball_horizontal, false, CropType.RIGHT_CENTER),
                new Image(R.drawable.ball_vertical, false, CropType.CENTER_TOP),
                new Image(R.drawable.ball_vertical, false, CropType.CENTER_BOTTOM),
                new Image(R.drawable.ball_horizontal, false, CropType.LEFT_TOP),
                new Image(R.drawable.ball_horizontal, false, CropType.LEFT_CENTER),
                new Image(R.drawable.ball_horizontal, false, CropType.LEFT_BOTTOM),
                new Image(R.drawable.ball_horizontal, false, CropType.RIGHT_TOP),
                new Image(R.drawable.ball_horizontal, false, CropType.RIGHT_BOTTOM),
                new Image(R.drawable.ball_horizontal, false, CropType.RIGHT_CENTER)
        );
    }
}
