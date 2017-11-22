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
    public static void main(String[] args) throws UnsupportedEncodingException, InterruptedException{
    	
    	DESUtil d = new DESUtil("12345678");
    	//º”√‹
    	String str = d.encrypt("13013013013");
    	System.out.println(str);
    	//Ω‚√‹
    	String str2 = d.decrypt(str);
    	System.out.println(str2);
    }
}
