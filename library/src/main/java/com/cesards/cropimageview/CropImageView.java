package com.cesards.cropimageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cesards on 20/09/14.
 */
public class CropImageView extends ImageView {

    private CropType cropType = CropType.NONE;

    public CropImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        parseAttributes(attrs);
    }

    public CropImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        parseAttributes(attrs);
    }

    public CropImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        parseAttributes(attrs);
    }

    private void parseAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CropImageView);

        final int crop = a.getInt(R.styleable.CropImageView_crop, CropType.NONE.getCrop());
        if (crop >= 0) {
            setScaleType(ScaleType.MATRIX);
            cropType = CropType.get(crop);
        }
        a.recycle();
    }

    @Override
    protected boolean setFrame(int l, int t, int r, int b) {
        if (!isInEditMode()) {
            computeImageMatrix();
        }
        return super.setFrame(l, t, r, b);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (!isInEditMode()) {
            computeImageMatrix();
        }
    }

    private void computeImageMatrix() {
        final int viewWidth = getWidth() - getPaddingLeft() - getPaddingRight();
        final int viewHeight = getHeight() - getPaddingTop() - getPaddingBottom();

        if (cropType != CropType.NONE && viewHeight > 0 && viewWidth > 0) {
            final Matrix matrix = getImageMatrix();

            int drawableWidth = getDrawable().getIntrinsicWidth();
            int drawableHeight = getDrawable().getIntrinsicHeight();

            final float scaleY = (float) viewHeight / (float) drawableHeight;
            final float scaleX = (float) viewWidth / (float) drawableWidth;
            final float scale = scaleX > scaleY ? scaleX : scaleY;
            matrix.setScale(scale, scale); // Same as doing matrix.reset() and matrix.preScale(...)

            final float xTranslation = getXTranslation(cropType, viewWidth, drawableWidth);
            final float yTranslation = getYTranslation(cropType, viewHeight, drawableHeight);

            switch (cropType) {
                case CENTER_BOTTOM:
                    if (scaleX > scaleY) {
                        xTranslation = 0;
                        yTranslation = viewHeight - (scale * drawableHeight);
                    } else {
                        xTranslation = (float) (((float)viewWidth / 2.0) + ((float)drawableWidth / 2.0)); // Dejamos la view en el medio de la pantalla
                        yTranslation = 0;
                    }
                    matrix.postTranslate(xTranslation, yTranslation);
                    break;

                case LEFT_BOTTOM:
                    if (scaleX > scaleY) {
                        xTranslation = 0;
                        yTranslation = viewHeight - (scale * drawableHeight);
                    } else {
                        xTranslation = 0;
                        yTranslation = 0;
                    }
                    matrix.postTranslate(xTranslation, yTranslation);
                    break;

                case RIGHT_BOTTOM:
                    if (scaleX > scaleY) {
                        xTranslation = 0;
                        yTranslation = viewHeight - (scale * drawableHeight);
                    } else {
                        xTranslation = viewWidth - (drawableWidth * scale);
                        yTranslation = 0;
                    }
                    matrix.postTranslate(xTranslation, yTranslation);
                    break;

                case LEFT_CENTER:
                    if (scaleX > scaleY) {
                        xTranslation = 0;
                        yTranslation = (float) (((float)viewWidth / 2.0) - ((float)drawableWidth / 2.0)); // Dejamos la view en el medio de la pantalla;
                    } else {
                        xTranslation = 0;
                        yTranslation = 0;
                    }
                    matrix.postTranslate(xTranslation, yTranslation);
                    break;

                case RIGHT_CENTER:
                    if (scaleX > scaleY) {
                        xTranslation = 0;
                        yTranslation = (float) (((float)viewWidth / 2.0) - ((float)drawableWidth / 2.0)); // Dejamos la view en el medio de la pantalla;

                    } else {
                        xTranslation = viewWidth - (drawableWidth * scale);
                        yTranslation = 0;
                    }
                    matrix.postTranslate(xTranslation, yTranslation);
                    break;

                case LEFT_TOP:
                    if (scaleX > scaleY) {
                        xTranslation = 0;
                        yTranslation = 0;

                    } else {
                        xTranslation = 0;
                        yTranslation = 0;
                    }
                    matrix.postTranslate(xTranslation, yTranslation);
                    break;

                case CENTER_TOP:
                    if (scaleX > scaleY) {
                        xTranslation = 0;
                        yTranslation = 0;

                    } else {
                        xTranslation = (float) (((float)viewWidth / 2.0) - ((float)drawableWidth / 2.0)); // Dejamos la view en el medio de la pantalla;
                        yTranslation = 0;
                    }
                    matrix.postTranslate(xTranslation, yTranslation);
                    break;

                case RIGHT_TOP:
                    if (scaleX > scaleY) {
                        xTranslation = 0;
                        yTranslation = 0;

                    } else {
                        xTranslation = viewWidth - (drawableWidth * scale);
                        yTranslation = 0;
                    }
                    matrix.postTranslate(xTranslation, yTranslation);
                    break;

            }

            setImageMatrix(matrix);


//            final float diffX = viewWidth - drawableWidth;
//            final float diffY = viewHeight - drawableHeight;

//            final boolean drawableInBounds = diffX > 0 && diffY > 0;

//            if (drawableInBounds) {
                // matrix.postTranslate
//            }

        }
    }

    private float getYTranslation(CropType cropType, int viewHeight, int drawableHeight) {
        return 0;
    }

    private float getXTranslation(CropType cropType, int viewWidth, int drawableWidth) {
        return 0;
    }

    /**
     * Options for cropping the bounds of an image to the bounds of this view.
     */
    public enum CropType {
        NONE(-1),
        LEFT_TOP(0),
        LEFT_CENTER(1),
        LEFT_BOTTOM(2),
        RIGHT_TOP(3),
        RIGHT_CENTER(4),
        RIGHT_BOTTOM(5),
        CENTER_TOP(6),
        CENTER_BOTTOM(7);

        final int cropType;

        // Reverse-lookup map for getting a day from an abbreviation
        private static final Map<Integer, CropType> codeLookup = new HashMap<>();

        static {
            for (CropType ft : CropType.values()) {
                codeLookup.put(ft.getCrop(), ft);
            }
        }

        CropType(int ct) {
            cropType = ct;
        }

        public int getCrop() {
            return cropType;
        }

        public static CropType get(int number) {
            return codeLookup.get(number);
        }
    }

}
