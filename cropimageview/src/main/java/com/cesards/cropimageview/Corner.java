package com.cesards.cropimageview;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@IntDef({
        RoundedCornerDrawable.CORNER_TOP_LEFT,
        RoundedCornerDrawable.CORNER_TOP_RIGHT,
        RoundedCornerDrawable.CORNER_BOTTOM_LEFT,
        RoundedCornerDrawable.CORNER_BOTTOM_RIGHT
})
public @interface Corner { }