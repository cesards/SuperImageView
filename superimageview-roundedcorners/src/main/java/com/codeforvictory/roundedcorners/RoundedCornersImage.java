package com.codeforvictory.superimageviewroundedcorners;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;

import com.cesards.cropimageview.RoundedCorners;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

public final class RoundedCornersImage implements RoundedCorners {

    private static final float DEFAULT_RADIUS = 0f;

    private final float[] cornerRadius = new float[]{ DEFAULT_RADIUS, DEFAULT_RADIUS, DEFAULT_RADIUS, DEFAULT_RADIUS };
    private final View view;


    private final Paint clipPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Path clipPath = new Path();
    private final PorterDuffXfermode pdMode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
    @Nullable protected Drawable drawable = null;
    private ClipPathManager clipManager = new ClipPathManager();
    private boolean requiersShapeUpdate = true;
    final Path rectView = new Path();
    private final RectF rectF = new RectF();
    private int topLeftRadius = 30;
    private int topRightRadius= 30;
    private int bottomRightRadius= 30;
    private int bottomLeftRadius= 30;
    private final RectF borderRectF = new RectF();
    private final Path borderPath = new Path();

    public RoundedCornersImage(View view) {
        this.view = view;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
        requiresShapeUpdate();
    }

    public void setDrawable(int redId) {
        setDrawable(ContextCompat.getDrawable(view.getContext(), redId));
    }

    public void requiresShapeUpdate() {
        borderPath.set(generatePath(borderRectF, topLeftRadius, topRightRadius, bottomRightRadius, bottomLeftRadius));
        requiersShapeUpdate = true;
        view.postInvalidate();
    }

    public void dispatchOnDraw(Canvas canvas) {
        if (requiersShapeUpdate) {
            calculateLayout(canvas.getWidth(), canvas.getHeight());
            requiersShapeUpdate = false;
        }

        if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.O_MR1){
            canvas.drawPath(clipPath, clipPaint);
        } else {
            canvas.drawPath(rectView, clipPaint);
        }

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
        final int corners = a.getInt(R.styleable.siv_RoundedCorners_siv_cornerRadius, Corner.NONE);
        if (corners != Corner.NONE) {
            cornerRadius[Corner.TOP_LEFT] = corners;
            cornerRadius[Corner.TOP_RIGHT] = corners;
            cornerRadius[Corner.BOTTOM_LEFT] = corners;
            cornerRadius[Corner.BOTTOM_RIGHT] = corners;
        } else {
            cornerRadius[Corner.TOP_LEFT] = a.getInt(R.styleable.siv_RoundedCorners_siv_cornerRadiusTopLeft, Corner.NONE);
            cornerRadius[Corner.TOP_RIGHT] = a.getInt(R.styleable.siv_RoundedCorners_siv_cornerRadiusTopRight, Corner.NONE);
            cornerRadius[Corner.BOTTOM_LEFT] = a.getInt(R.styleable.siv_RoundedCorners_siv_cornerRadiusBottomLeft, Corner.NONE);
            cornerRadius[Corner.BOTTOM_RIGHT] = a.getInt(R.styleable.siv_RoundedCorners_siv_cornerRadiusBottomRight, Corner.NONE);
        }
        a.recycle();

        for (int i = 0, len = this.cornerRadius.length; i < len; i++) {
            if (this.cornerRadius[i] < 0) {
                throw new IllegalStateException("This can'' be possible");
            }
        }

        clipPaint.setAntiAlias(true);
        view.setWillNotDraw(false);
        clipPaint.setColor(Color.BLUE);
        clipPaint.setStyle(Paint.Style.FILL);
        clipPaint.setStrokeWidth(1);

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O_MR1) {
            clipPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            view.setLayerType(android.view.View.LAYER_TYPE_SOFTWARE, clipPaint); //Only works for software layers
        } else {
            clipPaint.setXfermode(pdMode);
            view.setLayerType(android.view.View.LAYER_TYPE_SOFTWARE, null); //Only works for software layers
        }

        clipManager.setClipPathCreator(new ClipPathManager.ClipPathCreator() {
            @Override
            public Path createClipPath(int width, int height) {
                rectF.set(0, 0, width, height);
                return generatePath(rectF,
                        Math.min(topLeftRadius, Math.min(width, height)),
                        Math.min(topRightRadius, Math.min(width, height)),
                        Math.min(bottomRightRadius, Math.min(width, height)),
                        Math.min(bottomLeftRadius, Math.min(width, height))
                );

            }

            @Override
            public boolean requiresBitmap() {
                return false;
            }
        });
    }

    private Path generatePath(RectF rect, float topLeftRadius, float topRightRadius, float bottomRightRadius, float bottomLeftRadius) {
        return generatePath(false, rect, topLeftRadius, topRightRadius, bottomRightRadius, bottomLeftRadius);
    }

    private Path generatePath(boolean useBezier, RectF rect, float topLeftRadius, float topRightRadius, float bottomRightRadius, float bottomLeftRadius) {
        final Path path = new Path();

        final float left = rect.left;
        final float top = rect.top;
        final float bottom = rect.bottom;
        final float right = rect.right;

        final float minSize = Math.min(rect.width() / 2f, rect.height() / 2f);

        topLeftRadius = topLeftRadius < 0 ? 0 : topLeftRadius;
        topRightRadius = topRightRadius < 0 ? 0 : topRightRadius;
        bottomLeftRadius = bottomLeftRadius < 0 ? 0 : bottomLeftRadius;
        bottomRightRadius = bottomRightRadius < 0 ? 0 : bottomRightRadius;

        if (topLeftRadius > minSize) {
            topLeftRadius = minSize;
        }
        if (topRightRadius > minSize) {
            topRightRadius = minSize;
        }
        if (bottomLeftRadius > minSize) {
            bottomLeftRadius = minSize;
        }
        if (bottomRightRadius > minSize) {
            bottomRightRadius = minSize;
        }

        path.moveTo(left + topLeftRadius, top);
        path.lineTo(right - topRightRadius, top);

        //float left, float top, float right, float bottom, float startAngle, float sweepAngle, boolean forceMoveTo
        if (useBezier) {
            path.quadTo(right, top, right, top + topRightRadius);
        } else {
            path.arcTo(new RectF(right - topRightRadius * 2f, top, right, top + topRightRadius * 2f), -90, 90);
        }
        path.lineTo(right, bottom - bottomRightRadius);
        if (useBezier) {
            path.quadTo(right, bottom, right - bottomRightRadius, bottom);
        } else {
            path.arcTo(new RectF(right - bottomRightRadius * 2f, bottom - bottomRightRadius * 2f, right, bottom), 0, 90);
        }
        path.lineTo(left + bottomLeftRadius, bottom);
        if (useBezier) {
            path.quadTo(left, bottom, left, bottom - bottomLeftRadius);
        } else {
            path.arcTo(new RectF(left, bottom - bottomLeftRadius * 2f, left + bottomLeftRadius * 2f, bottom), 90, 90);
        }
        path.lineTo(left, top + topLeftRadius);
        if (useBezier) {
            path.quadTo(left, top, left + topLeftRadius, top);
        } else {
            path.arcTo(new RectF(left, top, left + topLeftRadius * 2f, top + topLeftRadius * 2f), 180, 90);
        }
        path.close();

        return path;
    }

    private void calculateLayout(int width, int height) {
        rectView.reset();
        rectView.addRect(0f, 0f, 1f * view.getWidth(), 1f * view.getHeight(), Path.Direction.CW);

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
