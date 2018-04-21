package com.example.ra412063.databaseproviderapp;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

/*
   The class will provide the AUTHORITY string and the
   CONTENT_URI to access the productDb.db and its table
 */
public class MyContentProvider extends ContentProvider {

    private static final String AUTHORITY = "com.example.ra412063.databaseproviderapp.MyContentProvider";
    private static final String DATABASE_NAME = "productDB.db";
    private static final String TABLE_PRODUCT = "product";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PRODUCT_NAME = "productName";
    public static final String COLUMN_QUANTITY = "quantity";
    private static final int DATABASE_VERSION = 1;
    public static final int PRODUCTS = 1;
    public static final int PRODUCTS_ID = 2;
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" +TABLE_PRODUCT);
    private SQLiteDatabase sqlDB;
    private UriMatcher uriMatcher;

    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int uriType = uriMatcher.match(uri);
        int rowsDeleted = 0;
        switch (uriType){
            case PRODUCTS: rowsDeleted = sqlDB.delete(TABLE_PRODUCT,selection, selectionArgs);
                           break;
            default: throw  new  UnsupportedOperationException("Unkonow URI: " + uri +
                    " is not supported");
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return rowsDeleted;
    }

    @Override
    public String getType(Uri uri) {
        return null;

    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int uriTtpe = uriMatcher.match(uri);
        long id = 0;
        switch (uriTtpe){
            case PRODUCTS: sqlDB.insert(TABLE_PRODUCT,null, values);
                           break;
            default: throw  new  UnsupportedOperationException("Unkonow URI: " + uri +
                    " is not supported");
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return uri.parse(TABLE_PRODUCT + "/" + id);

    }

    @Override
    public boolean onCreate() {
        //next three lines are used to start a content provider class
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, TABLE_PRODUCT, PRODUCTS);
        uriMatcher.addURI(AUTHORITY, TABLE_PRODUCT +"/#", PRODUCTS_ID);
        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        sqlDB = dbHelper.getWritableDatabase();
        if (sqlDB != null)
            return true;
        else
           return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(TABLE_PRODUCT);
        int uriType = uriMatcher.match(uri);
        switch (uriType){
            case PRODUCTS: break;
            default:throw  new  UnsupportedOperationException("Unkonow URI: " + uri +
                    " is not supported");
        }
        Cursor cursor = queryBuilder.query(sqlDB,projection,selection,selectionArgs,
                null,null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return  cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int rowsUpdated = 0;
        int uriTtpe = uriMatcher.match(uri);
        switch (uriTtpe){
            case PRODUCTS: rowsUpdated =
                    sqlDB.update(TABLE_PRODUCT, values,selection,selectionArgs);
                           break;
            default: throw  new  UnsupportedOperationException("Unkonow URI: " + uri +
                    " is not supported");
        }

        return rowsUpdated;
    }
    private static class DatabaseHelper extends SQLiteOpenHelper{

        public DatabaseHelper(Context c){
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

    }
}
