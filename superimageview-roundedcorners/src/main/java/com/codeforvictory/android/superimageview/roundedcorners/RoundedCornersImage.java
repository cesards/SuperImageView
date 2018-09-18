package com.codeforvictory.android.superimageview.roundedcorners;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;

import com.codeforvictory.android.superimageview.RoundedCorners;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 *
 *
 * The code is a modification of code found in https://github.com/florent37/ShapeOfView
 */
public final class RoundedCornersImage implements RoundedCorners {

    private static final float DEFAULT_RADIUS = 0f;

    private final float[] cornerRadius = new float[]{ DEFAULT_RADIUS, DEFAULT_RADIUS, DEFAULT_RADIUS, DEFAULT_RADIUS };
    private final View view;
    private final Paint clipPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final PorterDuffXfermode pdMode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
    private final Path clipPath = new Path();
    private final Rect canvasBounds = new Rect();
    private final RectF arcBounds = new RectF();

    public RoundedCornersImage(View view) {
        this.view = view;
    }

    // TODO: 9/8/18 IMPROVE PERFORMANCE: CLIPPATH + LESS MATH INVOLVED (ALLOCATION IS GOOD)
    @Override
    public void onDraw(Canvas canvas) {
        if (canvasBounds.width() != canvas.getWidth() && canvasBounds.height() != canvas.getHeight()) {
            canvas.getClipBounds(canvasBounds);

            clipPath.reset();
            clipPath.moveTo(canvasBounds.left + cornerRadius[0], canvasBounds.top);

            clipPath.lineTo(canvasBounds.right - cornerRadius[1], canvasBounds.top);
            arcBounds.left = canvasBounds.right - cornerRadius[1] * 2f;
            arcBounds.top = canvasBounds.top;
            arcBounds.right = canvasBounds.right;
            arcBounds.bottom = canvasBounds.top + cornerRadius[1] * 2f;
            clipPath.arcTo(arcBounds, -90, 90);

            clipPath.lineTo(canvasBounds.right, canvasBounds.bottom - cornerRadius[2]);
            arcBounds.left = canvasBounds.right - cornerRadius[2] * 2f;
            arcBounds.top = canvasBounds.bottom - cornerRadius[2] * 2f;
            arcBounds.right = canvasBounds.right;
            arcBounds.bottom = canvasBounds.bottom;
            clipPath.arcTo(arcBounds, 0, 90);

            clipPath.lineTo(canvasBounds.left + cornerRadius[3], canvasBounds.bottom);
            arcBounds.left = canvasBounds.left;
            arcBounds.top = canvasBounds.bottom - cornerRadius[3] * 2f;
            arcBounds.right = canvasBounds.left + cornerRadius[3] * 2f;
            arcBounds.bottom = canvasBounds.bottom;
            clipPath.arcTo(arcBounds, 90, 90);

            clipPath.lineTo(canvasBounds.left, canvasBounds.top + cornerRadius[0]);
            arcBounds.left = canvasBounds.left;
            arcBounds.top = canvasBounds.top;
            arcBounds.right = canvasBounds.left + cornerRadius[0] * 2f;
            arcBounds.bottom = canvasBounds.top + cornerRadius[0] * 2f;
            clipPath.arcTo(arcBounds, 180, 90);

            clipPath.close();
        }

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O_MR1) {
            canvas.drawPath(clipPath, clipPaint);
        } else {
//          canvas.drawPath(rectView, clipPaint);
        }
    }

    @Override
    public void onLayoutChanged() {
        // handled in onDraw()
    }

    @Override
    public void setup(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        final TypedArray a = view.getContext().obtainStyledAttributes(attributeSet, R.styleable.siv_RoundedCorners);
        final int corners = a.getDimensionPixelOffset(R.styleable.siv_RoundedCorners_siv_cornerRadius, Corner.NONE);
        if (corners != Corner.NONE) {
            cornerRadius[Corner.TOP_LEFT] = corners;
            cornerRadius[Corner.TOP_RIGHT] = corners;
            cornerRadius[Corner.BOTTOM_LEFT] = corners;
            cornerRadius[Corner.BOTTOM_RIGHT] = corners;
        } else {
            cornerRadius[Corner.TOP_LEFT] = a.getDimensionPixelOffset(R.styleable.siv_RoundedCorners_siv_cornerRadiusTopLeft, Corner.NONE);
            cornerRadius[Corner.TOP_RIGHT] = a.getDimensionPixelOffset(R.styleable.siv_RoundedCorners_siv_cornerRadiusTopRight, Corner.NONE);
            cornerRadius[Corner.BOTTOM_LEFT] = a.getDimensionPixelOffset(R.styleable.siv_RoundedCorners_siv_cornerRadiusBottomLeft, Corner.NONE);
            cornerRadius[Corner.BOTTOM_RIGHT] = a.getDimensionPixelOffset(R.styleable.siv_RoundedCorners_siv_cornerRadiusBottomRight, Corner.NONE);
        }
        a.recycle();

        for (int i = 0, len = this.cornerRadius.length; i < len; i++) {
            if (this.cornerRadius[i] < 0) {
                throw new IllegalStateException("This can't be possible");
            }
        }

        clipPaint.setAntiAlias(true);
        view.setWillNotDraw(false);

        // TODO: 9/9/18 STUDY WHY THIS IS NECESSARY
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O_MR1) {
            clipPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            view.setLayerType(android.view.View.LAYER_TYPE_HARDWARE, null);
        } else {
            clipPaint.setXfermode(pdMode);
            view.setLayerType(android.view.View.LAYER_TYPE_SOFTWARE, null);
        }
    }
}
