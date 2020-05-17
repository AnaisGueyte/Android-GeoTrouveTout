package com.example.a34011_82_10.geotrouvetout.View;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.content.Intent;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.net.Uri;

// Import of packages
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.a34011_82_10.geotrouvetout.R;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.Instant;


public class LocationView extends AppCompatActivity {

    ImageButton bCheckMap;
    Intent intent;
    ImageView car_picture;
    double longitude;
    double latitude;

    //@Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.locationview);

        //***************** FIRST PART: SHOW STEPS OPTIONS ***************** //

        //*** DEFINE LOCATION VIEW **** //

        //  Action on location button

        bCheckMap = (ImageButton) findViewById(R.id.checkmap);
        //bCheckMap.setImageResource(R.drawable.location);

        TextView addLocationTxt = (TextView) findViewById(R.id.confirmLocation);
        String defineLocation = "DEFINE LOCATION";

        addLocationTxt.setText(defineLocation);

        /* Getting ImageURI from Gallery from Main Activity */

        car_picture = findViewById(R.id.car_picture);

        /* Getting ImageURI from Gallery */
        String selectedImgUri3 = getIntent().getStringExtra("imagePath");
        Log.i("info", "what is selectedImgUri3 " + selectedImgUri3);

        if (selectedImgUri3 != null) {
            Log.i("info", "Gallery ImageURI3" + selectedImgUri3);
            String[] selectedImgPath = { MediaStore.Images.Media.DATA };
            Log.i("info", "what is selectedImgPath3 " + selectedImgPath);

            Uri imgPath = Uri.parse(Uri.decode(selectedImgUri3));
            Log.i("info", "what is imgPath3 " + imgPath);

            //Glide.with(this).load(imgPath).into(car_picture);
            Bitmap bitmap2 = null;
            try {
                bitmap2 = BitmapFactory.decodeStream(getContentResolver().openInputStream(Uri.parse(selectedImgUri3)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            // car_picture.setImageBitmap(BitmapFactory.decodeFile(String.valueOf(imgPath)));
            car_picture.setImageBitmap(bitmap2);
        }


        /* Getting ImageBitmap from Camera from Main Activity */
        Intent intent_camera = getIntent();
        Log.i("info", "what is intent_camera " + intent_camera);
        Bitmap camera_img_bitmap = (Bitmap) intent_camera.getParcelableExtra("BitmapImage");
        if (camera_img_bitmap != null) {
            Log.i("info", "what is camera_img_bitmap 2 " + camera_img_bitmap);
            car_picture.setImageBitmap(camera_img_bitmap);
        }


        //*** ADD LOCATION **** //

        // TODO: Might have to do something about the GPS.


    }    // END OF ON CREATE INSTRUCTIONS

} // END OF ON CLASS







