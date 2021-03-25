package com.sectest.seckilldemo;

public class test {

    public static void main(String[] args) {
        String s = "00001";
        if(s.length()>5){
            String dd = s.substring(s.length()-5,s.length());
            System.out.println(dd);
            Integer a = Integer.parseInt(s);
            System.out.println(a);
        }
        System.out.println("ddd");
    }
}
