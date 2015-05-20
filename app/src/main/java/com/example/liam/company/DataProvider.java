package com.example.liam.company;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by Liam Hubers on 20-5-2015.
 */
public class DataProvider extends ContentProvider {
    private SQLiteDatabase database;

    private HashMap<String, String> map;

    static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
    static{
        MATCHER.addURI(DBContract.AUTHORITY, DBContract.Company.TABLE, 1);
        MATCHER.addURI(DBContract.AUTHORITY, DBContract.Office.TABLE, 2);
        MATCHER.addURI(DBContract.AUTHORITY, DBContract.Company.TABLE + "/#", 3);
        MATCHER.addURI(DBContract.AUTHORITY, DBContract.Office.TABLE + "/#", 4);
        MATCHER.addURI(DBContract.AUTHORITY, DBContract.Company.TABLE + "/#/" + DBContract.Office.TABLE, 5);
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();

        DatabaseHelper DatabaseHelper = new DatabaseHelper(context);
        database = DatabaseHelper.getWritableDatabase();

        return (database == null)? false:true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        switch (MATCHER.match(uri)) {
            case 1:
                sortOrder = DBContract.Company.SORT_ORDER_DEFAULT;
                qb.setTables(uri.getPathSegments().get(0));
                qb.setProjectionMap(map);
                break;
            case 2:
                sortOrder = DBContract.Office.SORT_ORDER_DEFAULT;
                qb.setTables(uri.getPathSegments().get(0));
                qb.setProjectionMap(map);
                break;
            case 3:
                qb.setTables(uri.getPathSegments().get(0));
                qb.appendWhere(DBContract.Company._ID + " = " + uri.getPathSegments().get(1));
                break;
            case 4:
                qb.setTables(uri.getPathSegments().get(0));
                qb.appendWhere( DBContract.Office._ID + " = " + uri.getPathSegments().get(1));
                break;
            case 5:
                qb.setTables(uri.getPathSegments().get(2));
                Log.i("test", DBContract.Office.COMPANY_ID);
                qb.appendWhere(DBContract.Office.COMPANY_ID +" = " + uri.getPathSegments().get(1));
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri+" "+MATCHER.match(uri));
        }

        Cursor c = qb.query(database, projection, selection, selectionArgs, null, null, sortOrder);
        c.setNotificationUri(getContext().getContentResolver(), uri);

        return c;
    }

    @Override
    public String getType(Uri uri) {
        switch (MATCHER.match(uri)){
            case 1:
            case 2:
                return "vnd.android.cursor.dir/vnd.example."+uri.getPathSegments().get(0);
            case 3:
            case 4:
                return "vnd.android.cursor.item/vnd.example."+uri.getPathSegments().get(0);
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id = database.insert(uri.getPathSegments().get(0), "", values);
        if (id > 0)
        {
            Uri returnUri = ContentUris.withAppendedId(DBContract.CONTENT_URI, id);
            getContext().getContentResolver().notifyChange(returnUri, null);
            return returnUri;
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;

        String id;
        switch (MATCHER.match(uri)){
            case 1:
            case 2:
                count = database.delete(uri.getPathSegments().get(0), selection, selectionArgs);
                break;
            case 3:
                id = uri.getPathSegments().get(1);
                count = database.delete(uri.getPathSegments().get(0), DBContract.Company._ID + " = " + id + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;
            case 4:
                id = uri.getPathSegments().get(1);
                count = database.delete(uri.getPathSegments().get(0), DBContract.Office._ID + " = " + id + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int count = 0;

        switch (MATCHER.match(uri)){
            case 1:
            case 2:
                count = database.update(uri.getPathSegments().get(0), values, selection, selectionArgs);
                break;
            case 3:
                count = database.update(uri.getPathSegments().get(0), values, DBContract.Company._ID + " = " + uri.getPathSegments().get(1) + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;
            case 4:
                count = database.update(uri.getPathSegments().get(0), values, DBContract.Office._ID + " = " + uri.getPathSegments().get(1) + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri );
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
}
