package com.example.danmu.tryCatch;

/**
 * Created by anchaoguang on 2019-10-11.
 */
public class Trycatch {
    public static void f() throws MyException{
        System.out.println("Throwing MyException from f()");
        throw new MyException();
    }
    public static void g() throws MyException{
        System.out.println("Throwing MyException from g()");
        throw new MyException("Originated in g()");
    }

    public static void main(String args[]){
        try {
            f();
        }catch (MyException e){
            e.printStackTrace(System.out);
        }
        try {
            g();
        }catch (Exception e){
            e.printStackTrace(System.out);
        }
    }
}
