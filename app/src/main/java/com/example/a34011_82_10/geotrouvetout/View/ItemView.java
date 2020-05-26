/*
package com.example.a34011_82_10.geotrouvetout.View;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a34011_82_10.geotrouvetout.Controller.RestConnexion;
import com.example.a34011_82_10.geotrouvetout.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

*/
/**
 * This class will display the whole item
 *//*


public class ItemView extends Activity {

    private String TAG = MainView.class.getSimpleName();

    String Jsontitle;
    String Jsondescription;
    String id;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemview);


        // Receive the ID from previous activty
        //This will be used in the server url
        id = getIntent().getExtras().getString("id");


        //******* GET THE DATAS FROM ONE SPECIFIC ITEM ******* //

        AsyncTask loginReturn = new GetContacts().execute();


        //******* USE THIS DATA TO FILL MY VIEW ****** //

        Object resultTask = null;
        JSONObject task = null;

        try {
            resultTask = loginReturn.get();
            task = new JSONObject(resultTask.toString());

            Jsontitle = task.getString("title");
            Jsondescription = task.getString("description");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException je) {
            je.printStackTrace();
        }


        // Use the datas from JSON TO FILL MY VIEW
        TextView title = (TextView) findViewById(R.id.itemtitle);
        title.setText(Jsontitle);

        TextView description = (TextView) findViewById(R.id.itemdescription);
        description.setText(Jsondescription);
    }
    // END OF ON CREATE INSTRUCTIONS


    //************************************************************** //
    //***************** THE ASYNCTASK CLASS BELOW ***************** //
    //************************************************************ //

    protected class GetContacts extends AsyncTask<Void, Void, JSONObject> {
        JSONObject c;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected JSONObject doInBackground(Void... arg0) {

            RestConnexion sh = new RestConnexion();

            // Making a request to url and getting response
            String url = "http://rest.bzzcycle.com/items/" + id;

            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONArray jsonObj = new JSONArray(jsonStr);
                    // Getting JSON Array node

                    // looping through All Contacts
                    for (int i = 0; i < jsonObj.length(); i++) {
                        c = jsonObj.getJSONObject(i);
                        String id = c.getString("id");
                        String titre = c.getString("title");
                        String description = c.getString("description");
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return c;
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            super.onPostExecute(result);
        }
    }
}
*/
