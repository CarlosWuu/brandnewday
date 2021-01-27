package com.carloswuu.exampleweb.utils;

/**
 * @author: wjb
 * @Date: 2020/3/26 下午8:19
 */
public class SyncUtil {
    public synchronized void test(String s){
        System.out.println(s+"in");
        for(;;){

        }
    }

    public void print(){
        System.out.println("executing...");
    }


    public static void main(String[] args) {


    }
}
