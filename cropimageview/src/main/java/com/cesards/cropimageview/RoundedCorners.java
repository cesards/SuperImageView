package com.cesards.cropimageview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;

import com.cesards.cropimageview.rounded_corners.ClipPathManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.view.ViewCompat;

public final class RoundedCorners implements RoundedCornerImageViewHook {

    @Nullable
    public static RoundedCorners








    @NonNull private final ImageView imageView;
    private final Paint clipPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Path clipPath = new Path();
    private final PorterDuffXfermode pdMode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
    @Nullable protected Drawable drawable = null;
    private ClipPathManager clipManager = new ClipPathManager();
    private boolean requiersShapeUpdate = true;
    private Bitmap clipBitmap;
    final Path rectView = new Path();
    private final RectF rectF = new RectF();
    private int topLeftRadius = 15;
    private int topRightRadius= 15;
    private int bottomRightRadius= 15;
    private int bottomLeftRadius= 15;

    private final RectF borderRectF = new RectF();
    private final Path borderPath = new Path();


    public RoundedCorners(@NonNull ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    public void setup(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        final TypedArray a = imageView.getContext().obtainStyledAttributes(attributeSet, R.styleable.civ_CropImageView);

//        final int cornerRadius = a.getInt(R.styleable.civ_CropImageView_civ_cornerRadius, Corner.NONE);
//        if (cornerRadius != Corner.NONE) {
//            this.cornerRadius[Corner.TOP_LEFT] = cornerRadius;
//            this.cornerRadius[Corner.TOP_RIGHT] = cornerRadius;
//            this.cornerRadius[Corner.BOTTOM_LEFT] = cornerRadius;
//            this.cornerRadius[Corner.BOTTOM_RIGHT] = cornerRadius;
//        } else {
//            this.cornerRadius[Corner.TOP_LEFT] = a.getInt(R.styleable.civ_CropImageView_civ_cornerRadiusTopLeft, Corner.NONE);
//            this.cornerRadius[Corner.TOP_RIGHT] = a.getInt(R.styleable.civ_CropImageView_civ_cornerRadiusTopRight, Corner.NONE);
//            this.cornerRadius[Corner.BOTTOM_LEFT] = a.getInt(R.styleable.civ_CropImageView_civ_cornerRadiusBottomLeft, Corner.NONE);
//            this.cornerRadius[Corner.BOTTOM_RIGHT] = a.getInt(R.styleable.civ_CropImageView_civ_cornerRadiusBottomRight, Corner.NONE);
//        }
//        for (int i = 0, len = this.cornerRadius.length; i < len; i++) {
//            if (this.cornerRadius[i] < 0) {
//                this.cornerRadius[i] = DEFAULT_RADIUS;
//            }
//        }


        clipPaint.setAntiAlias(true);
        imageView.setWillNotDraw(false);
        clipPaint.setColor(Color.BLUE);
        clipPaint.setStyle(Paint.Style.FILL);
        clipPaint.setStrokeWidth(1);

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O_MR1) {
            clipPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            imageView.setLayerType(View.LAYER_TYPE_SOFTWARE, clipPaint); //Only works for software layers
        } else {
            clipPaint.setXfermode(pdMode);
            imageView.setLayerType(View.LAYER_TYPE_SOFTWARE, null); //Only works for software layers
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

        a.recycle();
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

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
        requiresShapeUpdate();
    }

    public void setDrawable(int redId) {
        setDrawable(AppCompatResources.getDrawable(imageView.getContext(), redId));
    }

    public void requiresShapeUpdate() {
        borderPath.set(generatePath(borderRectF,
                topLeftRadius,
                topRightRadius,
                bottomRightRadius,
                bottomLeftRadius
        ));
        requiersShapeUpdate = true;
        imageView.postInvalidate();
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
            imageView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }
    }

