package com.example.ra412063.tablelayoutdemo;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TableLayoutDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_layout_demo);
        String [] names = {"Rose Tillman", "Dave Dunlap",
         "Sim Travis", "Sherry Zimmerman", "Taylor Miller",
        "Rob Valentine", "Clay Rice", "Kerry Witt",
        "Velma Berry", "Tom Saxton"};
        Log.i("TableLayoutDemoActivity", " inside onCreate() " );
        TableLayout table = findViewById(R.id.TbleLayout);
        for(int i = 0; i < names.length; i++){
            TableRow row = new TableRow(this);
            TextView tv1 = new TextView(this);
            TextView tv2 = new TextView(this);
            tv1.setText(names[i]);
            tv2.setText(String.format("%d", ((int)(Math.random()*100))));
            Log.i("TableLayoutDemoActivity", "for loop math random " +((int)(Math.random()*100)));
            tv1.setPadding(20,0,30,0);
            tv2.setPadding(0, 0,20, 0);
            tv1.setTypeface(Typeface.DEFAULT_BOLD);
            tv2.setTypeface(Typeface.DEFAULT_BOLD);
            row.addView(tv1);
            row.addView(tv2);
            table.addView(row);
        }
    }
}
