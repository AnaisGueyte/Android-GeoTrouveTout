package com.example.a34011_82_10.geotrouvetout.Controller;

import com.example.a34011_82_10.geotrouvetout.Model.NewObject;
import com.example.a34011_82_10.geotrouvetout.Model.NewUser;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class aims to gather methods to parse data into Json or Json into data (String or NewObject
 * object, or NewUser object)
 */

public class JsonParsing {


    JSONObject json;
    NewObject newObject;
    NewUser newuser;
    String string;


    //***************** CONSTRUCTOR ***************** //

    //TODO: Do I need a constructor ? Is this an interface / Abstract class ?


    //***************** METHODS ***************** //

    // Parse a (new) user into a Json

    public JSONObject userIntoJson(NewUser newuser) {

        String login = newuser.getPseudo();
        String password = newuser.getPassword();

        String strJson = "[{ \"pseudo\": \" + login + \", \"password\": \" + password + \"}]";

        try {
            JSONObject json = new JSONObject(strJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json;
    }


    //Parse a Json into a list of NewObject

    public NewObject jsonIntoObject(JSONObject json) {

        try {
            String title = json.getString("title");
           String description = json.getString("description");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return newObject;
    }


    // Parse a NewObject into Json

    public JSONObject objectIntoJson(NewObject newObject) {

        String title = newObject.getTitle();
        String description = newObject.getDescription();
        String location = newObject.getLocation();

        String strJson = "[{ \"title\": \" + myTitle + \", \"description\": \" + myDescription + \",  \"location\": \" + myLocation + \"}]";

        try {
            JSONObject json = new JSONObject(strJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json;
    }


    //***************** GETTERS & SETTERS ***************** //

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public NewUser getNewuser() {
        return newuser;
    }

    public void setNewuser(NewUser newuser) {
        this.newuser = newuser;
    }

    public NewObject getNewObject() {
        return newObject;
    }

    public void setNewObject(NewObject newObject) {
        this.newObject = newObject;
    }

    public JSONObject getJson() {
        return json;
    }

    public void setJson(JSONObject json) {
        this.json = json;
    }


}
