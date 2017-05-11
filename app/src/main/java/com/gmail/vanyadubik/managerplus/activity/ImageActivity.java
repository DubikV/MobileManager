package com.gmail.vanyadubik.managerplus.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.gmail.vanyadubik.managerplus.R;
import com.gmail.vanyadubik.managerplus.ui.TouchImageView;
import com.squareup.picasso.Picasso;

import java.io.File;

public class ImageActivity extends AppCompatActivity {

    public static final String IMAGE_FULL_NAME = "image_full_name";
    public static final String IMAGE_NAME = "image_name";

    private TouchImageView imgDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        imgDisplay = (TouchImageView) findViewById(R.id.image);
        imgDisplay.setMaxZoom(5f);

        FloatingActionButton closeView = (FloatingActionButton) findViewById(R.id.return_image);
        closeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        Bundle extras = getIntent().getExtras();

        String imageFullName = null;
        String imageName = null;
        if (extras != null) {
            imageFullName = extras.getString(IMAGE_FULL_NAME);
            imageName = extras.getString(IMAGE_NAME);
        }else{
            Toast.makeText(getApplicationContext(),
                    getResources().getString(R.string.photo_not_found), Toast.LENGTH_SHORT)
                    .show();
            finish();
            return;
        }
        if(imageFullName == null || imageFullName.isEmpty()){
            Toast.makeText(getApplicationContext(),
                    getResources().getString(R.string.photo_not_found), Toast.LENGTH_SHORT)
                    .show();
            finish();
            return;
        }

        getSupportActionBar().setTitle(imageName);

        Picasso.with(this).load(new File(imageFullName))
                .placeholder(getResources().getDrawable(R.drawable.ic_image))
                .into(imgDisplay);

        //imgDisplay.setImageResource(R.drawable.myImage);
    }

}