package com.example.liam.company;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;

/**
 * Created by Liam on 19/05/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private String table;
    static final HashMap<String, String> CREATES = new HashMap<>();
    static{
        CREATES.put("company", "CREATE TABLE company (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR NOT NULL, email VARCHAR NOT NULL)");
        CREATES.put("office", "CREATE TABLE office (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR NOT NULL, spaces INT NOT NULL, company_id INT NOT NULL)");
    }

    DatabaseHelper(Context context, Integer version, String table){
        super(context, table, null, version);
        this.table = table;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(this.CREATES.get(this.table));
        db.execSQL("INSERT INTO office (name, spaces, company_id) VALUES ('Kantoor', 5, 1), ('Test', 10, 1), ('Kantoor', 15, 2)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+this.table);
        onCreate(db);
    }
}
