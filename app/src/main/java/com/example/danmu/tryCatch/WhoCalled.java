package com.example.danmu.tryCatch;

/**
 * Created by anchaoguang on 2019-10-11.
 */
public class WhoCalled {
static void f(){
    try {
        throw new Exception();
    }catch (Exception e){
        for (StackTraceElement ste: e.getStackTrace())
            System.out.println(ste);
    }
}
static void g(){
    f();
}
static void h(){
    g();
}
public static void main(String args[]){
    f();
    System.out.println("---------------");
    g();
    System.out.println("---------------");
    h();
    System.out.println("---------------");
}
}
