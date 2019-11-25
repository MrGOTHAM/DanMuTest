package com.example.danmu.tryCatch;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by anchaoguang on 2019-10-12.
 */
public class Task2 {
    public static void main(String args[]){
        int a = 0;
        int b = 0;
        while (true){
            System.out.println("计算  |a-b| 的绝对值");
            Scanner in = new Scanner(System.in);
            try{
            System.out.println("输入a:");
             a = in.nextInt();
            System.out.println("输入b:");
            b = in.nextInt();

                FixThrowable fix = new FixThrowable();

                System.out.println("绝对值为 :"+ fix.chuli(a,b));
                System.out.println("无异常");

            }catch (ThisException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                System.out.println("有异常");
                System.out.println("b-a:"+(b-a));
            }catch (InputMismatchException e){
                System.out.println("输入异常，请重新输入");
            } catch (OtherException e) {
                e.printStackTrace();
            }
        }
    }
}
class ThisException extends Exception{
    ThisException(String s){
        super(s);
    }
}
class OtherException extends Exception{
    OtherException(String s){
        super(s);
    }
}
