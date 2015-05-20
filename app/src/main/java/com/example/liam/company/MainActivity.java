package com.example.liam.company;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*ContentValues values = new ContentValues();

        values.put("name", "Eetzaal");
        values.put("spaces", 35);

        getContentResolver().insert(OfficeProvider.CONTENT_URI, values);
        */

        /*ContentValues values = new ContentValues();

        values.put("name", "Kantoor");

        getContentResolver().update(
                OfficeProvider.CONTENT_URI,
            values,
            "id = ?",
            new String[]{ "1" }
        );*/

        /*getContentResolver().delete(
            OfficeProvider.CONTENT_URI,
            "id = ?",
            new String[]{ "4" }
        );*/


        /*Cursor c = getContentResolver().query(CompanyProvider.CONTENT_URI, null, null, null, null);
        if (c.moveToFirst()) {
            do{
                Log.i("company", c.getString(c.getColumnIndex("name")));
            }
            while(c.moveToNext());
        }*/

        //Cursor d = getContentResolver().query(Uri.parse("content://com.example.liam.dataProvider.office/offices/company/1"), null, null, null, null);
        Cursor d = getContentResolver().query(Uri.parse("content://com.example.liam.providers.dataProvider/offices/2"), null, null, null, null);

        if (d.moveToFirst()) {
            do{
                Log.i("company", d.getString(d.getColumnIndex("name")));
            }
            while(d.moveToNext());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
