package com.codeforvictory.superimageview.roundedcorners;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

interface RoundedCornerImageViewHook {
    void setup(@NonNull Context context, @Nullable AttributeSet attributeSet);

    // void delegateDispatchDraw(Canvas canvas);







    /**
     * Needed for {@link ImageView#setImageDrawable(Drawable)}
     * We process the drawable that will be set in the ImageView and we use it as a hook for every
     * time {@link ImageView#setImageDrawable(Drawable)} is called
     *
     * This should be passed to the parent by the ImageView:
     *  super.setImageDrawable(roundedImage.modifiedDrawable(drawable));
     *
     *
     *
     *
     */
    Drawable modifiedDrawable(Drawable drawable);

    /**
     * Usage:
     *
     *   @Override
     *   public void setImageBitmap(Bitmap bm) {
     *     super.setImageDrawable(roundedImage.modifiedDrawable(bm));
     *   }
     */
    Drawable modifiedDrawable(Bitmap bitmap);

    /**
     * Needed for {@link ImageView#drawableStateChanged()}
     *
     * Usage:
     *
     *   @Override
     *   protected void drawableStateChanged() {
     *     super.drawableStateChanged();
     *     roundedImage.notifyDrawableStateChanges();
     *   }
     */
    void notifyDrawableStateChanges();
}
