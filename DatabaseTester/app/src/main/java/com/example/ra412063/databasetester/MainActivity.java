package com.example.ra412063.databasetester;
/*
    This app can retrieve all the items stored in the database of the
    DatabaseProvider app.
 */
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_PRODUCT_NAME = "productName";
    private static final String COLUMN_QUANTITY = "quantity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String AUTHORITY = "com.example.ra412063.databaseproviderapp.MyContentProvider";
        String TABLE_PRODUCT = "product";
        Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" +TABLE_PRODUCT);
        ContentResolver contentResolver = getContentResolver();
        String [] projection = {COLUMN_ID, COLUMN_PRODUCT_NAME, COLUMN_QUANTITY};
        Cursor cursor = contentResolver.query(CONTENT_URI,projection,null,null,null);
        String s = "";
        TextView tv = findViewById(R.id.text_view_result);
        if (!cursor.moveToFirst()) s = "no result to display";
        else{
            do {
                s += String.format("%-10s\t%-10s\t%-10s\n",cursor.getString(0),
                        cursor.getString(1), cursor.getString(2));
            } while (cursor.moveToNext());
        }
        tv.setText(s);
    }
}
