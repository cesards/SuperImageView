package com.cesards.samples.cropimageview.rounded_corners;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.cesards.samples.cropimageview.R;
import com.cesards.samples.cropimageview.rounded_corners.widget.VerticalTransparentItemDecorator;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

public class RoundedCornerImagesActivity extends AppCompatActivity {

    private ImagesAdapter imagesAdapter = new ImagesAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rounded_corner_images);
        setupViews(savedInstanceState);
    }

    private void setupViews(Bundle savedInstanceState) {
        setSupportActionBar(findViewById(R.id.title_container));
//        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP);

        RecyclerView images = findViewById(R.id.images);
        images.addItemDecoration(new VerticalTransparentItemDecorator(getResources().getDimensionPixelOffset(R.dimen.activity_horizontal_margin)));
        images.setAdapter(imagesAdapter);

        Spinner navSpinner = findViewById(R.id.image_options);

        navSpinner.setAdapter(
                ArrayAdapter.createFromResource(
                        navSpinner.getContext(),
                        R.array.options,
                        android.R.layout.simple_spinner_dropdown_item
                )
        );

        navSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        imagesAdapter.removeAll();
                        imagesAdapter.add(ImageFactory.imagesWithoutRoundedCorners());
                        break;

                    case 1:
                        imagesAdapter.removeAll();
                        imagesAdapter.add(ImageFactory.imagesWithRoundedCorners());
                        break;

                    default:
                        throw new IllegalStateException("If it exists you can handle it.");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        if (savedInstanceState == null) {
            navSpinner.setSelection(0);
        }
    }

}
