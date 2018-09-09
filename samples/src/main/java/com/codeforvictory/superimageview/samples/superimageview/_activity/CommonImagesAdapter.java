package com.codeforvictory.superimageview.samples.superimageview._activity;

import android.widget.ImageView;

public interface CommonImagesAdapter {
    int imagesSize();
    ImageView instantiatePagerItem(int position);
}
