package com.example.ra412063.activitylifecycle;
/*
   This program will show the methods that get called when an app is
   launched. It will display the number of times the onCreate, onStart,
   and onResume get called, show how to use Log as a tool for debug,
   how to pass values from one activity to another, and
   how one activity can call another activity
   author: rajan alex
   version: 2/7/18

 */
import android.content.Intent;
import android.os.TestLooperManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityOne extends AppCompatActivity {

    TextView onCreateTV, onStartTV, onResumeTV;
    Button next;
    int create = 0;
    int start = 0;
    int resume = 0;
    int pause = 0;
    final int MY_REQUEST_CODE = 1;

    @Override
    protected void onStart() {
        super.onStart();
        start++;
        Log.i("Actvity One",
                "Entering onStart()" + start);
        display();
    }

    @Override
    protected void onResume() {
        super.onResume();
        resume++;
        Log.i("Actvity One",
                "Entering onResume()" + resume);
        display();
    }

    @Override
    protected void onPause() {
        super.onPause();
        pause++;
        Log.i("Actvity One",
                "Entering onPause()" + pause);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Actvity One",
                "Entering onStop()");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Actvity One",
                "Entering onDestroy()");
    }

    @Override
    public void finish() {
        super.finish();
        Log.i("Actvity One",
                "Entering finish()");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == MY_REQUEST_CODE)
            if(resultCode == RESULT_OK){
               create = data.getExtras().getInt("from-create");
               start = data.getExtras().getInt("from-start");
               resume = data.getExtras().getInt("from-resume");
            }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        onCreateTV = findViewById(R.id.createString);
        onStartTV = findViewById(R.id.startString);
        onResumeTV = findViewById(R.id.resumeString);
        next = findViewById(R.id.nextButton);
        create++;
        Log.i("Actvity One", "Entering onCreate()" + create);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityOne.this,
                        ActivityTwo.class);
                intent.putExtra("to-create", create);
                intent.putExtra("to-start", start);
                intent.putExtra("to-resume", resume);
                startActivityForResult(intent,
                        MY_REQUEST_CODE);
                // or call startActivity(intent);
            }
        });
        display();
    }
    private void display(){
        onCreateTV.setText("onCreate() count: " + create);
        onStartTV.setText("onStart() count: "+ start);
        onResumeTV.setText("onResume() count: "+ resume);
    }


}
