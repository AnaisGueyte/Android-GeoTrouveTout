package com.example.a34011_82_10.geotrouvetout.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Blob;


public class Sqlite extends SQLiteOpenHelper
{
    public static final String NOM_BDD = "geotrouvetout.db";
    public static final int VERSION = 1;

    public static final String TABLE_OBJECT = "table_objects";
    public static final String COL_ID = "ID";
    public static final String COL_TITLE = "title";
    private static final String COL_DESCRIPTION = "description";
    private static final String COL_LOCATION = "location";
   // private static final Blob COL_PICTURE = "picture";

    public static final String TABLE_USERS = "table_users";
    public static final String COL_USERSID = "ID";
    public static final String COL_PSEUDO = "pseudo";
    private static final String COL_PASSWORD = "password";

    private static final String CREATE_BDD =
            "CREATE TABLE " + TABLE_OBJECT + " ("
                    + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COL_TITLE + " TEXT NOT NULL, "
                    + COL_DESCRIPTION + " TEXT NOT NULL,"
                    + COL_LOCATION + " TEXT NOT NULL);"
                    /* + COL_PICTURE + "NOT NULL);";*/+
                    "CREATE TABLE" + TABLE_USERS + " ( "
                    + COL_USERSID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COL_PSEUDO + " TEXT NOT NULL,"
                    + COL_PASSWORD + " TEXT NOT NULL);" ;




    //***************** CONSTRUCTOR ***************** //


    // TODO: GET ANSWERS TO KNOW WHY THE CONSTRUCTOR IS THIS WAY AND WHY IT DOES NOT TAKE THE CREATE_BDD variable.

    public Sqlite(Context context) {
        super(context, NOM_BDD, null, VERSION);

    }


    //***************** GETTERS ***************** //


    public String getBddTitre()
    {
        return TABLE_OBJECT;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BDD);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_OBJECT + ";");
        onCreate(db);
    }

}