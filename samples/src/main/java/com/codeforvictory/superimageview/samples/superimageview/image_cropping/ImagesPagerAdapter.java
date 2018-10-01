package com.codeforvictory.superimageview.samples.superimageview.image_cropping;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codeforvictory.android.superimageview.SuperImageView;
import com.codeforvictory.android.superimageview.crop.CropType;
import com.codeforvictory.android.superimageview.crop.CroppedImage;
import com.codeforvictory.superimageview.samples.superimageview.R;
import com.codeforvictory.superimageview.samples.superimageview.shared.LocalImage;

import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;

final class ImagesPagerAdapter extends PagerAdapter {

  private final List<LocalImage> localImages;

  ImagesPagerAdapter(List<LocalImage> localImages) {
    this.localImages = localImages;
  }

  @NonNull
  @Override
  public Object instantiateItem(ViewGroup container, int position) {
    View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_local_image, container, false);

    LocalImage localImage = localImages.get(position);

    int cropType = localImage.getCropType();
    if (cropType == CropType.NONE) {
      ((ImageView) view.findViewById(R.id.image)).setScaleType(ImageView.ScaleType.FIT_CENTER);
      ((ImageView) view.findViewById(R.id.image)).setImageDrawable(
          ContextCompat.getDrawable(container.getContext(), localImage.getDrawableResource())
      );
    } else {
      SuperImageView superImageView = view.findViewById(R.id.image);
      Objects.requireNonNull(superImageView.getFeature(CroppedImage.class)).setCropType(cropType);
      superImageView.setImageDrawable(
          ContextCompat.getDrawable(container.getContext(), localImage.getDrawableResource())
      );
    }

    ((TextView) view.findViewById(R.id.crop_type)).setText(localImage.getTitle());

    container.addView(view);
    return view;
  }

  @Override
  public void destroyItem(ViewGroup container, int position, Object object) {
    container.removeView((View) object);
  }

  @Override
  public int getCount() {
    return localImages.size();
  }

  @Override
  public boolean isViewFromObject(View view, Object object) {
    return view == object;
  }
}
