package com.cesards.samples.cropimageview.simple_network_crop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.cesards.cropimageview.CropImageView;
import com.cesards.cropimageview.crop.CropType;
import com.cesards.samples.cropimageview.R;
import com.cesards.samples.cropimageview._activity.CommonImagesAdapter;
import com.cesards.samples.cropimageview._util.SystemUiHelper;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

public class SimpleNetworkCropActivity extends AppCompatActivity implements CommonImagesAdapter {

    private static final int[] images = {
            CropType.LEFT_TOP,
            CropType.CENTER_TOP,
            CropType.RIGHT_TOP,
            CropType.LEFT_CENTER,
            CropType.RIGHT_CENTER,
            CropType.LEFT_BOTTOM,
            CropType.CENTER_BOTTOM,
            CropType.RIGHT_BOTTOM,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_network_crop);
        switchToLowProfile();
        setupViews();
    }

    private void setupViews() {
        final ViewPager pagerView = findViewById(R.id.cropped_images);
        pagerView.setAdapter(new ImagesAdapter(this));
//        pagerView.setPageTransformer(true, new BackgroundToForegroundTransformer());
    }

    @Override
    public int imagesSize() {
        return images.length + 1;
    }

    @Override
    public ImageView instantiatePagerItem(int position) {
        final CropImageView cropImageView = (CropImageView) LayoutInflater.from(this).inflate(R.layout.view_cropped_image, null);
//        final ImageView cropImageView = new CropImageView(this);
//        cropImageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ball_horizontal));
        if (position == 0) {

//            cropImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            cropImageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ball_horizontal));
        } else {
            cropImageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ball_horizontal));
//            cropImageView.croppedImage().withCropType(CropType.LEFT_CENTER);
//            Picasso.get().load(R.drawable.ball_horizontal).into(cropImageView);
//            ((CropImageView) cropImageView).withCropType(images[position - 1]);
        }
        return cropImageView;
    }

    private void switchToLowProfile() {
        new SystemUiHelper(this, SystemUiHelper.LEVEL_LOW_PROFILE, SystemUiHelper.FLAG_IMMERSIVE_STICKY).hide();
    }
}
