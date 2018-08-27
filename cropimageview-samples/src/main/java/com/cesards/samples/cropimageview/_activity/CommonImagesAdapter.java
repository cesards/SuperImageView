package com.cesards.samples.cropimageview._activity;

import android.widget.ImageView;

public interface CommonImagesAdapter {
    int imagesSize();
    ImageView instantiatePagerItem(int position);
}
