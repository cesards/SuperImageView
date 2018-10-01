package com.codeforvictory.superimageview.samples.superimageview.rounded_corners;

import android.os.Bundle;

import com.codeforvictory.superimageview.samples.superimageview.R;
import com.codeforvictory.superimageview.samples.superimageview.shared.ImageLocalDataSource;
import com.codeforvictory.superimageview.samples.superimageview.shared.widget.VerticalTransparentItemDecorator;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public final class RoundedCornerImagesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        setupViews();
    }

    private void setupViews() {
        RecyclerView images = findViewById(R.id.images);
        images.addItemDecoration(new VerticalTransparentItemDecorator(getResources().getDimensionPixelOffset(R.dimen.activity_horizontal_margin)));
        images.setAdapter(new ImagesRecyclerAdapter(ImageLocalDataSource.networkImages()));
    }

}
