/*
package com.example.a34011_82_10.geotrouvetout.View;

import android.content.Intent;
import android.os.AsyncTask;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.a34011_82_10.geotrouvetout.Controller.RestConnexion;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a34011_82_10.geotrouvetout.R;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {

    EditText mPseudo;
    EditText mPassword;
    String mMyPseudo;
    String mMyPassword;
    Intent intent;


    ArrayList<HashMap<String, String>> contactList;

    // To show info into a bubble during the connection
    //private static final String TAG = RestConnexion.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sign up button instructions

        Button bSignUp = (Button) findViewById(R.id.signup);

        bSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPseudo = (EditText) findViewById(R.id.pseudo);
                mMyPseudo = mPseudo.getText().toString();

                mPassword = (EditText) findViewById(R.id.password);
                mMyPassword = mPassword.getText().toString();

                intent = new Intent(MainActivity.this, SignUP.class);
                intent.putExtra("pseudo", mMyPseudo);
                startActivity(intent);
            }
        });

        // END of sign up button instructions


        // Sign in Button Instructions

        Button bSignIn = (Button) findViewById(R.id.signin);

        bSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //***************** SAVING DATAS ***************** //

                mPseudo = (EditText) findViewById(R.id.pseudo);
                mMyPseudo = mPseudo.getText().toString();


                mPassword = (EditText) findViewById(R.id.password);
                mMyPassword = mPassword.getText().toString();


                //***************** SERVER CONNEXION ***************** //


                // New object type AsyncTask to capture the data form onPostExecute
                AsyncTask loginReturn = new GetContacts().execute();

                // To be sure to retrieve any type of data, new variable type Object
                Object resultTask = null;
                String task = "";

                // I get the data and put it into my Object.
                // Then parse my object into a string.
                try {
                    resultTask = loginReturn.get();
                    task = new String(resultTask.toString());
                } catch (ExecutionException ee) {
                    ee.printStackTrace();
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }

                // String to verify status
                String statutOk = "{\"status\":0";


                //***************** NEXT STEP ***************** //

                // If return data from server matchs with the statusOk then go to the next view
                if (task.startsWith(statutOk) == true) {
                    intent = new Intent(MainActivity.this, MainView.class);
                    intent.putExtra("pseudo", mMyPseudo);
                    startActivity(intent);
                } else {
                    TextView error = (TextView) findViewById(R.id.loginerror);
                    error.setText("Login failed. Please try again or Sign UP");
                }
            }
        });
        // END of sign in button instruction

    }
    // END of onCreate instructions


    //************************************************************** //
    //***************** THE ASYNCTASK CLASS BELOW ***************** //
    //************************************************************ //

    protected class GetContacts extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this, "Please wait while we are checking your login", Toast.LENGTH_LONG).show();
        }


        @Override
        protected String doInBackground(Void... arg0) {
            StringBuilder builder = new StringBuilder();
            String url = "http://rest.bzzcycle.com/users/login";
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.19.254.1", 8080));

            try {
                URL object = new URL(url);

                HttpURLConnection con = (HttpURLConnection) object.openConnection(proxy);
                con.setDoOutput(true);
                con.setDoInput(true);
                con.setRequestProperty("Content-Type", "application/json");
                con.setRequestProperty("Accept", "application/json");
                con.setRequestMethod("POST");

                JSONObject user = new JSONObject(); // cred est un premier json qui sera inclus dans auth

                user.put("email", mMyPseudo);
                user.put("pwd", mMyPassword);

                OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
                wr.write(user.toString());
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


}

*/
