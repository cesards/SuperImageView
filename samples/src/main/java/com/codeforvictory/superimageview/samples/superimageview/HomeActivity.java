package com.codeforvictory.superimageview.samples.superimageview;

import android.content.Intent;
import android.os.Bundle;

import com.codeforvictory.superimageview.samples.superimageview.rounded_corners.RoundedCornerImagesActivity;
import com.codeforvictory.superimageview.samples.superimageview.simple_crop.SimpleCropActivity;
import com.codeforvictory.superimageview.samples.superimageview.simple_network_crop.SimpleNetworkCropActivity;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        findViewById(R.id.simple_crop).setOnClickListener(view ->
                startActivity(new Intent(this, SimpleCropActivity.class))
        );
        findViewById(R.id.simple_network_crop).setOnClickListener(view ->
                startActivity(new Intent(this, SimpleNetworkCropActivity.class))
        );
        findViewById(R.id.rounded_corners).setOnClickListener(view ->
                startActivity(new Intent(this, RoundedCornerImagesActivity.class))
        );
    }
}
