package com.example.ra412063.imagebuttons;

/*
   This program illustrates how ImageButtons can be used
   to accpet inpt by putting a listener object of the imagebutton
   author rajan alex
   version 04/04/18

 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
    }
    public void cupcake(View view){
        Toast.makeText(ImageActivity.this, "You have selected the cupcake",
                Toast.LENGTH_SHORT).show();
    }

    public void donut(View view){
        Toast.makeText(ImageActivity.this, "You have selected the donut",
                Toast.LENGTH_SHORT).show();
    }

    public void eclair(View view){
        Toast.makeText(ImageActivity.this, "You have selected the eclair",
                Toast.LENGTH_SHORT).show();
    }
    public void froyo(View view){
        Toast.makeText(ImageActivity.this, "You have selected the froyo",
                Toast.LENGTH_SHORT).show();
    }
    public void gingerbread(View view){
        Toast.makeText(ImageActivity.this, "You have selected the gingerbread",
                Toast.LENGTH_SHORT).show();
    }
}
