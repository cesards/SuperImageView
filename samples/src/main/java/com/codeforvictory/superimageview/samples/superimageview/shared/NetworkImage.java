package com.codeforvictory.superimageview.samples.superimageview.shared;

import com.codeforvictory.android.superimageview.crop.CropType;

public final class NetworkImage {

  @CropType private final int cropType;
  private final String title;
  private final String imageUrl;

  NetworkImage(@CropType int cropType, String title, String imageUrl) {
    this.cropType = cropType;
    this.title = title;
    this.imageUrl = imageUrl;
  }

  @CropType
  public int cropType() {
    return cropType;
  }

  public String title() {
    return title;
  }

  public String imageUrl() {
    return imageUrl;
  }
}

