package com.example.ra412063.stockprice;
/*
    This app uses a URL to get the current price of stock  when the stock is normally traded
    in a securities trading exchange. For example,
    the URL https://query1.finance.yahoo.com/v8/finance/chart/MSFT?interval=2m
    returns the stock price Microsoft company. A BufferedReader instance is
    used to get the data as a string which needs to be then tokenized to extract the data of interest.
    The app will allow the user to enter a valid stock symbol and display the
    previous business day closing time price.
    author: rajan alex
    version: 3/19/2018
 */

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class StockPriceActivity extends AppCompatActivity {

   EditText symbol;
    TextView price;
    Button compute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_price);
        symbol =(EditText)findViewById(R.id.editText);
        price = (TextView)findViewById(R.id.textView2);
        compute = findViewById(R.id.button);
        compute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String st = symbol.getText().toString();
                st = "https://query1.finance.yahoo.com/v8/finance/chart/"+st+"?interval=2m";
                Log.i("UIThread", st);
                new FindPrice().execute(st);
            }
        });
    }
    /*
       An alternate to setting a click listener on the button using setOnClickListener(View.OnClickListener) is
       to add the onClick attribute to the button defined in the xml file and
       implement a void function for the attribute name telling what action to take when the button is
       clicked.
     */

 /*   protected void compute(View view){

        String st = symbol.getText().toString();
        st = "https://query1.finance.yahoo.com/v8/finance/chart/"+st+"?interval=2m";
        Log.i("UIThread", st);
        new FindPrice().execute(st);


    }*/
    /*
        FindPrice has three template types in general. The first type is used to specify the type
        for the parameter passed at the time of invoking execute() method to the doInBackground() method, the second type is
        used to pass parameter to onProgressUpdate() method by calling publishProgress(), and the third type
        is returned by the doInBackground() method which is passed to onPostExecute()
        method. In this example, the first type is a String, second type is Void since it
        is not used, and the third type is a String.
     */
    private class FindPrice extends AsyncTask<String,Void,String> {
        String ticker;

        @Override
        protected String doInBackground(String... params) {
            String priceString = "";
            try {
                Log.i("doInBackground", "Stock symbol is " + symbol.getText().toString());
                URL url = new URL(params[0]);
                BufferedReader bufferedReader =  new BufferedReader(new InputStreamReader(url.openStream()));
                String line = bufferedReader.readLine();
                String[] ar = line.split("\"previousClose\":");
                String[] sr = ar[1].split(",");
                priceString = sr[0];
                Log.i("doInBackground", "Stock price " + priceString);
                bufferedReader.close();
            }
            catch (MalformedURLException e){
                Toast.makeText(getApplicationContext(), "URL Error",Toast.LENGTH_SHORT).show();
            }
            catch (IOException e) {
                Toast.makeText(getApplicationContext(), "URL Error",Toast.LENGTH_SHORT).show();
            }
            return priceString;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ticker = symbol.getText().toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Double price_p = Double.parseDouble(s);
            price.setText("Price of " + ticker + " for the previous day " + String.format("$%,.2f",price_p));
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
