package com.example.jh949711.internetapp;

/*
    This app shows how to view a URL in a web browser. To invoke a browser
    create a Uri instance and pass the instance and an Intent.ACTION_VIEW constant
    to an Intent instance.
    Author: James Hund
    Version 05/02/18
 */

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class InternetActivity extends AppCompatActivity {

    private String myURL;
    private Uri webpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet);
    }

    public void google(View view) {
        myURL = "http://www.google.com";
        webpage = Uri.parse(myURL);
        Intent intent = new Intent(Intent.ACTION_VIEW,webpage);
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
    }

    public void wtamu(View view) {
        myURL = "http://www.wtamu.edu";
        webpage = Uri.parse(myURL);
        Intent intent = new Intent(Intent.ACTION_VIEW,webpage);
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
    }

    public void msn(View view) {
        myURL = "http://www.msn.com";
        webpage = Uri.parse(myURL);
        Intent intent = new Intent(Intent.ACTION_VIEW,webpage);
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
    }
}
