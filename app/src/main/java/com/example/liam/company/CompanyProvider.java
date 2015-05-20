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

import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by Liam on 19/05/2015.
 */
public class CompanyProvider {

    /*private SQLiteDatabase database;
    private String table;
    private String plural;

    static final String PROVIDER_NAME = "com.example.liam.dataProvider.company";
    static final String URL = "content://" + PROVIDER_NAME + "/"+PLURAL;
    static final Uri CONTENT_URI = Uri.parse(URL);
    static final UriMatcher Matcher;
    static{
        Matcher = new UriMatcher(UriMatcher.NO_MATCH);
        Matcher.addURI(PROVIDER_NAME, PLURAL, 1);
        Matcher.addURI(PROVIDER_NAME, PLURAL+"/#", 2);
    }

    private static HashMap<String, String> COMPANIES_MAP;

    @Override
    public boolean onCreate() {
        Context context = getContext();

        DatabaseHelper DatabaseHelper = new DatabaseHelper(context, 1, this.TABLE);
        database = DatabaseHelper.getWritableDatabase();

        return (database == null)? false:true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(this.TABLE);

        switch (Matcher.match(uri)) {
            case 1:
                qb.setProjectionMap(COMPANIES_MAP);
                break;
            case 2:
                qb.appendWhere( "id =" + uri.getPathSegments().get(1));
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        if(sortOrder == null || sortOrder == ""){
            sortOrder = this.TABLE_ORDER;
        }
        Cursor c = qb.query(database, projection, selection, selectionArgs, null, null, sortOrder);
        c.setNotificationUri(getContext().getContentResolver(), uri);

        return c;
    }

    @Override
    public String getType(Uri uri) {
        switch (Matcher.match(uri)){
            case 1:
                return "vnd.android.cursor.dir/vnd.example."+TABLE;
            case 2:
                return "vnd.android.cursor.item/vnd.example."+PLURAL;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id = database.insert(this.TABLE, "", values);
        if (id > 0)
        {
            Uri returnUri = ContentUris.withAppendedId(CONTENT_URI, id);
            getContext().getContentResolver().notifyChange(returnUri, null);
            return returnUri;
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;

        switch (Matcher.match(uri)){
            case 1:
                count = database.delete(this.TABLE, selection, selectionArgs);
                break;
            case 2:
                String id = uri.getPathSegments().get(1);
                count = database.delete(this.TABLE, "id = " + id + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
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

        switch (Matcher.match(uri)){
            case 1:
                count = database.update(this.TABLE, values,
                        selection, selectionArgs);
                break;
            case 2:
                count = database.update(this.TABLE, values, "id = " + uri.getPathSegments().get(1) + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri );
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }*/
}
