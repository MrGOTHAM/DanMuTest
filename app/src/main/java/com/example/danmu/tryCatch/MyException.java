package com.example.danmu.tryCatch;

/**
 * Created by anchaoguang on 2019-10-11.
 */
public class MyException extends Exception{
    int x;

    MyException(){

    }
    MyException(String msg){
        super(msg);
    }
    MyException(String msg,int x){
        super(msg);
        this.x = x;
    }
    public int val(){
        return x;
    }
    public String getMessage(){
        return "Detail Message: "+ x +" "+ super.getMessage();
    }
}
