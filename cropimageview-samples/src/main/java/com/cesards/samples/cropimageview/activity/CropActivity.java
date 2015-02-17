package com.cesards.samples.cropimageview.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.cesards.samples.cropimageview.BackgroundToForegroundTransformer;
import com.cesards.samples.cropimageview.R;
import me.relex.circleindicator.CircleIndicator;

/**
 * @author cesards
 */
public abstract class CropActivity extends BaseActivity {

  @InjectView(R.id.crop_pager) ViewPager pagerView;
  @InjectView(R.id.crop_indicator) CircleIndicator indicatorView;
  
  protected abstract int getImagesCount();
  protected abstract ImageView instantiatePagerItem(int position);

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_crop);
    ButterKnife.inject(this);

    this.initData();
  }

  private void initData() {
    this.pagerView.setAdapter(new CropImageAdapter());
    this.pagerView.setPageTransformer(true, new BackgroundToForegroundTransformer());

    this.indicatorView.setViewPager(this.pagerView);
    this.indicatorView.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override public void onPageScrolled(int i, float v, int i2) { }

      @Override public void onPageSelected(int i) { }

      @Override public void onPageScrollStateChanged(int i) { }
    });
  }

  private class CropImageAdapter extends PagerAdapter {

    @Override public Object instantiateItem(ViewGroup container, int position) {
      final ImageView imageView = instantiatePagerItem(position);

      final int matchParent = ViewGroup.LayoutParams.MATCH_PARENT;
      FrameLayout.LayoutParams imageParams = new FrameLayout.LayoutParams(matchParent, matchParent);
      container.addView(imageView, imageParams);
      return imageView;
    }

    @Override public void destroyItem(ViewGroup container, int position, Object object) {
      container.removeView((ImageView) object);
    }

    @Override public int getCount() {
      return getImagesCount();
    }

    @Override public boolean isViewFromObject(View view, Object object) {
      return view == object;
    }
  }
}
