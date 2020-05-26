package com.example.a34011_82_10.geotrouvetout.View;
import com.example.a34011_82_10.geotrouvetout.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.content.Intent;
import android.provider.MediaStore;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.net.Uri;
import android.widget.Toast;

// Import of packages
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;


import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

public class LocationView extends AppCompatActivity {

    private static final String TAG = null ;
    private static final int AUTOCOMPLETE_REQUEST_CODE = 0;
    ImageButton bCheckMap;
    Intent intent_camera;
    ImageView car_picture;
    double longitude;
    double latitude;
    private static final String LOG_TAG = "PlaceSelectionListener";

    private Fragment autocompleteFragment;
    private TextView attributionsTextView;
    private TextView address1;
    private String the_google_address;

    protected String Address1;
    List<Place.Field> fields;
    private Activity InputFragmentView;


    //@Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.locationview);

        //***************** FIRST PART: SHOW STEPS OPTIONS ***************** //

        //*** DEFINE LOCATION VIEW **** //

        //  Action on location button

        TextView addLocationTxt = (TextView) findViewById(R.id.confirmLocation);
        String defineLocation = "DEFINE LOCATION";

        addLocationTxt.setText(defineLocation);

        /* Getting ImageURI from Gallery from Main Activity */

        car_picture = findViewById(R.id.car_picture);

        /* Getting ImageURI from Gallery */
        String selectedImgUri3 = getIntent().getStringExtra("imagePath");
        Log.i("info", "what is selectedImgUri3 " + selectedImgUri3);

        if (selectedImgUri3 != null) {
            String[] selectedImgPath = { MediaStore.Images.Media.DATA };
            Uri imgPath = Uri.parse(Uri.decode(selectedImgUri3));
            Log.i("info", "what is imgPath3 " + imgPath);

            Bitmap bitmap2 = null;
            try {
                bitmap2 = BitmapFactory.decodeStream(getContentResolver().openInputStream(Uri.parse(selectedImgUri3)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            car_picture.setImageBitmap(bitmap2);
        }

        /* Getting ImageBitmap from Camera from Main Activity */
        intent_camera = getIntent();
        Log.i("info", "what is intent_camera " + intent_camera);
        Bitmap camera_img_bitmap = (Bitmap) intent_camera.getParcelableExtra("BitmapImage");
        if (camera_img_bitmap != null) {
            Log.i("info", "what is camera_img_bitmap 2 " + camera_img_bitmap);
            car_picture.setImageBitmap(camera_img_bitmap);
        }


        //*** ADD LOCATION **** //

        TextView type_address_field = (TextView) findViewById(R.id.labelAddress1);
        String type_address_txt = "ENTER ADDRESS";

        type_address_field.setCompoundDrawablesWithIntrinsicBounds(R.drawable.add_location, 0, 0, 0);
        type_address_field.setGravity(Gravity.CENTER_VERTICAL);
        type_address_field.setText(type_address_txt);

        TextView yourRequest_field = findViewById(R.id.yourRequest);
        String yourRequest_txt = "See your alert below";

        yourRequest_field.setText(yourRequest_txt);


        // Initialize the SDK
        Places.initialize(getApplicationContext(), "");

        // Create a new Places client instance
        PlacesClient placesClient = Places.createClient(this);

        // Set the fields to specify which types of place data to return.
        final List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS);


        final Context mContext = this;

        type_address_field.setOnClickListener( new View.OnClickListener() {
           // @Override
            public void onClick(View v) {
                // Start the autocomplete intent.
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields).build(mContext);
                startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
            }
        });

    }    // END OF ON CREATE INSTRUCTIONS

    //@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Button continueButton = findViewById(R.id.continueButton);
        String continueTxt = "ADD A NOTE";


        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                Log.i(TAG, "Place: " + place);

                Log.i(LOG_TAG, "Place Selected: " + place.getName());
                Log.i(LOG_TAG, "Place: " + place.getName() + ", " + place.getId() + ", " + place.getAddress());

                //attributionsTextView.setText(getString(R.string.place_autocomplete_search_hint, place.getName(), place.getAddress(), place.getPhoneNumber(), place.getWebsiteUri(), place.getRating(), place.getId()));

                Log.i("info", "apres le attribution textvie de addr1");

                the_google_address = place.getAddress();
                Log.i("info", "addr1: " + the_google_address);

                TextView Address1 = (TextView) findViewById(R.id.Address1);
                //String the_address = the_googele_address;

                Address1.setText(the_google_address);

                continueButton.setVisibility(View.VISIBLE);
                continueButton.setText(continueTxt);


            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.i(TAG, status.getStatusMessage());
            }  // The user canceled the operation.

        }
    }




} // END OF ON CLASS







