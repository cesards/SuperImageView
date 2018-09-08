package com.codeforvictory.superimageview.roundedcorners;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;

import com.cesards.cropimageview.RoundedCorners;
import com.codeforvictory.roundedcorners.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class RoundedCornersImage implements RoundedCorners {

    private static final float DEFAULT_RADIUS = 0f;

    private final float[] cornerRadius = new float[]{ DEFAULT_RADIUS, DEFAULT_RADIUS, DEFAULT_RADIUS, DEFAULT_RADIUS };
    private final View view;
    private final Paint clipPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final PorterDuffXfermode pdMode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);



    private final Path clipPath = new Path();
    private ClipPathManager clipManager = new ClipPathManager();
    private boolean requiersShapeUpdate = true;
    private final RectF rectF = new RectF();

    private final Path path = new Path();



    public RoundedCornersImage(View view) {
        this.view = view;
    }

    @Override
    public void onLayoutChanged() {
        requiersShapeUpdate = true;
        view.postInvalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (requiersShapeUpdate) {
            calculateLayout(canvas.getWidth(), canvas.getHeight());
            requiersShapeUpdate = false;
        }

//        if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.O_MR1){
            canvas.drawPath(clipPath, clipPaint);
//        } else {
//            canvas.drawPath(rectView, clipPaint);
//        }

        if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.O_MR1) {
            view.setLayerType(android.view.View.LAYER_TYPE_HARDWARE, null);
        }
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
                throw new IllegalStateException("This can'' be possible");
            }
        }

        clipPaint.setAntiAlias(true);
        view.setWillNotDraw(false);

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O_MR1) {
            clipPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            view.setLayerType(android.view.View.LAYER_TYPE_SOFTWARE, clipPaint); //Only works for software layers
        } else {
            clipPaint.setXfermode(pdMode);
            view.setLayerType(android.view.View.LAYER_TYPE_SOFTWARE, null); //Only works for software layers
        }

        clipManager.setClipPathCreator((width, height) -> {
            rectF.set(0, 0, width, height);
            return generatePath(rectF);
        });
    }

    private Path generatePath(RectF rect) {
        final Path path = new Path();

        final float left = rect.left;
        final float top = rect.top;
        final float bottom = rect.bottom;
        final float right = rect.right;

        final float minSize = Math.min(rect.width() / 2f, rect.height() / 2f);
        
        if (cornerRadius[0] > minSize) {
            cornerRadius[0] = minSize;
        }
        if (cornerRadius[1] > minSize) {
            cornerRadius[1] = minSize;
        }
        if (cornerRadius[3] > minSize) {
            cornerRadius[3] = minSize;
        }
        if (cornerRadius[2] > minSize) {
            cornerRadius[2] = minSize;
        }

        path.moveTo(left + cornerRadius[0], top);
        path.lineTo(right - cornerRadius[1], top);


        path.arcTo(new RectF(right - cornerRadius[1] * 2f, top, right, top + cornerRadius[1] * 2f), -90, 90);
        path.lineTo(right, bottom - cornerRadius[2]);
        path.arcTo(new RectF(right - cornerRadius[2] * 2f, bottom - cornerRadius[2] * 2f, right, bottom), 0, 90);
        path.lineTo(left + cornerRadius[3], bottom);
        path.arcTo(new RectF(left, bottom - cornerRadius[3] * 2f, left + cornerRadius[3] * 2f, bottom), 90, 90);
        path.lineTo(left, top + cornerRadius[0]);
        path.arcTo(new RectF(left, top, left + cornerRadius[0] * 2f, top + cornerRadius[0] * 2f), 180, 90);
        path.close();

        return path;
    }

    private void calculateLayout(int width, int height) {
        if (clipManager != null) {
            if (width > 0 && height > 0) {
                clipManager.setupClipLayout(width, height);
                clipPath.reset();
                clipPath.set(clipManager.createMask(width, height));
            }
        }

        view.postInvalidate();
    }
}
