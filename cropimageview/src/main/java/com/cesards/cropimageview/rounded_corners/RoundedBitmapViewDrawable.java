package com.cesards.cropimageview.rounded_corners;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * shows a bitmap as if it had rounded corners. based on :
 * http://rahulswackyworld.blogspot.co.il/2013/04/android-drawables-with-rounded_7.html
 * easy alternative from support library: RoundedBitmapDrawableFactory.create( ...) ;
 */
//public class RoundedBitmapViewDrawable extends RoundedBitmapDrawable {
class RoundedBitmapViewDrawable extends Drawable {

    @Override
    public void draw(@NonNull Canvas canvas) {

    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }






    //
//    private final BitmapShader bitmapShader;
//    private final Paint p;
//    private final RectF rect;
//    private final float borderRadius;
//
//    public RoundedBitmapViewDrawable(final Resources resources, final Bitmap bitmap, final float borderRadius) {
//        super(resources, bitmap);
//        bitmapShader = new BitmapShader(getBitmap(), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//        final Bitmap b = getBitmap();
//        p = getPaint();
//        p.setAntiAlias(true);
//        p.setShader(bitmapShader);
//        final int w = b.getWidth(), h = b.getHeight();
//        rect = new RectF(0, 0, w, h);
//        this.borderRadius = borderRadius < 0 ? 0.15f * Math.min(w, h) : borderRadius;
//    }
//
//    @Override
//    public void draw(final Canvas canvas) {
//        canvas.drawRoundRect(rect, borderRadius, borderRadius, p);
//    }
//




//    private static final float DEFAULT_RADIUS = 0f;
//
//    private final RectF bounds = new RectF();
//    private final RectF drawableRect = new RectF();
//    private final RectF bitmapRect = new RectF();
//    private final Bitmap bitmap;
//    private final Paint bitmapPaint;
//    private final int bitmapWidth;
//    private final int bitmapHeight;
//    private final Matrix shaderMatrix = new Matrix();
//    private final RectF squareCornersRect = new RectF();
//    private Shader.TileMode tileModeX = Shader.TileMode.CLAMP;
//    private Shader.TileMode tileModeY = Shader.TileMode.CLAMP;
//    private boolean mRebuildShader = true;
//    private float cornerRadius = 0f;
//    private final boolean[] cornerRadiusRounded = new boolean[] { true, true, true, true };
//    private ImageView.ScaleType scaleType = ImageView.ScaleType.FIT_CENTER;
//
//    public RoundedBitmapViewDrawable(Bitmap bitmap) {
//        this.bitmap = bitmap;
//
//        bitmapWidth = bitmap.getWidth();
//        bitmapHeight = bitmap.getHeight();
//        bitmapRect.set(0, 0, bitmapWidth, bitmapHeight);
//
//        bitmapPaint = new Paint();
//        bitmapPaint.setStyle(Paint.Style.FILL);
//        bitmapPaint.setAntiAlias(true);
//    }
//
//    public static Drawable fromDrawable(Drawable drawable) {
//        if (drawable != null) {
//            if (drawable instanceof RoundedBitmapViewDrawable) {
//                // just return if it's already a RoundedBitmapViewDrawable
//                return drawable;
//            }
//
//            // try to get a bitmap from the drawable and
//            Bitmap bm = drawableToBitmap(drawable);
//            if (bm != null) {
//                return new RoundedBitmapViewDrawable(bm);
//            }
//        }
//        return drawable;
//    }
//
//    public static RoundedBitmapViewDrawable fromBitmap(Bitmap bitmap) {
//        if (bitmap != null) {
//            return new RoundedBitmapViewDrawable(bitmap);
//        } else {
//            return null;
//        }
//    }
//
//    public static Bitmap drawableToBitmap(Drawable drawable) {
//        if (drawable instanceof BitmapDrawable) {
//            return ((BitmapDrawable) drawable).getBitmap();
//        }
//
//        Bitmap bitmap;
//        int width = Math.max(drawable.getIntrinsicWidth(), 2);
//        int height = Math.max(drawable.getIntrinsicHeight(), 2);
//        try {
//            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
//            Canvas canvas = new Canvas(bitmap);
//            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
//            drawable.draw(canvas);
//        } catch (Exception e) {
//            e.printStackTrace();
//            Log.w(RoundedBitmapViewDrawable.class.getSimpleName(), "Failed to create bitmap from drawable!");
//            bitmap = null;
//        }
//
//        return bitmap;
//    }
//
//
//
//    public Bitmap getSourceBitmap() {
//        return bitmap;
//    }
//
//    /**
//     * @return the corner radius.
//     */
//    public float getCornerRadius() {
//        return cornerRadius;
//    }
//
//    /**
//     * @param corner the specific corner to get radius of.
//     * @return the corner radius of the specified corner.
//     */
//    public float getCornerRadius(@Corner int corner) {
//        return cornerRadiusRounded[corner] ? cornerRadius : 0f;
//    }
//
//    /**
//     * Sets all corners to the specified radius.
//     *
//     * @param radius the radius.
//     * @return the {@link RoundedBitmapViewDrawable} for chaining.
//     */
//    public RoundedBitmapViewDrawable setCornerRadius(float radius) {
//        setCornerRadius(radius, radius, radius, radius);
//        return this;
//    }
//
//    /**
//     * Sets the corner radius of one specific corner.
//     *
//     * @param corner the corner.
//     * @param radius the radius.
//     * @return the {@link RoundedBitmapViewDrawable} for chaining.
//     */
//    public RoundedBitmapViewDrawable setCornerRadius(@Corner int corner, float radius) {
//        if (radius != 0 && cornerRadius != 0 && cornerRadius != radius) {
//            throw new IllegalArgumentException("Multiple nonzero corner radii not yet supported.");
//        }
//
//        if (radius == 0) {
//            if (only(corner, cornerRadiusRounded)) {
//                cornerRadius = 0;
//            }
//            cornerRadiusRounded[corner] = false;
//        } else {
//            if (cornerRadius == 0) {
//                cornerRadius = radius;
//            }
//            cornerRadiusRounded[corner] = true;
//        }
//
//        return this;
//    }
//
//    /**
//     * Sets the corner radii of all the corners.
//     *
//     * @param topLeft top left corner radius.
//     * @param topRight top right corner radius
//     * @param bottomRight bototm right corner radius.
//     * @param bottomLeft bottom left corner radius.
//     * @return the {@link RoundedBitmapViewDrawable} for chaining.
//     */
//    public RoundedBitmapViewDrawable setCornerRadius(
//            float topLeft,
//            float topRight,
//            float bottomRight,
//            float bottomLeft
//    ) {
//        Set<Float> radiusSet = new HashSet<>(4);
//        radiusSet.add(topLeft);
//        radiusSet.add(topRight);
//        radiusSet.add(bottomRight);
//        radiusSet.add(bottomLeft);
//
//        radiusSet.remove(0f);
//
//        if (radiusSet.size() > 1) {
//            throw new IllegalArgumentException("Multiple nonzero corner radii not yet supported.");
//        }
//
//        if (!radiusSet.isEmpty()) {
//            float radius = radiusSet.iterator().next();
//            if (Float.isInfinite(radius) || Float.isNaN(radius) || radius < 0) {
//                throw new IllegalArgumentException("Invalid radius value: " + radius);
//            }
//            cornerRadius = radius;
//        } else {
//            cornerRadius = 0f;
//        }
//
//        cornerRadiusRounded[Corner.TOP_LEFT] = topLeft > 0;
//        cornerRadiusRounded[Corner.TOP_RIGHT] = topRight > 0;
//        cornerRadiusRounded[Corner.BOTTOM_RIGHT] = bottomRight > 0;
//        cornerRadiusRounded[Corner.BOTTOM_LEFT] = bottomLeft > 0;
//        return this;
//    }
//
//    public ImageView.ScaleType getScaleType() {
//        return scaleType;
//    }
//
//    public RoundedBitmapViewDrawable setScaleType(ImageView.ScaleType scaleType) {
//        if (scaleType == null) {
//            scaleType = ImageView.ScaleType.FIT_CENTER;
//        }
//        if (this.scaleType != scaleType) {
//            this.scaleType = scaleType;
//            updateShaderMatrix();
//        }
//        return this;
//    }
//
//    public Shader.TileMode getTileModeX() {
//        return tileModeX;
//    }
//
//    public RoundedBitmapViewDrawable setTileModeX(Shader.TileMode tileModeX) {
//        if (this.tileModeX != tileModeX) {
//            this.tileModeX = tileModeX;
//            mRebuildShader = true;
//            invalidateSelf();
//        }
//        return this;
//    }
//
//    public Shader.TileMode getTileModeY() {
//        return tileModeY;
//    }
//
//    public RoundedBitmapViewDrawable setTileModeY(Shader.TileMode tileModeY) {
//        if (this.tileModeY != tileModeY) {
//            this.tileModeY = tileModeY;
//            mRebuildShader = true;
//            invalidateSelf();
//        }
//        return this;
//    }
//
//    public Bitmap toBitmap() {
//        return drawableToBitmap(this);
//    }
//
//    @Override
//    protected void onBoundsChange(@NonNull Rect bounds) {
//        super.onBoundsChange(bounds);
//
//        bounds.set(bounds);
//
//        updateShaderMatrix();
//    }
//
//    @Override
//    public void draw(@NonNull Canvas canvas) {
//
//    }
//
//    //    @Override
////    public void draw(@NonNull Canvas canvas) {
//////        bitmapPaint.setShader(mShader);
//////        canvas.drawRoundRect(drawableRect, cornerRadius, cornerRadius, bitmapPaint);
////        if (mRebuildShader) {
////            BitmapShader bitmapShader = new BitmapShader(bitmap, tileModeX, tileModeY);
////            if (tileModeX == Shader.TileMode.CLAMP && tileModeY == Shader.TileMode.CLAMP) {
////                bitmapShader.setLocalMatrix(shaderMatrix);
////            }
////            bitmapPaint.setShader(bitmapShader);
////            mRebuildShader = false;
////        }
////
////        if (any(cornerRadiusRounded)) {
////            float radius = cornerRadius;
////            canvas.drawRoundRect(drawableRect, radius, radius, bitmapPaint);
////            redrawBitmapForSquareCorners(canvas);
////        } else {
////            canvas.drawRect(drawableRect, bitmapPaint);
////        }
////    }
//
//    @Override
//    public int getAlpha() {
//        return bitmapPaint.getAlpha();
//    }
//
//    @Override
//    public void setAlpha(int alpha) {
//        bitmapPaint.setAlpha(alpha);
//        invalidateSelf();
//    }
//
//    @Override
//    public ColorFilter getColorFilter() {
//        return bitmapPaint.getColorFilter();
//    }
//
//    @Override
//    public void setColorFilter(ColorFilter cf) {
//        bitmapPaint.setColorFilter(cf);
//        invalidateSelf();
//    }
//
//    @Override
//    public void setDither(boolean dither) {
//        bitmapPaint.setDither(dither);
//        invalidateSelf();
//    }
//
//    @Override
//    public void setFilterBitmap(boolean filter) {
//        bitmapPaint.setFilterBitmap(filter);
//        invalidateSelf();
//    }
//
//    @Override
//    public int getIntrinsicWidth() {
//        return bitmapWidth;
//    }
//
//    @Override
//    public int getIntrinsicHeight() {
//        return bitmapHeight;
//    }
//
//    private void updateShaderMatrix() {
//        float scale;
//        float dx;
//        float dy;
//
//        switch (scaleType) {
//            case CENTER:
//                shaderMatrix.reset();
//                shaderMatrix.setTranslate(( bitmapWidth * 0.5f + 0.5f), bitmapHeight * 0.5f + 0.5f);
//                break;
//
//            case CENTER_CROP:
//                break;
//
//            case CENTER_INSIDE:
//                shaderMatrix.reset();
//
//                if (bitmapWidth <= bounds.width() && bitmapHeight <= bounds.height()) {
//                    scale = 1.0f;
//                } else {
//                    scale = Math.min(bounds.width() / (float) bitmapWidth, bounds.height() / (float) bitmapHeight);
//                }
//
//                dx = (int) ((bounds.width() - bitmapWidth * scale) * 0.5f + 0.5f);
//                dy = (int) ((bounds.height() - bitmapHeight * scale) * 0.5f + 0.5f);
//
//                shaderMatrix.setScale(scale, scale);
//                shaderMatrix.postTranslate(dx, dy);
//                break;
//
//            default:
//            case FIT_CENTER:
//                shaderMatrix.setRectToRect(bitmapRect, bounds, Matrix.ScaleToFit.CENTER);
//                break;
//
//            case FIT_END:
//                shaderMatrix.setRectToRect(bitmapRect, bounds, Matrix.ScaleToFit.END);
//                break;
//
//            case FIT_START:
//                shaderMatrix.setRectToRect(bitmapRect, bounds, Matrix.ScaleToFit.START);
//                break;
//
//            case FIT_XY:
//                shaderMatrix.reset();
//                break;
//        }
//
//        mRebuildShader = true;
//    }
//
//    private void redrawBitmapForSquareCorners(Canvas canvas) {
//        if (all(cornerRadiusRounded)) {
//            // no square corners
//            return;
//        }
//
//        if (cornerRadius == 0) {
//            return; // no round corners
//        }
//
//        float left = drawableRect.left;
//        float top = drawableRect.top;
//        float right = left + drawableRect.width();
//        float bottom = top + drawableRect.height();
//        float radius = cornerRadius;
//
//        if (!cornerRadiusRounded[Corner.TOP_LEFT]) {
//            squareCornersRect.set(left, top, left + radius, top + radius);
//            canvas.drawRect(squareCornersRect, bitmapPaint );
//        }
//
//        if (!cornerRadiusRounded[Corner.TOP_RIGHT]) {
//            squareCornersRect.set(right - radius, top, right, radius);
//            canvas.drawRect(squareCornersRect, bitmapPaint );
//        }
//
//        if (!cornerRadiusRounded[Corner.BOTTOM_RIGHT]) {
//            squareCornersRect.set(right - radius, bottom - radius, right, bottom);
//            canvas.drawRect(squareCornersRect, bitmapPaint );
//        }
//
//        if (!cornerRadiusRounded[Corner.BOTTOM_LEFT]) {
//            squareCornersRect.set(left, bottom - radius, left + radius, bottom);
//            canvas.drawRect(squareCornersRect, bitmapPaint );
//        }
//    }
//
//    private static boolean only(int index, boolean[] booleans) {
//        for (int i = 0, len = booleans.length; i < len; i++) {
//            if (booleans[i] != (i == index)) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private static boolean any(boolean[] booleans) {
//        for (boolean b : booleans) {
//            if (b) { return true; }
//        }
//        return false;
//    }
//
//    private static boolean all(boolean[] booleans) {
//        for (boolean b : booleans) {
//            if (b) { return false; }
//        }
//        return true;
//    }
}