    private void calculateLayout(int width, int height) {
        rectView.reset();
        rectView.addRect(0f, 0f, 1f * imageView.getWidth(), 1f * imageView.getHeight(), Path.Direction.CW);

        if (clipManager != null) {
            if (width > 0 && height > 0) {
                clipManager.setupClipLayout(width, height);
                clipPath.reset();
                clipPath.set(clipManager.createMask(width, height));

                //invert the path for android P
                if(Build.VERSION.SDK_INT > Build.VERSION_CODES.O_MR1) {
                    final boolean success = rectView.op(clipPath, Path.Op.DIFFERENCE);
                }

                //this needs to be fixed for 25.4.0
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && ViewCompat.getElevation(imageView) > 0f) {
                    try {
                        imageView.setOutlineProvider(getOutlineProvider());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        imageView.postInvalidate();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ViewOutlineProvider getOutlineProvider() {
        return new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                final Path shadowConvexPath = clipManager.getShadowConvexPath();
                if (shadowConvexPath != null) {
                    try {
                        outline.setConvexPath(shadowConvexPath);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        };

    }









//    private static final float DEFAULT_RADIUS = 0f;
//
//    private final float[] cornerRadius = new float[] {
//            DEFAULT_RADIUS,
//            DEFAULT_RADIUS,
//            DEFAULT_RADIUS,
//            DEFAULT_RADIUS
//    };
//    private Drawable backgroundDrawable; //? needed? @Nullable ? @NonNull?
//    private Drawable drawable; //? needed? @Nullable ? @NonNull?private boolean mMutateBackground = false;



    /**
     * @return the largest corner radius.
     */
//    public float getCornerRadiuses() {
//        return getMaxCornerRadius();
//    }

    /**
     * @return the largest corner radius.
     */
//    public float getMaxCornerRadius() {
//        float maxRadius = DEFAULT_RADIUS;
//        for (int i = cornerRadius.length - 1; i >= 0; i--) {
//            maxRadius = Math.max(cornerRadius[i], maxRadius);
//        }
//        return maxRadius;
//    }

    /**
     * Get the imageCorner radius of a specified imageCorner.
     *
     * @param imageCorner the imageCorner.
     * @return the radius.
     */
//    public float getCornerRadius(@Corner int imageCorner) {
//        return cornerRadius[imageCorner];
//    }

    /**
     * Set the corner radii of all corners in px.
     *
     * @param radius the radius to set.
     */
//    public void setCornerRadiuses(int radius) {
//        setCornerRadius(radius, radius, radius, radius);
//    }

    /**
     * Set all the corner radii from a dimension resource id.
     *
     * @param resId dimension resource id of radii.
     */
//    public void setCornerRadiusDimen(@DimenRes int resId) {
//        float radius = imageView.getResources().getDimension(resId);
//        setCornerRadius(radius, radius, radius, radius);
//    }

    /**
     * Set the corner radius of a specific corner from a dimension resource id.
     *
     * @param corner the corner to set.
     * @param resId the dimension resource id of the corner radius.
     */
//    public void setCornerRadiusDimen(@Corner int corner, @DimenRes int resId) {
//        if (corner == Corner.NONE) {
//            return;
//        }
//        setCornerRadius(corner, imageView.getResources().getDimensionPixelSize(resId));
//    }

    /**
     * Set the corner radii of all corners in px.
     *
     * @param radius the radius to set.
     */
//    public void setCornerRadius(float radius) {
//        setCornerRadius(radius, radius, radius, radius);
//    }

    /**
     * Set the corner radius of a specific corner in px.
     *
     * @param corner the corner to set.
     * @param radius the corner radius to set in px.
     */
//    public void setCornerRadius(@Corner int corner, float radius) {
//        if (cornerRadius[corner] == radius) {
//            return;
//        }
//
//        cornerRadius[corner] = radius;
//
//        updateDrawableAttrs();
//        updateBackgroundDrawableAttrs(false);
//        imageView.invalidate();
//    }

    /**
     * Set the corner radii of each corner individually. Currently only one unique nonzero value is
     * supported.
     *
//     * @param topLeft radius of the top left corner in px.
//     * @param topRight radius of the top right corner in px.
//     * @param bottomRight radius of the bottom right corner in px.
//     * @param bottomLeft radius of the bottom left corner in px.
     */
//    public void setCornerRadius(float topLeft, float topRight, float bottomLeft, float bottomRight) {
//        if (cornerRadius[Corner.TOP_LEFT] == topLeft && cornerRadius[Corner.TOP_RIGHT] == topRight && cornerRadius[Corner.BOTTOM_RIGHT] == bottomRight && cornerRadius[Corner.BOTTOM_LEFT] == bottomLeft) {
//            return;
//        }
//
//        cornerRadius[Corner.TOP_LEFT] = topLeft;
//        cornerRadius[Corner.TOP_RIGHT] = topRight;
//        cornerRadius[Corner.BOTTOM_LEFT] = bottomLeft;
//        cornerRadius[Corner.BOTTOM_RIGHT] = bottomRight;
//
//        updateDrawableAttrs();
//        updateBackgroundDrawableAttrs(false);
//        imageView.invalidate();
//    }







    @Override
    public Drawable modifiedDrawable(Drawable drawable) {
//        this.drawable = SuperDrawable.fromDrawable(drawable);
        updateDrawableAttrs();
        return this.drawable;
    }

    @Override
    public Drawable modifiedDrawable(Bitmap bitmap) {
//        this.drawable = SuperDrawable.fromBitmap(bitmap);
        updateDrawableAttrs();
        return this.drawable;
    }

    @Override
    public void notifyDrawableStateChanges() {
//        imageView.invalidate();
    }

//    @Override
//    public void delegateScaleType(ImageView.ScaleType scaleType) {
//        // TODO: 8/28/18 CHECK IF REALLY NEEDED
//        if (imageView.getScaleType() != scaleType) {
//            updateDrawableAttrs();
//            updateBackgroundDrawableAttrs(false);
//        }
//        // parent scaleType invalidates.
//    }

    private void updateDrawableAttrs() {
        updateAttrs(drawable);
    }

    private void updateAttrs(Drawable drawable) {
        if (drawable == null) { return; }

        // TODO how this can be possible?
//        if (drawable instanceof SuperDrawable) {
//            ((SuperDrawable) drawable).setCornerRadius(
//                    cornerRadius[Corner.TOP_LEFT],
//                    cornerRadius[Corner.TOP_RIGHT],
//                    cornerRadius[Corner.BOTTOM_LEFT],
//                    cornerRadius[Corner.BOTTOM_RIGHT]
//            );
//        }
    }



//    private void updateBackgroundDrawableAttrs(boolean convert) {
//        if (mutateBackground) {
//            if (convert) {
////                backgroundDrawable = SuperDrawable.fromDrawable(backgroundDrawable);
//            }
//            updateAttrs(backgroundDrawable);
//        }
//    }









//    @Override
//    public Drawable delegateSetImageBitmap(Bitmap bm) {
//        drawable = SuperDrawable.fromBitmap(bm);
//        updateDrawableAttrs();
//        imageView.setImageDrawable(this.drawable);
//        return drawable;
//    }








//    @Override
//    public void setImageBitmap(Bitmap bm) {
//
//
//        super.setImageDrawable(drawable);
//    }
//
//    @Override
//    public void setImageResource(@DrawableRes int resId) {
//        if (resource != resId) {
//            resource = resId;
//            drawable = resolveResource();
//            updateDrawableAttrs();
//            super.setImageDrawable(drawable);
//        }
//    }
//
//    @Override
//    public void setImageURI(Uri uri) {
//        super.setImageURI(uri);
//        setImageDrawable(getDrawable());
//    }
//
//    private Drawable resolveResource() {
//        Resources rsrc = getResources();
//        if (rsrc == null) { return null; }
//
//        Drawable d = null;
//
//        if (resource != 0) {
//            try {
//                d = rsrc.getDrawable(resource);
//            } catch (Exception e) {
//                Log.w(TAG, "Unable to find resource: " + resource, e);
//                // Don't try again.
//                resource = 0;
//            }
//        }
//        return SuperDrawable.fromDrawable(d);
//    }
//
//    @Override
//    public void setBackground(Drawable background) {
//        setBackgroundDrawable(background);
//    }
//
//    @Override
//    public void setBackgroundResource(@DrawableRes int resId) {
//        if (backgroundResource != resId) {
//            backgroundResource = resId;
//            backgroundDrawable = resolveBackgroundResource();
//            setBackgroundDrawable(backgroundDrawable);
//        }
//    }
//
//    @Override
//    public void setBackgroundColor(int color) {
//        backgroundDrawable = new ColorDrawable(color);
//        setBackgroundDrawable(backgroundDrawable);
//    }
//
//    private Drawable resolveBackgroundResource() {
//        Resources rsrc = getResources();
//        if (rsrc == null) { return null; }
//
//        Drawable d = null;
//
//        if (backgroundResource != 0) {
//            try {
//                d = rsrc.getDrawable(backgroundResource);
//            } catch (Exception e) {
//                Log.w(TAG, "Unable to find resource: " + backgroundResource, e);
//                // Don't try again.
//                backgroundResource = 0;
//            }
//        }
//        return SuperDrawable.fromDrawable(d);
//    }
//
//    @Override
//    @Deprecated
//    public void setBackgroundDrawable(Drawable background) {
//        backgroundDrawable = background;
//        updateBackgroundDrawableAttrs(true);
//        //noinspection deprecation
//        super.setBackgroundDrawable(backgroundDrawable);
//    }



//    /**
//     * If {@code true}, we will also round the background drawable according to the settings on this
//     * ImageView.
//     *
//     * @return whether the background is mutated.
//     */
//    public boolean mutatesBackground() {
//        return mMutateBackground;
//    }
//
//    /**
//     * Set whether the {@link RoundedImageView} should round the background drawable according to
//     * the settings in addition to the source drawable.
//     *
//     * @param mutate true if this view should mutate the background drawable.
//     */
//    public void mutateBackground(boolean mutate) {
//        if (mMutateBackground == mutate) { return; }
//
//        mMutateBackground = mutate;
//        updateBackgroundDrawableAttrs(true);
//        invalidate();
//    }
}
