package com.example.a34011_82_10.geotrouvetout.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a34011_82_10.geotrouvetout.R;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

// Import of packages


public class MainView extends AppCompatActivity {

    ImageButton bAddObj;
    ImageButton uploadPhoto;
    ImageButton bCheckMap;
    Intent intent;
    Intent intent_camera;
    ImageView car_picture;

    int REQUEST_CAMERA = 100;
    int REQUEST_GALLERY = 1;


    private String TAG = MainView.class.getSimpleName();
    private ListView lv;

    ArrayList<HashMap<String, String>> contactList;


    //@Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainview);


        //***************** FIRST PART: SHOW STEPS OPTIONS ***************** //

        //*** SET AND DEFINE VIEW **** //

        TextView addPhotoTxt = (TextView) findViewById(R.id.addPhotoText);
        String addPhotoText = "UPLOAD A PICTURE";

        addPhotoTxt.setText(addPhotoText);

        bAddObj = (ImageButton) findViewById(R.id.uploadphoto);
        bAddObj.setImageResource(R.drawable.insertphoto);


        //*** ADD IMAGE **** //

        bAddObj.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
            }
        });


        //*** TAKE PICTURE **** //

        uploadPhoto = (ImageButton) findViewById(R.id.takephoto);
        uploadPhoto.setImageResource(R.drawable.photo_camera);

        uploadPhoto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent_camera = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent_camera, REQUEST_CAMERA);
            }
        });

    }
    // END OF ON CREATE INSTRUCTIONS

    /* Choose Image from Gallery & Camera onActivityResult */

    //@Override
    @SuppressLint("IntentReset")
    protected void onActivityResult(int reqCode, int resCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(reqCode, resCode, data);

        if (resCode == RESULT_OK) {
            if (reqCode == REQUEST_CAMERA) {
                if (data != null) {
                    Bitmap photo = (Bitmap) data.getExtras().get("data");

                    /*if (photo != null) {
                        Log.i("info", "what is camera_img_bitmap 2 " + photo);
                        car_picture.setImageBitmap(photo);
                        car_picture.setVisibility(View.VISIBLE);
                    }*/

                    /* Passing ImageURI to the Second Activity */
                    Intent IntentCamera = new Intent(this, LocationView.class);
                    IntentCamera.putExtra("BitmapImage", photo);
                    startActivity(IntentCamera);

                }
            } else if (reqCode == REQUEST_GALLERY) {
                if (data != null) {
                    Uri selectedImgUri = data.getData();

                    /*if (selectedImgUri != null) {
                        Uri[] selectedImgPath = { MediaStore.Images.Media.EXTERNAL_CONTENT_URI };
                        //intent = new Intent(Intent.ACTION_GET_CONTENT, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        //intent.setType("image/*");
                        Log.i("info", "what is selectedImgPath " + selectedImgPath);


                        Uri imgPath = Uri.parse(Uri.decode(String.valueOf(selectedImgUri)));
                        Log.i("info", "what is imgPath3 " + imgPath);

                        Bitmap bitmap2 = null;
                        try {
                            bitmap2 = BitmapFactory.decodeStream(getContentResolver().openInputStream(imgPath));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                        car_picture.setVisibility(View.VISIBLE);
                        car_picture.setImageBitmap(bitmap2);
                    }*/

                    /* Passing ImageURI to the Second Activity */
                    Intent IntentGallery = new Intent(this, LocationView.class);
                    IntentGallery.setData(selectedImgUri);
                    IntentGallery.putExtra("imagePath", selectedImgUri.toString());
                    Log.i("info", "what is file URI " + selectedImgUri.toString());
                    startActivity(IntentGallery);
                }
            }
        }
    }

}

