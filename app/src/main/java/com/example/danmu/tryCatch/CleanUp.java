package com.example.danmu.tryCatch;


/**
 * Created by anchaoguang on 2019-10-12.
 */
public class CleanUp {
    public static void main(String args[]){
        try {
            InputFile in = new InputFile("CleanUp.java");
            try {
                String s;
                int i = 1;
                while ((s = in.getLine()) != null);
            }catch (Exception e){
                System.out.println("caught Exception in main");
                e.printStackTrace(System.out);
            }finally {
                in.dispose();
            }

        }catch (Exception e){
            System.out.println("input file constrction failed");
        }
    }
}
