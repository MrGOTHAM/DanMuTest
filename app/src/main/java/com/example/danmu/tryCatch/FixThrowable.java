package com.example.danmu.tryCatch;

/**
 * Created by anchaoguang on 2019-10-12.
 */
public class FixThrowable {
    public int chuli(int a, int b) throws ThisException, OtherException {
        int g;
        if (a<b){
            throw new ThisException("出现了异常");
        }else if (a >= b){
            g = a - b;
            System.out.println("a-b的绝对值为 :"+ g);
        }else {
            throw new OtherException("输入异常");
        }
        return g;
    }
}
