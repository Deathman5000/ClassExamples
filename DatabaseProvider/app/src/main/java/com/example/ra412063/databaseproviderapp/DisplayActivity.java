package com.example.ra412063.databaseproviderapp;

import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/*
    This class is used for display of items stored in the database. it
    will create a TableLayout using java code for the display
 */
public class DisplayActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        String [] projection = {MyContentProvider.COLUMN_ID,
                    MyContentProvider.COLUMN_PRODUCT_NAME,
                   MyContentProvider.COLUMN_QUANTITY};
        Cursor cursor = getContentResolver().query(MyContentProvider.CONTENT_URI,
                projection,null,null);
        int count = cursor.getCount();
        cursor.moveToFirst();
        TableLayout tableLayout = new TableLayout(this);
        tableLayout.setVerticalScrollBarEnabled(true);
        TableRow row = new TableRow(this);
        TextView t0,t1,t2;
        t0 = new TextView(this);
        t0.setText("Product ID");
        t0.setTextColor(Color.RED);
        t0.setTypeface(null, Typeface.BOLD);
        t0.setPadding(20,20,20,20);
        row.addView(t0);
        t1 = new TextView(this);
        t1.setText("Product Name");
        t1.setTextColor(Color.RED);
        t1.setTypeface(null, Typeface.BOLD);
        t1.setPadding(20,20,20,20);
        row.addView(t1);
        t2 = new TextView(this);
        t2.setText("Product Quantity");
        t2.setTextColor(Color.RED);
        t2.setTypeface(null, Typeface.BOLD);
        t2.setPadding(20,20,20,20);
        row.addView(t2);
        tableLayout.addView(row);
        for(int i = 0; i < count; i++){
            row = new TableRow(this);
            t0 = new TextView(this);
            t0.setText(""+cursor.getInt(0));
            t0.setTextColor(Color.BLACK);
            t0.setPadding(20,20,20,20);
            row.addView(t0);
            t1 = new TextView(this);
            t1.setText(cursor.getString(1));
            t1.setTextColor(Color.BLACK);
            t1.setPadding(20,20,20,20);
            row.addView(t1);
            t2 = new TextView(this);
            t2.setText(""+cursor.getInt(2));
            t2.setPadding(20,20,20,20);
            row.addView(t2);
            tableLayout.addView(row);
            cursor.moveToNext();
        }
        setContentView(tableLayout);
    }
}
