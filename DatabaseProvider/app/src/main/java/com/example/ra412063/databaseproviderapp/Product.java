package com.example.ra412063.databaseproviderapp;

/**
 * Created by ra412063 on 4/9/18.
 */

public class Product {
    private int id;
    private String productName;
    private int quantity;


    public Product(){

    }
    public Product(String s, int q){
        productName = s;
        quantity = q;
    }

    public Product(int id, String s, int q){
        productName = s;
        quantity = q;
        this.id = id;
    }

    public void setId(int i){
        id = i;
    }
    public void setProductName(String s){
        productName = s;
    }

    public  void setQuantity(int q){
        quantity = q;
    }

    public int getId(){
        return id;
    }

    public String getProductName(){
        return productName;
    }

    public  int getQuantity(){
        return quantity;
    }
}
