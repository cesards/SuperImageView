package com.codeforvictory.superimageview.roundedcorners;

final class RoundedCornerss {

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







//    @Override
//    public Drawable modifiedDrawable(Drawable drawable) {
//        this.drawable = SuperDrawable.fromDrawable(drawable);
//        updateDrawableAttrs();
//        return this.drawable;
//    }

//    @Override
//    public Drawable modifiedDrawable(Bitmap bitmap) {
////        this.drawable = SuperDrawable.fromBitmap(bitmap);
//        updateDrawableAttrs();
//        return this.drawable;
//    }
//
//    @Override
//    public void notifyDrawableStateChanges() {
////        imageView.invalidate();
//    }

//    @Override
//    public void delegateScaleType(ImageView.ScaleType scaleType) {
//        // TODO: 8/28/18 CHECK IF REALLY NEEDED
//        if (imageView.getScaleType() != scaleType) {
//            updateDrawableAttrs();
//            updateBackgroundDrawableAttrs(false);
//        }
//        // parent scaleType invalidates.
//    }

//    private void updateDrawableAttrs() {
//        updateAttrs(drawable);
//    }

//    private void updateAttrs(Drawable drawable) {
//        if (drawable == null) { return; }

        // TODO how this can be possible?
//        if (drawable instanceof SuperDrawable) {
//            ((SuperDrawable) drawable).setCornerRadius(
//                    cornerRadius[Corner.TOP_LEFT],
//                    cornerRadius[Corner.TOP_RIGHT],
//                    cornerRadius[Corner.BOTTOM_LEFT],
//                    cornerRadius[Corner.BOTTOM_RIGHT]
//            );
//        }
//    }



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
