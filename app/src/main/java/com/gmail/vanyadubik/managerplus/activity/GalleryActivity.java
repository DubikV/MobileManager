package com.gmail.vanyadubik.managerplus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextSwitcher;

import com.gmail.vanyadubik.managerplus.R;
import com.gmail.vanyadubik.managerplus.adapter.GalleryAdapter;
import com.gmail.vanyadubik.managerplus.app.ManagerPlusAplication;
import com.gmail.vanyadubik.managerplus.model.PhotoItem;
import com.gmail.vanyadubik.managerplus.repository.DataRepository;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import static android.R.attr.path;

public class GalleryActivity extends AppCompatActivity {

    private static final int CAPTURE_IMAGE_ACTIVITY_REQ = 999;

    private static final String GALLERY_NAME_OBJECT = "gallery_name_object";
    private static final String GALLERY_EXTERNALID_OBJECT = "gallery_externatlid_object";

    @Inject
    DataRepository dataRepository;

    private ImageView selectedImage;
    private Gallery gallery;
    private GalleryAdapter mAdapter;
    private TextSwitcher mTitle;
    private List<PhotoItem> mData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        getSupportActionBar().setTitle(getResources().getString(R.string.added_foto));

        ((ManagerPlusAplication) getApplication()).getComponent().inject(this);

        gallery = (Gallery) findViewById(R.id.gallery);
        selectedImage = (ImageView)findViewById(R.id.imageView);
        gallery.setSpacing(1);

        mTitle = (TextSwitcher) findViewById(R.id.title);

        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                PhotoItem photoItem = mData.get(position);

                Picasso.with(GalleryActivity.this)
                        .load(photoItem.getFile())
                        //.placeholder(R.drawable.shape_camera)
                        .into(selectedImage);
                mTitle.setText(photoItem.getTitle());
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.gallery_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_foto) {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, path);
            startActivityForResult(cameraIntent, CAPTURE_IMAGE_ACTIVITY_REQ);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

//        mAdapter = new GalleryAdapter(GalleryActivity.this, mData);
//        gallery.setAdapter(mAdapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}