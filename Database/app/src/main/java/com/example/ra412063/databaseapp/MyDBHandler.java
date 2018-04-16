package com.example.ra412063.databaseapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ra412063 on 3/28/18.
 * This class will have all the methods needed to create a database. If the
 * the a database exist this class will connect to it.
 */


public class MyDBHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "productDB.db";
    private static final String TABLE_PRODUCT = "product";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_PRODUCT_NAME = "productName";
    private static final String COLUMN_QUANTITY = "quantity";
    private static final int DATABASE_VERSION = 1;

    public MyDBHandler(Context c){
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_product_table = "CREATE TABLE " + TABLE_PRODUCT +"(" +
                COLUMN_ID +" INTEGER PRIMARY KEY," + COLUMN_PRODUCT_NAME + " TEXT," +
                COLUMN_QUANTITY + " INTEGER )";
        db.execSQL(create_product_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.d("MyDBHandler", "Updating db from version " + oldVersion + " to " + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);
        onCreate(db);
    }

    // helper method to work with the database when a button is clicked
    public void addProduct(Product product){
        // need an instance of ContentValue to store the values of a record
        ContentValues value = new ContentValues();
        value.put(COLUMN_PRODUCT_NAME, product.getProductName());
        value.put(COLUMN_QUANTITY, product.getQuantity());
        SQLiteDatabase database = getWritableDatabase();
        database.insert(TABLE_PRODUCT, null, value);
        database.close();
    }

    public Product[] findAll(){
        String query = "select * from " + TABLE_PRODUCT;
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(query,null);
        int n = cursor.getCount();

        //  Log.i("find All" , "number of records  " + n);
        Product [] ar = new Product[n];
        for(int i = 0; i < n; i++){
            Product p = new Product();
            cursor.moveToNext();
            p.setId(Integer.parseInt(cursor.getString(0)));
            p.setProductName(cursor.getString(1));
            p.setQuantity(Integer.parseInt(cursor.getString(2)));
            //  Log.i("find All" , "records  " + cursor.getString(0) +
            //     ", " + cursor.getString(1) + ", " +
            //         cursor.getString(2));
            ar[i] = p;

        }
        database.close();
        return ar;
    }


    public Product findProduct( String productName){
        String query =  "select * from " + TABLE_PRODUCT + " where "+
                COLUMN_PRODUCT_NAME + " = \"" + productName + "\"";

        Product p = new Product();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(TABLE_PRODUCT,null,COLUMN_PRODUCT_NAME + "= ?",new String []{productName},null,null,null);
        // Cursor cursor = database.rawQuery(query,null);
        if(cursor.moveToFirst()){
            p.setId(cursor.getInt(0));
            p.setProductName(cursor.getString(1));
            p.setQuantity(cursor.getInt(2));
        }
        else
            p = null;
        cursor.close();
        database.close();
        return  p;
    }

    public int deleteProduct(String productName){
        String query = "select * from " + TABLE_PRODUCT + " where " + COLUMN_PRODUCT_NAME + " = \"" + productName +"\"";
        // Log.i("findProduct", query);
        int n = 0;
        SQLiteDatabase database = getWritableDatabase();
        n = database.delete(TABLE_PRODUCT, COLUMN_PRODUCT_NAME + "=?",new String []{productName});
        //   Log.i("findProduct", productName + n + " deleted");
        database.close();
        return  n;

    }
}

