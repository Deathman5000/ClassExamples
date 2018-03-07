package com.example.ra412063.activitylifecycle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.widget.TextView;

public class ActivityTwo extends AppCompatActivity {

    TextView onCreateTV, onStartTV, onResumeTV;

    int create = 0;
    int start = 0;
    int resume = 0;
    int pause = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        onCreateTV = findViewById(R.id.createString);
        onStartTV = findViewById(R.id.startString);
        onResumeTV = findViewById(R.id.resumeString);
        Bundle extras = getIntent().getExtras();
        create = extras.getInt("to-create");
        start = extras.getInt("to-start");
        resume = extras.getInt("to-resume");
        create++;
        display();
        Log.i("ActivityTwo", "Entering onCreate()" + create);
    }

    @Override
    protected void onStart() {
        super.onStart();
        start++;
        Log.i("Actvity Two",
                "Entering onStart()" + start);
        display();
    }

    @Override
    protected void onResume() {
        super.onResume();
        resume++;
        Log.i("Actvity Two",
                "Entering onResume()" + resume);
        display();
    }

    @Override
    protected void onPause() {
        super.onPause();
        pause++;
        Log.i("Actvity Two",
                "Entering onPause()" + pause);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Actvity Two",
                "Entering onStop()");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Actvity Two",
                "Entering onDestroy()");
    }

    @Override
    public void finish() {
        Intent i = new Intent();
        i.putExtra("from-create", create);
        i.putExtra("from-start", start);
        i.putExtra("from-resume", resume);
        setResult(RESULT_OK, i);
        Log.i("Actvity Two",
                "Entering finish()");
        super.finish();

    }
    private void display(){
        onCreateTV.setText("onCreate() count: " + create);
        onStartTV.setText("onStart() count: "+ start);
        onResumeTV.setText("onResume() count: "+ resume);
    }
}
