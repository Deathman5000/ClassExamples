package com.example.ra412063.databaseproviderapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/*
   The app with this entry point class is used to store a list of
   items and their quantities in a database. We will have a inner class that
   will extend the SQLiteOpenHelper class for the database. To make the database accessible to
   other apps will create the database in a class that extends the ContentProvider class.
   author rajan alex
   version: 04/09/18

 */
public class DatabaseProviderActivity extends AppCompatActivity {

    TextView idView;
    EditText productBox, quantityBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_provider);
        idView = findViewById(R.id.productID);
        productBox = findViewById(R.id.productName);
        quantityBox = findViewById(R.id.productQuantity);
        idView.setEnabled(false);
    }
    //definitions of the onClick methods
    public void show (View view){
        idView.setText("not-assigned");
        productBox.setText("");
        quantityBox.setText("");
        productBox.requestFocus();
        Intent intent = new Intent(this, DisplayActivity.class);
        startActivity(intent);
    }
    public void newProduct(View view){
        Product product = new Product(productBox.getText().toString(),
                Integer.parseInt(quantityBox.getText().toString()));
        ContentValues values = new ContentValues();
        values.put(MyContentProvider.COLUMN_PRODUCT_NAME, product.getProductName());
        values.put(MyContentProvider.COLUMN_QUANTITY, product.getQuantity());
        Uri uri = getContentResolver().insert(MyContentProvider.CONTENT_URI,values);
        Toast.makeText(this,"New product " + product.getProductName() + ", " +
              product.getQuantity() + " added",Toast.LENGTH_SHORT ).show();
        productBox.setText("");
        quantityBox.setText("");
    }
    public void lookupProduct(View view){
        String [] projection = {MyContentProvider.COLUMN_ID,
                    MyContentProvider.COLUMN_PRODUCT_NAME,
        MyContentProvider.COLUMN_QUANTITY};
        String selection = "productName = \"" + productBox.getText().toString()+"\"";
        Cursor cursor = getContentResolver().query(MyContentProvider.CONTENT_URI,
                projection,selection,null,null);
        Product product = new Product();
        if(cursor.moveToFirst()){
            product.setId(cursor.getInt(0));
            product.setProductName(cursor.getString(1));
            product.setQuantity(cursor.getInt(2));
        }
        else
            product = null;
        if(product != null){
            idView.setText(String.valueOf(product.getId()));
            quantityBox.setText(String.valueOf(product.getQuantity()));
        }
        else
            idView.setText("No match found");
    }

    public void removeProduct(View view){
        String selection = "productName = \"" + productBox.getText().toString() + "\"";
        int result =
                getContentResolver().delete(MyContentProvider.CONTENT_URI,selection,null);
        if(result > 0){
            idView.setText(""+result+" deleted");
            productBox.setText("");
            quantityBox.setText("");

        }
        else
            idView.setText("No match");
    }
}
