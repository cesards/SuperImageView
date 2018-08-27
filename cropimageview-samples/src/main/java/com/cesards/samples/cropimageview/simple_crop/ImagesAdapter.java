package com.cesards.samples.cropimageview.simple_crop;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.cesards.samples.cropimageview._activity.CommonImagesAdapter;

import androidx.viewpager.widget.PagerAdapter;

final class ImagesAdapter extends PagerAdapter {

    private final CommonImagesAdapter commonImagesAdapter;

    public ImagesAdapter(CommonImagesAdapter commonImagesAdapter) {
        this.commonImagesAdapter = commonImagesAdapter;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final ImageView imageView = commonImagesAdapter.instantiatePagerItem(position);
        final int matchParent = ViewGroup.LayoutParams.MATCH_PARENT;
        FrameLayout.LayoutParams imageParams = new FrameLayout.LayoutParams(matchParent, matchParent);
        container.addView(imageView, imageParams);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView) object);
    }

    @Override
    public int getCount() {
        return commonImagesAdapter.imagesSize();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
