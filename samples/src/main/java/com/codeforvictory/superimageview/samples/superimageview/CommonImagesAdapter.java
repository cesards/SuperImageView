package com.codeforvictory.superimageview.samples.superimageview;

import android.widget.ImageView;

public interface CommonImagesAdapter {
    int imagesSize();
    ImageView instantiatePagerItem(int position);
}
