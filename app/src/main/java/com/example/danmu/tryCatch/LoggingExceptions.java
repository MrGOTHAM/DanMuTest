package com.example.danmu.tryCatch;

/**
 * Created by anchaoguang on 2019-10-11.
 */
public class LoggingExceptions {
    public static void main(String args[]){
        try{
            throw new LoggingException();
        }catch (LoggingException e){
            System.err.println("Caught" + e);
        }
        try{
            throw new LoggingException();
        }catch (LoggingException e){
            System.err.println("Caught" + e);
        }

    }
}
