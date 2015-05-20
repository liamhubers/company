package com.example.liam.company;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;

/**
 * Created by Liam on 19/05/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "database.db";
    static final Integer VERSION = 4;
    DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ DBContract.Company.TABLE + " ("+
                DBContract.Company._ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                DBContract.Company.NAME +" VARCHAR NOT NULL, "+
                DBContract.Company.EMAIL +" VARCHAR NOT NULL"+
        ")");
        db.execSQL("CREATE TABLE "+ DBContract.Office.TABLE +" ("+
                DBContract.Office._ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                DBContract.Office.NAME +" VARCHAR NOT NULL, "+
                DBContract.Office.SPACES_AMOUNT +" INT NOT NULL, "+
                DBContract.Office.COMPANY_ID +" INT NOT NULL"+
                DBContract.Office.WEBSITE_URL +" VARCHAR NOT NULL"+
                DBContract.Office.LON +" FLOAT(9, 7) NOT NULL"+
                DBContract.Office.LAT +" FLOAT(9, 7) NOT NULL"+
        ")");
        db.execSQL("INSERT INTO "+ DBContract.Company.TABLE +" ("+ DBContract.Company.NAME +", "+ DBContract.Company.EMAIL +") VALUES ('Edit4U', 'info@edit4u.nl'), ('Inverso Media', 'info@inversomedia.nl')");
        db.execSQL("INSERT INTO "+ DBContract.Office.TABLE +" ("+ DBContract.Office.NAME +", "+ DBContract.Office.SPACES_AMOUNT +", "+ DBContract.Office.COMPANY_ID +") VALUES ('Kantoor', 5, 1), ('Test', 10, 1), ('Kantoor', 15, 2)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DBContract.Company.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DBContract.Office.TABLE);
        onCreate(db);
    }
}
