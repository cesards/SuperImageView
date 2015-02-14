package com.cesards.cropimageviewsamples;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.cesards.cropimageview.CropImageView;
import me.relex.circleindicator.CircleIndicator;

public class CropActivity extends Activity {

  @InjectView(R.id.crop_pager) ViewPager pagerView;
  @InjectView(R.id.crop_indicator) CircleIndicator indicatorView;

  private CropImageView.CropType[] imageCrops = {
      CropImageView.CropType.LEFT_CENTER,
      CropImageView.CropType.RIGHT_CENTER,
      CropImageView.CropType.CENTER_TOP,
      CropImageView.CropType.LEFT_TOP,
      CropImageView.CropType.RIGHT_TOP,
      CropImageView.CropType.CENTER_BOTTOM,
      CropImageView.CropType.LEFT_BOTTOM,
      CropImageView.CropType.RIGHT_BOTTOM,
  };

  private int[] images = {
      R.drawable.zombie,
      R.drawable.zombie,
      R.drawable.zombie,
      R.drawable.zombie,
      R.drawable.zombie,
      R.drawable.ball_centered_bottom_ball,
      R.drawable.ball_centered_bottom_ball,
      R.drawable.ball_centered_bottom_ball,
  };

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_crop);
    ButterKnife.inject(this);

    this.initData();
  }

  private void initData() {
    this.pagerView.setAdapter(new CropImageAdapter());

    this.indicatorView.setViewPager(this.pagerView);
    this.indicatorView.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override public void onPageScrolled(int i, float v, int i2) { }

      @Override public void onPageSelected(int i) { }

      @Override public void onPageScrollStateChanged(int i) { }
    });
  }

  private class CropImageAdapter extends PagerAdapter {

    @Override public Object instantiateItem(ViewGroup container, int position) {
      CropImageView cropImageView = new CropImageView(CropActivity.this);
      cropImageView.setImageDrawable(getResources().getDrawable(images[position]));
      final CropImageView.CropType cropType = imageCrops[position];
      cropImageView.setId(cropType.getCrop());
      cropImageView.setCropType(cropType);
      final int matchParent = ViewGroup.LayoutParams.MATCH_PARENT;
      FrameLayout.LayoutParams imageParams = new FrameLayout.LayoutParams(matchParent, matchParent);

      container.addView(cropImageView, imageParams);
      return cropImageView;
    }

    @Override public void destroyItem(ViewGroup container, int position, Object object) {
      container.removeView((CropImageView) object);
    }

    @Override public int getCount() {
      return imageCrops.length;
    }

    @Override public boolean isViewFromObject(View view, Object object) {
      return view == object;
    }
  }
}
