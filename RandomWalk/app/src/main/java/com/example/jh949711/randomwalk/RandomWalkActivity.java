package com.example.jh949711.randomwalk;
/*
    This app will draw a solid circle and randomly change its location
    after resting for a small unit of tiem to give teh effect of animation

 */

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class RandomWalkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_walk);
        //to find the width and height of the ui screen
        //DisplayMatrix instance is used to get general information
        //about screen size and so on.
        DisplayMetrics displayMetrics = new DisplayMetrics();
        //next create an instance of window manager to communicate with it
        WindowManager windowManager =
                (WindowManager)getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        // tell this window manager to create a new window for drawing or objects
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        float width = displayMetrics.widthPixels;
        float height = displayMetrics.heightPixels;
        setContentView(new WalkAnimation (this,width,height));
    }
}
