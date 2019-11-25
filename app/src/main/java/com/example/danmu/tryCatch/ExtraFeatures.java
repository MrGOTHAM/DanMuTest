package com.example.danmu.tryCatch;

/**
 * Created by anchaoguang on 2019-10-11.
 */
public class ExtraFeatures {
    public static void f() throws MyException{
        System.out.print("Throwing MyException from f()");
        throw new MyException();
    }

    public static void g() throws MyException{
        System.out.print("Throwing MyExceptiom from g()");
        throw new MyException("Originated in g()");
    }

    public static void h() throws MyException{
        System.out.print("Throwing MyException from h()");
        throw new MyException("Originated in h()",47);
    }

    public static void main(String args[]){
        try {
            f();
        }catch (MyException e){
            e.printStackTrace(System.out);
        }
        try {
            g();
        }catch (MyException e){
            e.printStackTrace(System.out);
        }
        try {
            h();
        }catch (MyException e){
            e.printStackTrace(System.out);
            System.out.println("e.val  =  " + e.val());
        }
    }
}
