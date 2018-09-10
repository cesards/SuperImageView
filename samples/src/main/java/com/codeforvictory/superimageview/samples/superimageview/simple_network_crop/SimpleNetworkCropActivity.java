package com.codeforvictory.superimageview.samples.superimageview.simple_network_crop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.codeforvictory.android.superimageview.SuperImageView;
import com.codeforvictory.android.superimageview.crop.CropType;
import com.codeforvictory.superimageview.samples.superimageview.R;
import com.codeforvictory.superimageview.samples.superimageview._activity.CommonImagesAdapter;
import com.codeforvictory.superimageview.samples.superimageview._util.SystemUiHelper;

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
        final SuperImageView superImageView = (SuperImageView) LayoutInflater.from(this).inflate(R.layout.view_cropped_image, null);
//        final ImageView cropImageView = new CroppedImage(this);
//        cropImageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ball_horizontal));
        if (position == 0) {

//            cropImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            superImageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ball_horizontal));
        } else {
            superImageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ball_horizontal));
//            cropImageView.croppedImage().withCropType(CropType.LEFT_CENTER);
//            Picasso.get().load(R.drawable.ball_horizontal).into(cropImageView);
//            ((CroppedImage) cropImageView).withCropType(images[position - 1]);
        }
        return superImageView;
    }

    private void switchToLowProfile() {
        new SystemUiHelper(this, SystemUiHelper.LEVEL_LOW_PROFILE, SystemUiHelper.FLAG_IMMERSIVE_STICKY).hide();
    }
}
