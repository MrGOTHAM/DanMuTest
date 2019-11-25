package com.example.danmu.tryCatch;

import java.util.Scanner;

/**
 * Created by anchaoguang on 2019-10-12.
 */
public class Task1 {
    public static void main(String args[]) {
        while (true) {
            System.out.println("a/b等于几？");
            Scanner in = new Scanner(System.in);
            System.out.println("输入a的值:");
            int a = in.nextInt();
            System.out.println("输入b的值:");
            int b = in.nextInt();
            try {
                System.out.println("a/b=" + a / b);
            } catch (Exception e) {
                System.out.println("输入错误，除数不能为0");
            }
        }
    }
}
