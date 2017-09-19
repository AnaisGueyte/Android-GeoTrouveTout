package com.example.a34011_82_10.geotrouvetout.Model;

import java.sql.Blob;


public class NewObject {


    long id;
    String title;
    String description;
    String location;
    Blob picture;


    //***************** CONSTRUCTOR ***************** //


    public NewObject(String t, String d, String l) {
        this.title = t;
        this.description = d;
        this.location = l;
        //this.picture = p;
    }


    //***************** GETTERS & SETTERS ***************** //

    public Blob getPicture() {
        return picture;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public void setPicture(Blob picture) {
        this.picture = picture;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
