package com.example.a34011_82_10.geotrouvetout.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * This class aims to save all the methods to interact with database
 *
 */


public class ObjectData {

    // Creating an instance of the dbb to use all the methods on it
    // TODO: "SQLiteDatabase" WHYYYYYY ? WHAT FOR ?!

    private SQLiteDatabase database;
    private Sqlite bddSqlite;
    private String[] columnTitle = { Sqlite.COL_TITLE};


    //***************** CONSTRUCTOR ***************** //

    public ObjectData(Context context) {
        bddSqlite = new Sqlite(context);
    }


    //***************** METHODS ***************** //

    public void open() throws SQLException {
        database = bddSqlite.getWritableDatabase();
    }


    public void close() {
        bddSqlite.close();
    }

    // TODO: Method to create new user
    // Method to create new user



    // TODO: method to check existing user




    // Method to insert a new object into the server REST

    public long insertObjects(NewObject object) {

        ContentValues values = new ContentValues();

        values.put("title", object.getTitle());
        values.put("descripion", object.getDescription());
        values.put("location", object.getLocation());

        //on insère l'objet dans la BDD via le ContentValues
        return database.insert(bddSqlite.TABLE_OBJECT, null, values);
    }
}
