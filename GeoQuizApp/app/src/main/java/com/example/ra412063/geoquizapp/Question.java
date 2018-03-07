package com.example.ra412063.geoquizapp;

/**
 * Created by ra412063 on 1/24/18.
 */

public class Question {
    private String statement;
    private boolean answer;

    public void setStatement(String s){
        statement = s;
    }
    public void setAnswer(boolean b){
        answer = b;
    }
    public String getStatement(){
        return statement;
    }
    public boolean getAnswer(){
        return answer;
    }
}
