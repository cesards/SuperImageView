package com.codeforvictory.superimageview.samples.superimageview.shared;

import com.codeforvictory.android.superimageview.crop.CropType;
import com.codeforvictory.superimageview.samples.superimageview.R;

import java.util.Arrays;
import java.util.List;

public final class ImageLocalDataSource {

    private ImageLocalDataSource() {
        throw new AssertionError("This shouldn't be initialized!");
    }

    private static final LocalImage[] localImages = {
      new LocalImage(CropType.NONE, "Original horizontal image", R.drawable.ball_horizontal),
      new LocalImage(CropType.NONE, "Original vertical image", R.drawable.ball_vertical),
      new LocalImage(CropType.TOP_LEFT, "Left Top crop of the horizontal image", R.drawable.ball_horizontal),
      new LocalImage(CropType.TOP, "Center Top crop of the vertical image", R.drawable.ball_vertical),
      new LocalImage(CropType.TOP_RIGHT, "Right Top crop of the horizontal image", R.drawable.ball_horizontal),
      new LocalImage(CropType.LEFT, "Left Center crop of the horizontal image", R.drawable.ball_horizontal),
      new LocalImage(CropType.RIGHT, "Right Center crop of the horizontal image", R.drawable.ball_horizontal),
      new LocalImage(CropType.BOTTOM_LEFT, "Left Bottom crop of the horizontal image", R.drawable.ball_horizontal),
      new LocalImage(CropType.BOTTOM, "Center Bottom crop of the vertical image", R.drawable.ball_vertical),
      new LocalImage(CropType.BOTTOM_RIGHT, "Right Bottom crop of the horizontal image", R.drawable.ball_horizontal),
  };

  private static final NetworkImage[] networkImages = {
      // Provided by https://unsplash.com
      new NetworkImage(
          CropType.NONE,
          "Original image (vertical)",
          "https://images.unsplash.com/photo-1512061203001-0c4d837c6882?w=600"
      ),
      new NetworkImage(
          CropType.TOP,
          "Top crop of the original image",
          "https://images.unsplash.com/photo-1512061203001-0c4d837c6882?w=600"
      ),
      new NetworkImage(
          CropType.RIGHT,
          "Right crop of the original image",
          "https://images.unsplash.com/photo-1512061203001-0c4d837c6882?w=600"
      ),
      new NetworkImage(
          CropType.BOTTOM,
          "Bottom crop of the original image",
          "https://images.unsplash.com/photo-1512061203001-0c4d837c6882?w=600"
      ),
      new NetworkImage(
          CropType.LEFT,
          "Left crop of the original image",
          "https://images.unsplash.com/photo-1512061203001-0c4d837c6882?w=600"
      ),
      new NetworkImage(
          CropType.TOP_LEFT,
          "Top left crop of the original image",
          "https://images.unsplash.com/photo-1512061203001-0c4d837c6882?w=600"
      ),
      new NetworkImage(
          CropType.TOP_RIGHT,
          "Top right crop of the original image",
          "https://images.unsplash.com/photo-1512061203001-0c4d837c6882?w=600"
      ),
      new NetworkImage(
          CropType.BOTTOM_RIGHT,
          "Bottom right crop of the original image",
          "https://images.unsplash.com/photo-1512061203001-0c4d837c6882?w=600"
      ),
      new NetworkImage(
          CropType.BOTTOM_LEFT,
          "Bottom left crop of the original image",
          "https://images.unsplash.com/photo-1512061203001-0c4d837c6882?w=600"
      ),
      new NetworkImage(
          CropType.NONE,
          "Original image (horizontal)",
          "https://images.pexels.com/photos/161798/stonehenge-architecture-history-monolith-161798.jpeg?auto=compress&h=400"
      ),
      new NetworkImage(
          CropType.TOP,
          "Top crop of the original image",
          "https://images.pexels.com/photos/161798/stonehenge-architecture-history-monolith-161798.jpeg?auto=compress&h=400"
      ),
      new NetworkImage(
          CropType.RIGHT,
          "Right crop of the original image",
          "https://images.pexels.com/photos/161798/stonehenge-architecture-history-monolith-161798.jpeg?auto=compress&h=400"
      ),
      new NetworkImage(
          CropType.BOTTOM,
          "Bottom crop of the original image",
          "https://images.pexels.com/photos/161798/stonehenge-architecture-history-monolith-161798.jpeg?auto=compress&h=400"
      ),
      new NetworkImage(
          CropType.LEFT,
          "Left crop of the original image",
          "https://images.pexels.com/photos/161798/stonehenge-architecture-history-monolith-161798.jpeg?auto=compress&h=400"
      ),
      new NetworkImage(
          CropType.TOP_LEFT,
          "Top left crop of the original image",
          "https://images.pexels.com/photos/161798/stonehenge-architecture-history-monolith-161798.jpeg?auto=compress&h=400"
      ),
      new NetworkImage(
          CropType.TOP_RIGHT,
          "Top right crop of the original image",
          "https://images.pexels.com/photos/161798/stonehenge-architecture-history-monolith-161798.jpeg?auto=compress&h=400"
      ),
      new NetworkImage(
          CropType.BOTTOM_RIGHT,
          "Bottom right crop of the original image",
          "https://images.pexels.com/photos/161798/stonehenge-architecture-history-monolith-161798.jpeg?auto=compress&h=400"
      ),
      new NetworkImage(
          CropType.BOTTOM_LEFT,
          "Bottom left crop of the original image",
          "https://images.pexels.com/photos/161798/stonehenge-architecture-history-monolith-161798.jpeg?auto=compress&h=400"
      ),
  };

  public static List<LocalImage> localImages() {
    return Arrays.asList(localImages);
  }

  public static List<NetworkImage> networkImages() {
    return Arrays.asList(networkImages);
  }

}
