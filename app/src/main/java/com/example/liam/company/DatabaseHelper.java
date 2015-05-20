package com.example.liam.company;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "database.db";
    static final Integer VERSION = 4;

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DBContract.Company.TABLE + " (" +
                DBContract.Company._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DBContract.Company.NAME + " VARCHAR NOT NULL, " +
                DBContract.Company.DESCRIPTION + " TEXT NOT NULL" +
                DBContract.Company.WEBSITE_URL + "VARCHAR NOT NULL" +
                ")");

        db.execSQL("CREATE TABLE " + DBContract.Office.TABLE + " (" +
                DBContract.Office._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DBContract.Office.COMPANY_ID + " INT NOT NULL" +
                DBContract.Office.NAME + " VARCHAR NOT NULL, " +
                DBContract.Office.SPACE_AMOUNT + " INT NOT NULL, " +
                DBContract.Office.PHONE_NUM + " INT NOT NULL, " +
                DBContract.Office.ADDRESS + " VARCHAR NOT NULL, " +
                DBContract.Office.LON + " FLOAT(9, 7) NOT NULL" +
                DBContract.Office.LAT + " FLOAT(9, 7) NOT NULL" +
                ")");

        db.execSQL("INSERT INTO " + DBContract.Company.TABLE + " (" + DBContract.Company.NAME + ", " + DBContract.Company.DESCRIPTION + ", " + DBContract.Company.WEBSITE_URL + ") VALUES " +
                "('Edit4U', 'Stom bedrijf vol hodors', 'http://www.edit4u.nl'), ('Inverso Media', 'Failliet ftw', 'www.hodor.nl')");

        db.execSQL("INSERT INTO " + DBContract.Office.TABLE + " (" + DBContract.Office.NAME + ", " + DBContract.Office.SPACE_AMOUNT + ", " + DBContract.Office.COMPANY_ID + ", " + DBContract.Office.PHONE_NUM + ", " + DBContract.Office.ADDRESS + ") VALUES " +
                "('Kantoor', 5, 1, 0202398491, Coolestraat 10), ('Test', 10, 1, 0703939403, Hodorlaan 23), ('Kantoor Hodor', 15, 2, 0153101239, Zegge 203)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DBContract.Company.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DBContract.Office.TABLE);
        onCreate(db);
    }
}
