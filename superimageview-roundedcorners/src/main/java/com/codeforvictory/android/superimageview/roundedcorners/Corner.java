package com.codeforvictory.android.superimageview.roundedcorners;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;

import static com.codeforvictory.android.superimageview.roundedcorners.Corner.NONE;
import static com.codeforvictory.android.superimageview.roundedcorners.Corner.TOP_LEFT;
import static com.codeforvictory.android.superimageview.roundedcorners.Corner.TOP_RIGHT;
import static com.codeforvictory.android.superimageview.roundedcorners.Corner.BOTTOM_LEFT;
import static com.codeforvictory.android.superimageview.roundedcorners.Corner.BOTTOM_RIGHT;

/**
 * Options for cropping the bounds of an image to the bounds of the ImageView.
 */
@Retention(RetentionPolicy.SOURCE)
@IntDef({
        NONE,
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT,
})
public @interface Corner {
    int NONE = -1;
    int TOP_LEFT = 0;
    int TOP_RIGHT = 1;
    int BOTTOM_LEFT = 2;
    int BOTTOM_RIGHT = 3;
}