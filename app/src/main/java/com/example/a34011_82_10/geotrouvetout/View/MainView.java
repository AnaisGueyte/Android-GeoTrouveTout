package com.example.a34011_82_10.geotrouvetout.View;

import android.os.AsyncTask;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

// Import of packages

import com.example.a34011_82_10.geotrouvetout.Controller.RestConnexion;
import com.example.a34011_82_10.geotrouvetout.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class MainView extends AppCompatActivity {

    ImageButton bAddObj;
    ImageButton bCheckMap;
    Intent intent;

    private String TAG = MainView.class.getSimpleName();
    private ListView lv;
    ArrayList<HashMap<String, String>> contactList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainview);


        //***************** FIRST PART WITH CONTENT ***************** //

        // TODO FIRST: The text will later on display the number of objects found and number of object yet to be found.

        //String monText = getIntent().getExtras().getString("pseudo");

        TextView mTextView = (TextView) findViewById(R.id.textView);
        String mString = "Start playing ! \n" +
                " \n You have 0 item unfound." +
                "\n" +
                " \n You have 0 item found.";

        mTextView.setText(mString);


        //***************** SECOND PART WITH LIST ***************** //

        // This will inflate the listview and get the datas from REST server

        contactList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.List);

        new GetContacts().execute();

        //***************** ONCLICK ITEM INSTRUCTION ***************** //

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // Get the id from the item, pass it to the next wiew to the GET request will request the proprer item
                String selected = ((TextView) view.findViewById(R.id.id)).getText().toString();

                intent = new Intent(MainView.this, ItemView.class);
                intent.putExtra("id", selected);
                startActivity(intent);
            }
        });


        //***************** THIRD PART ON THE BOTTOM MENU ***************** //

        // Action on adding a new object

        bAddObj = (ImageButton) findViewById(R.id.addobject);
        bAddObj.setImageResource(R.drawable.fab);

        bAddObj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainView.this, AddNewObject.class);
                startActivity(intent);
            }
        });

        //  Action on location button

        bCheckMap = (ImageButton) findViewById(R.id.checkmap);
        bCheckMap.setImageResource(R.drawable.location);

        bCheckMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainView.this, AddNewObject.class);
                startActivity(intent);

                // TODO: Send the user on a MAP.
                //TODO: Create a map
            }
        });
    }
    // END OF ON CREATE INSTRUCTIONS


    //************************************************************** //
    //***************** THE ASYNCTASK CLASS BELOW ***************** //
    //************************************************************ //

    protected class GetContacts extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected Void doInBackground(Void... arg0) {

            RestConnexion sh = new RestConnexion();

            // Making a request to url and getting response
            String url = "http://rest.bzzcycle.com/items";

            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONArray jsonObj = new JSONArray(jsonStr);

                    // Getting JSON Array node


                    // looping through All Contacts
                    for (int i = 0; i < jsonObj.length(); i++) {
                        JSONObject c = jsonObj.getJSONObject(i);
                        String id = c.getString("id");
                        String titre = c.getString("title");
                        String description = c.getString("description");


                        // tmp hash map for single contact
                        HashMap<String, String> contact = new HashMap<>();

                        // adding each child node to HashMap key => value
                        contact.put("id", id);
                        contact.put("titre", titre);
                        contact.put("description", description);


                        // adding contact to contact list
                        contactList.add(contact);
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

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            ListAdapter adapter = new SimpleAdapter(MainView.this, contactList,
                    R.layout.list_item, new String[]{"id", "titre", "description"},
                    new int[]{R.id.id, R.id.titre, R.id.description});
            lv.setAdapter(adapter);
        }
    }


}

