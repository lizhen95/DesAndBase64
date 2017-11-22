package com.lz.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Random;

import javax.swing.plaf.SliderUI;

public class test {
	public static String txt2String(File file){
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(System.lineSeparator()+s);
            }
            br.close();    
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }
    
    public static void main(String[] args) throws UnsupportedEncodingException, InterruptedException{
    	
    	DESUtil d = new DESUtil("12345678");
    	System.out.println(d.encrypt("13013013013"));
    	int i = 1;
    	while(true){
    	System.out.print((int)(Math.random()*10));
    	Date d1=new Date();
    	Date d2=new Date();
    	if(i%80==0){
    		System.out.println();
    	}
    	i += 1;
//    	while(d1.getTime()+3000>d2.getTime()){
//    		d2=new Date();
//    	}
    	Thread.sleep(200);
    	}
    }
}
