package com.codeforvictory.android.superimageview.crop;

import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Build;

import com.codeforvictory.android.superimageview.Crop;
import com.codeforvictory.android.superimageview.crop.error.IllegalTransformationType;

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

            float xTranslation = getXTranslation(
                cropType,
                viewWidth,
                drawableWidth * scale,
                verticalImageMode
            );
            float yTranslation = getYTranslation(
                cropType,
                viewHeight,
                drawableHeight * scale,
                verticalImageMode
            );

            matrix.postTranslate(xTranslation, yTranslation);
            view.setImageMatrix(matrix);
        }
    }

    // FIXME: 9/19/18 fix this case
    private float getYTranslation(
            @CropType int cropType,
            int viewHeight,
            float postDrawableHeight,
            boolean verticalImageMode
    ) {
        if (verticalImageMode) {
            switch (cropType) {
                case CropType.BOTTOM:
                case CropType.BOTTOM_LEFT:
                case CropType.BOTTOM_RIGHT:
                    return viewHeight - postDrawableHeight;
                case CropType.LEFT:
                case CropType.RIGHT:
                    // View in the middle of the screen
                    return (viewHeight - postDrawableHeight) / 2f;
                case CropType.TOP:
                case CropType.TOP_LEFT:
                case CropType.NONE:
                case CropType.TOP_RIGHT:
                  return 0f;
                default:
                    throw new IllegalTransformationType("Transformation not supported. Check if the transformation you want to do should be handled by the method getXTranslation()");

            }
        }

        // All other cases we don't need to translate
        return 0f;
    }

    // FIXME: 9/19/18 fix this case
    private float getXTranslation(
            @CropType int cropType,
            int viewWidth,
            float postDrawableWidth,
            boolean verticalImageMode
    ) {
        if (!verticalImageMode) {
            switch (cropType) {
                case CropType.TOP_RIGHT:
                case CropType.RIGHT:
                case CropType.BOTTOM_RIGHT:
                    return viewWidth - postDrawableWidth;
                case CropType.TOP:
                case CropType.BOTTOM:
                    // View in the middle of the screen
                    return (viewWidth - postDrawableWidth) / 2f;
                case CropType.BOTTOM_LEFT:
                case CropType.LEFT:
                case CropType.TOP_LEFT:
                case CropType.NONE:
                  return 0f;
                default:
                    throw new IllegalTransformationType("Transformation not supported. Check if the transformation you want to do should be handled by the method getYTranslation()");

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
