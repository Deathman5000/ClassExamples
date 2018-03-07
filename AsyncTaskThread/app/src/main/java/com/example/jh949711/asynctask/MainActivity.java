package com.example.jh949711.asynctask;
/*
    This app will run a long computation which may take several seconds as
    an instance of the AsyncTask class as a separate thread to run in the
    background for the long computation. The main thread, UIThread will occasionally
    monitor the progress of the long computation and add a tick mark to a progress
    bar which is displayed on the UI.
    Author: James Hund
    Version: 03/07/18
 */

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    TextView display;
    Button compute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progress_bar);
        display = findViewById(R.id.textView);
        compute = findViewById(R.id.Button);
        compute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new LongComputation().execute("40000","160000000");
            }
        });
    }

    private class LongComputation extends AsyncTask<String, Integer, String> {
        long begin, end, timeTaken;

        //this is called second
        @Override
        protected String doInBackground(String... strings) {
            double result = 0;
            int time = 0;
            long max, maxCount, count = 1;
            max = Long.parseLong(strings[0]);
            maxCount = Long.parseLong(strings[1]);
            for(int i = 1; i <= max; i++){
                for(int j = 1; j <= max; j++){
                    result = Math.cosh((i+j)/max);
                    count++;
                    if(count == maxCount){
                        time++;
                        count = 1;
                        publishProgress(time);
                    }
                }
            }
            String st = String.format("Result = %.2f", result);
            return st;
        }

        //this is called first
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            begin = System.nanoTime();
        }
        //this is called third
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            end = System.nanoTime();
            timeTaken = (end - begin)/1000000000;
            display.setText("Time taken for computation = " + timeTaken +" seconds"+"\n" + s);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }
    }
}
