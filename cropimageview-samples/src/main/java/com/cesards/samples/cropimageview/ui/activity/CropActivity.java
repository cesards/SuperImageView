package com.cesards.samples.cropimageview.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import com.cesards.cropimageview.model.CropType;
import com.cesards.samples.cropimageview.R;
import com.cesards.samples.cropimageview.ui.adapter.CropFragmentPagerAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;

public class CropActivity extends BaseActivity {

  private static final String ARG_CROP = "arg_crop";

  @Bind(R.id.crop_tabs_container) TabLayout titleTabs;
  @Bind(R.id.crop_pager_container) ViewPager cropPager;

  public static void show(@NonNull Context context, @CropType int cropType) {
    Intent starter = new Intent(context, CropActivity.class);
    starter.putExtra(ARG_CROP, cropType);
    context.startActivity(starter);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_crop);
    ButterKnife.bind(this);

    cropPager.setAdapter(new CropFragmentPagerAdapter(
        getFragmentManager(),
        getIntent().getExtras().getInt(ARG_CROP)
    ));
    titleTabs.setupWithViewPager(cropPager);
  }
}
