package com.mobxpert.men30dayfitnesschallenge.activities;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.mobxpert.men30dayfitnesschallenge.R;
import com.mobxpert.men30dayfitnesschallenge.adapter.ImageAdapter;
import com.mobxpert.men30dayfitnesschallenge.fragmenets.SlideshowDialogFragment;
import com.mobxpert.men30dayfitnesschallenge.models.MyImage;
import com.mobxpert.men30dayfitnesschallenge.utils.MyDbPrefrences;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;



public class CameraActivity extends AppCompatActivity {

    String picturePath;
    private List<MyImage> images = new ArrayList<>();
    private ImageAdapter imageAdapter;
    private RecyclerView recyclerView;
    private FloatingActionButton cameraBtn;
    private Uri mCapturedImageURI;
    private static final int RESULT_LOAD_IMAGE = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 2;
    AdView mAdView;
    LinearLayout empty_container;
    String mCaptionString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        cameraBtn = findViewById(R.id.cambtn);
        empty_container = findViewById(R.id.empty_container);
        recyclerView = (RecyclerView) findViewById(R.id.main_list_view);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mAdView = findViewById(R.id.adView);
        String id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        //MobileAds.initialize(this, getString(R.string.bannerappid));
        MobileAds.initialize(this, getString(R.string.bannerappid));
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(id)
                .build();
        adRequest.isTestDevice(this);
        boolean istestdeviice = adRequest.isTestDevice(this);
        mAdView.loadAd(adRequest);
        boolean shown = mAdView.isShown();
       /* recyclerView = (RecyclerView) findViewById(R.id.main_list_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));*/
        if (MyDbPrefrences.getCameraImages(CameraActivity.this) != null) {
            images = MyDbPrefrences.getCameraImages(CameraActivity.this);
        }
        setAdapter();
        /*imageAdapter = new ImageAdapter(CameraActivity.this, images);

        recyclerView.setAdapter(imageAdapter);*/
        updateGuiContainer();


        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeTakePhoto();
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && cameraBtn.getVisibility() == View.VISIBLE) {
                    cameraBtn.hide();
                } else if (dy < 0 && cameraBtn.getVisibility() != View.VISIBLE) {
                    cameraBtn.show();
                }
            }
        });


    }

    private void setAdapter() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        imageAdapter = new ImageAdapter(CameraActivity.this, images);

        recyclerView.setAdapter(imageAdapter);
        recyclerView.addOnItemTouchListener(new ImageAdapter.RecyclerTouchListener(getApplicationContext(), recyclerView, new ImageAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("time", (Serializable) getCurrentDateTime());
                bundle.putSerializable("urlList", (Serializable) images);
                bundle.putInt("position", position);

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                SlideshowDialogFragment newFragment = (SlideshowDialogFragment) SlideshowDialogFragment.newInstance();
                newFragment.setArguments(bundle);
                newFragment.show(CameraActivity.this.getSupportFragmentManager(), "slideshow");
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }


    /**
     * take a photo
     */
    private void activeTakePhoto() {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            String fileName = "Health_Fitness_IMG_" + System.currentTimeMillis();
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, fileName);
            mCapturedImageURI = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mCapturedImageURI);
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onRestart() {
        //updateGuiContainer();
        super.onRestart();
    }

    @Override
    protected void onResume() {
        //  updateGuiContainer();
        updateGuiContainer();

        if (MyDbPrefrences.getCameraImages(CameraActivity.this) != null) {
            images = MyDbPrefrences.getCameraImages(CameraActivity.this);
        }
        setAdapter();

        super.onResume();
    }

    /**
     * to gallery
     */
    private void activeGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, RESULT_LOAD_IMAGE);
    }

    public String getCurrentDateTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("EEE, d MMM yyyy 'at' hh:mm aaa");
        String strDate = mdformat.format(calendar.getTime());

        return strDate;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RESULT_LOAD_IMAGE:
                if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    cursor.close();
                    MyImage image = new MyImage();
                    image.setTitle("Fitness Challenge");
                    image.setPath(picturePath);
                    images.add(image);
                }
            case REQUEST_IMAGE_CAPTURE:
                if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                    String[] projection = {MediaStore.Images.Media.DATA};
                    Cursor cursor = managedQuery(mCapturedImageURI, projection, null, null, null);
                    int column_index_data = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    cursor.moveToFirst();
                    picturePath = cursor.getString(column_index_data);

                   /* MyImage image = new MyImage();

                    image.setTitle("");*/
                    showDialogAndPrefrences(picturePath);
                    //image.setDateTime(getCurrentDateTime());
                    //image.setPath(picturePath);
                    // images.add(image);
                    updateGuiContainer();


                }
        }
    }


    private String showDialogAndPrefrences(final String picturePath) {
        File imgFile = new File(picturePath);
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(CameraActivity.this);
        View mView = layoutInflaterAndroid.inflate(R.layout.image_caption_layout, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(CameraActivity.this);
        alertDialogBuilderUserInput.setView(mView);
        ImageView imageThumb = mView.findViewById(R.id.imageThumb);
        final EditText captionFromEditText = (EditText) mView.findViewById(R.id.dialogEditText);
        if (imgFile.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            imageThumb.setImageBitmap(myBitmap);
        }
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        // ToDo get user input here
                        MyImage image = new MyImage();
                        image.setTitle(captionFromEditText.getText().toString());
                        image.setDateTime(getCurrentDateTime());
                        image.setPath(picturePath);
                        images.add(image);
                        MyDbPrefrences.saveCameraImage(CameraActivity.this, images);
                        setAdapter();
                        updateGuiContainer();
                    }
                })

                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                MyImage image = new MyImage();
                                image.setTitle(getResources().getString(R.string.noCaption));
                                image.setDateTime(getCurrentDateTime());
                                image.setPath(picturePath);
                                images.add(image);
                                MyDbPrefrences.saveCameraImage(CameraActivity.this, images);
                                setAdapter();
                                updateGuiContainer();
                            }
                        });

        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.show();

        return mCaptionString;
    }

    private void updateGuiContainer() {
        if (images.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            empty_container.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            empty_container.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
       // MainActivity.mainscreenloadtime++;
        super.onBackPressed();
    }

}