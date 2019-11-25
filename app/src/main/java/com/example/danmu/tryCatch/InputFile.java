package com.example.danmu.tryCatch;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by anchaoguang on 2019-10-12.
 */
public class InputFile {
    private BufferedReader in;
    public InputFile(String fname)throws Exception{
        try {
            in = new BufferedReader(new FileReader(fname));
            // 其它可能抛出异常的代码
        }catch (FileNotFoundException e){
            System.out.println("Could not open "+ fname);
            // 尚未打开，因此不应该关闭
            throw e;
        }catch (Exception e){
            try {
                in.close();
            }catch (IOException e2){
                System.out.println("in.close() unSuccessful");
            }
            throw e;
        }finally {
            // 不要在此关闭 in
        }
    }
    public String getLine(){
        String s;
        try{
            s = in.readLine();
        }catch (IOException e){
            throw new RuntimeException("readLine() failed");
        }
        return s;
    }
    public void dispose(){
        try{
            in.close();
            System.out.println("dispose() successful");
        }catch (IOException e2){
            throw new RuntimeException("in.close() failed");
        }
    }
}
