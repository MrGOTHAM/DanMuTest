package com.example.danmu.tryCatch;

/**
 * Created by anchaoguang on 2019-10-12.
 */
public class MyClass {
    private static void f() throws MyOwnException {
        throw new MyOwnException();
    }

    private static void g() throws MyOwnException{
        f();
    }
    public static void main(String args[]) throws Exception {
        try {
//            f();
            g();
        }catch (MyOwnException e){

            throw (Exception) e.fillInStackTrace();
        }finally {
            System.out.println("this is finally!");
        }
    }
}
