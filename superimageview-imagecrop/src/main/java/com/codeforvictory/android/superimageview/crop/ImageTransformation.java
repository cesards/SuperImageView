package com.codeforvictory.android.superimageview.crop;

import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Build;

import com.codeforvictory.android.superimageview.Crop;

import androidx.annotation.Nullable;

final class ImageTransformation {

    private final Crop.View view;
    private final CompatMatrix compatMatrix;

    ImageTransformation(Crop.View view) {
        this.view = view;

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
            compatMatrix = new PreAPI18Matrix(view);
        } else {
            compatMatrix = new API18Matrix(view);
        }
    }

    void compute(@CropType int cropType) {
        int viewWidth = view.getWidth() - view.getPaddingLeft() - view.getPaddingRight();
        int viewHeight = view.getHeight() - view.getPaddingTop() - view.getPaddingBottom();

        if (cropType != CropType.NONE && viewHeight > 0 && viewWidth > 0) {
            Matrix matrix = compatMatrix.matrix(cropType);

            Drawable drawable = view.getDrawable();
            int drawableWidth = drawable.getIntrinsicWidth();
            int drawableHeight = drawable.getIntrinsicHeight();

            float scaleY = (float) viewHeight / (float) drawableHeight;
            float scaleX = (float) viewWidth / (float) drawableWidth;
            float scale = scaleX > scaleY ? scaleX : scaleY;
            matrix.setScale(scale, scale); // Same as doing matrix.reset() and matrix.preScale(...)

            boolean verticalImageMode = scaleX > scaleY;

            float postDrawableWidth = drawableWidth * scale;
            float xTranslation = getXTranslation(cropType, viewWidth, postDrawableWidth, verticalImageMode);
            float postDrawabeHeigth = drawableHeight * scale;
            float yTranslation = getYTranslation(cropType, viewHeight, postDrawabeHeigth, verticalImageMode);

            matrix.postTranslate(xTranslation, yTranslation);
            view.setImageMatrix(matrix);
        }
    }

    private float getYTranslation(
            @CropType int cropType,
            int viewHeight,
            float postDrawableHeight,
            boolean verticalImageMode
    ) {
        if (verticalImageMode) {
            switch (cropType) {
                case CropType.CENTER_BOTTOM:
                case CropType.LEFT_BOTTOM:
                case CropType.RIGHT_BOTTOM:
                    return viewHeight - postDrawableHeight;
                case CropType.LEFT_CENTER:
                case CropType.RIGHT_CENTER:
                    // View in the middle of the screen
                    return (viewHeight - postDrawableHeight) / 2f;
            }
        }

        // All other cases we don't need to translate
        return 0f;
    }

    private float getXTranslation(
            @CropType int cropType,
            int viewWidth,
            float postDrawableWidth,
            boolean verticalImageMode
    ) {
        if (!verticalImageMode) {
            switch (cropType) {
                case CropType.RIGHT_TOP:
                case CropType.RIGHT_CENTER:
                case CropType.RIGHT_BOTTOM:
                    return viewWidth - postDrawableWidth;
                case CropType.CENTER_TOP:
                case CropType.CENTER_BOTTOM:
                    // View in the middle of the screen
                    return (viewWidth - postDrawableWidth) / 2f;
            }
        }
        // All other cases we don't need to translate
        return 0f;
    }


    private static final class PreAPI18Matrix extends CompatMatrix {

        @Nullable private Matrix matrix;

        PreAPI18Matrix(Crop.View view) {
            super(view);
        }

        @Override
        Matrix matrix(int cropType) {
            if (cropType != CropType.NONE && matrix == null) {
                matrix = new Matrix();
            }

            return matrix != null ? matrix : view.getImageMatrix();
        }
    }

    private static final class API18Matrix extends CompatMatrix {

        API18Matrix(Crop.View view) {
            super(view);
        }

        @Override
        Matrix matrix(int cropType) {
            return view.getImageMatrix();
        }
    }

    static abstract class CompatMatrix {

        final Crop.View view;

        CompatMatrix(Crop.View view) {
            this.view = view;
        }

        abstract Matrix matrix(@CropType int cropType);
    }
}
