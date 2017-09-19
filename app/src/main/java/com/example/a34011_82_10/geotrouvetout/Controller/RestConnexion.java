
package com.example.a34011_82_10.geotrouvetout.Controller;

import android.util.Log;

import com.example.a34011_82_10.geotrouvetout.Model.NewObject;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.URL;


/**
 * This class aims to gather connexion methods to the REST server
 */


public class RestConnexion {

    private NewObject newObject;
    private JSONObject json;

    URL object;
    HttpURLConnection con;
    OutputStreamWriter wr;

    StringBuilder sb;


    // To show info into a bubble during the connection
    private static final String TAG = RestConnexion.class.getSimpleName();


    //***************** CONSTRUCTOR ***************** //

    public RestConnexion() {
    }

    //***************** METHODS ***************** //

    /**
     * @return String
     * <p>
     * This methods connect to the server, stream the data and return it into a string.
     * It's a GET connection, it only fetch datas but doest not post, put or delete
     * datas into the server
     * @params URL: link to the server
     */


    // This method is meant to connect to the server for any kind of use
    public String makeServiceCall(String reqUrl) {
        String response = null;
        Log.i("info", reqUrl);

        try {

            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.19.254.1", 8080));

            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection(proxy);
            conn.setRequestMethod("GET");

            // read the response

            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(in);


        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }

        return response;
    }


    /**
     * @return String
     * This methods is called during the connection to the server to read the data
     * and stream the data into string.
     * Therefore, the method return a string which is returned by the first method.
     * @params InputStream
     */

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }

// TODO: More methods to come with the map !

}
