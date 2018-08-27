package com.cesards.samples.cropimageview;

import android.content.Intent;
import android.os.Bundle;

import com.cesards.samples.cropimageview.simple_crop.SimpleCropActivity;
import com.cesards.samples.cropimageview.simple_network_crop.SimpleNetworkCropActivity;
import com.cesards.samples.cropimageview.test.TestActivity;

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
        findViewById(R.id.test).setOnClickListener(view ->
                startActivity(new Intent(this, TestActivity.class))
        );
    }

}
