package com.example.a34011_82_10.geotrouvetout.View;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a34011_82_10.geotrouvetout.Controller.JsonParsing;
import com.example.a34011_82_10.geotrouvetout.Model.NewObject;
import com.example.a34011_82_10.geotrouvetout.R;
import com.example.a34011_82_10.geotrouvetout.View.MainView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.util.concurrent.ExecutionException;


public class AddLocation extends Activity {

    Button bsave;
    EditText mTitle;
    String myTitle;
    EditText mDescription;
    String myDescription;
    Button mlocation;
    String myLocation;
    ImageButton mpicture;
    Intent intent;

    double longitutde;
    double latitude;
    int userfinder;
    int userposter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addobject);


        // Instructions to get the GPS location
        // Obviously not working now

        mlocation = (Button) findViewById(R.id.blocation);
        mlocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String text = "TEST";
                myLocation = text;
            }
        });


    }
    // END OF ON CREATE INSTRUCTIONS


    //************************************************************** //
    //***************** THE ASYNCTASK CLASS BELOW ***************** //
    //************************************************************ //

    protected class GetContacts extends AsyncTask<Void, Void, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(com.example.a34011_82_10.geotrouvetout.View.AddLocation.this, "Please wait while add your item", Toast.LENGTH_LONG).show();

        }

        @Override
        protected String doInBackground(Void... arg0) {
            StringBuilder builder = new StringBuilder();
            String url = "http://rest.bzzcycle.com/items";
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.19.254.1", 8080));

            try {
                URL object = new URL(url);

                HttpURLConnection con = (HttpURLConnection) object.openConnection(proxy);
                con.setDoOutput(true);
                con.setDoInput(true);
                con.setRequestProperty("Content-Type", "application/json");
                con.setRequestProperty("Accept", "application/json");
                con.setRequestMethod("POST");

                JSONObject item = new JSONObject();

                item.put("title", myTitle);
                item.put("description", myDescription);
                item.put("longitude", longitutde);
                item.put("latitude", latitude);
                // item.put("img",img ); NULL atm because I don't know how to take a picture
                item.put("id_user_finder", userfinder);
                item.put("id_user_poster", userposter);

                OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
                wr.write(item.toString());
                wr.flush();

                //display what returns the POST request
                int HttpResult = con.getResponseCode();

                if (HttpResult == HttpURLConnection.HTTP_OK) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
                    String line = null;

                    while ((line = br.readLine()) != null) {
                        builder.append(line + "\n");
                    }

                    br.close();

                    Log.i("info", "*******************SB STRING*****" + builder.toString());

                } else {
                    Log.i("info", "*************CON RESPONSE***********" + con.getResponseMessage());
                }
            } catch (MalformedURLException mue) {
                mue.printStackTrace();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            } catch (JSONException jse) {
                jse.printStackTrace();
            }


            return builder.toString();
        }


        @Override
        protected void onPostExecute(String result) {

            super.onPostExecute(result);
        }
    }
    // END OF ASYNCTASK
}
