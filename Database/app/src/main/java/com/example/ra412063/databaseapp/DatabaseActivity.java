package com.example.ra412063.databaseapp;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class DatabaseActivity extends AppCompatActivity {

    TextView idView;
    EditText quantityBox, productBox;
    TableLayout table;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        idView = findViewById(R.id.ProductID);
        quantityBox = findViewById(R.id.product_qty);
        productBox = findViewById(R.id.product_name);
        table = findViewById(R.id.list_item);
        idView.setEnabled(false);
        productBox.requestFocus();
    }

    public void newProduct(View view){
        MyDBHandler dbHandler = new MyDBHandler(this);
        int quantity = Integer.parseInt(quantityBox.getText().toString());
        String name = productBox.getText().toString();
        Product product = new Product(name,quantity);
        dbHandler.addProduct(product);
        Toast.makeText(this, "new prduct " + name + ", " + quantity +" added", Toast.LENGTH_SHORT ).show();
        table.removeAllViews();
        idView.setText("not-assigned");
        productBox.setText("");
        quantityBox.setText("");
        productBox.requestFocus();
    }

    public void findProduct(View view){

        MyDBHandler dbHandler = new MyDBHandler(this);
        Product product = dbHandler.findProduct(productBox.getText().toString());
        if(product != null){
            idView.setText(String.valueOf(product.getId()));
            quantityBox.setText(String.valueOf(product.getQuantity()));
        }
        else{
            idView.setText("No match found");
        }
        table.removeAllViews();
    }

    public void removeProduct(View view){
        table.removeAllViews();
        MyDBHandler dbHandler = new MyDBHandler(this);
        int n = dbHandler.deleteProduct(productBox.getText().toString());
        if(n > 0){
            idView.setText(n + " records deleted");
            productBox.setText("");
            quantityBox.setText("");
        }
        else
            idView.setText("No match deleted");
        productBox.requestFocus();
    }

    public void showAll(View view){
        MyDBHandler dbHandler = new MyDBHandler(this);
        Product[] ar = dbHandler.findAll();
        reset(view);
        //  Log.i("showAll", "number of records " + ar.length );
        if(ar.length > 0){
            for(int i = 0; i < ar.length; i++){
                TableRow row = new TableRow(this);
                TextView t1 = new TextView(this);
                t1.setText(""+ar[i].getId());
                t1.setPadding(30,20,0,0);
                t1.setTypeface(Typeface.DEFAULT_BOLD);
                TextView t2 = new TextView(this);
                t2.setText(ar[i].getProductName());
                t2.setPadding(30,20,0,0);
                t2.setTypeface(Typeface.DEFAULT_BOLD);
                TextView t3 = new TextView(this);
                t3.setText(""+ar[i].getQuantity());
                t3.setPadding(30,20,0,0);
                t3.setTypeface(Typeface.DEFAULT_BOLD);
                row.addView(t1);
                row.addView(t2);
                row.addView(t3);
                table.addView(row);
            }
        }
        else{
            TableRow row = new TableRow(this);
            TextView t1 = new TextView(this);
            t1.setText("table is empty");
            row.addView(t1);
            table.addView(row);
        }
        productBox.requestFocus();
    }
    public void reset(View view){
        //first three lines are to hide the input soft keypad
        Context context = getApplicationContext();
        InputMethodManager inputManager =
                (InputMethodManager) context.
                        getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(
                this.getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        table.removeAllViews();
        idView.setText("not-assigned");
        productBox.setText("");
        quantityBox.setText("");
        productBox.requestFocus();
    }

}
