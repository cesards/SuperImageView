/*
 * Copyright 2015 Cesar Diez Sanchez
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

public abstract class CropActivity extends BaseActivity {

  @InjectView(R.id.crop_pager)
  ViewPager pagerView;

  protected abstract int getImagesCount();
  protected abstract ImageView instantiatePagerItem(int position);

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_crop);
    ButterKnife.inject(this);

    this.initData();
  }

  private void initData() {
    this.pagerView.setAdapter(new CropImageAdapter());
    this.pagerView.setPageTransformer(true, new BackgroundToForegroundTransformer());
  }

  private class CropImageAdapter extends PagerAdapter {

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
      final ImageView imageView = instantiatePagerItem(position);

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
      return getImagesCount();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
      return view == object;
    }
  }
}
