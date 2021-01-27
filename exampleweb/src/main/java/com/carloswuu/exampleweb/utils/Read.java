package com.carloswuu.exampleweb.utils;

import com.alibaba.fastjson.JSONObject;

import java.io.*;

/**
 * @author: wjb
 * @Date: 2020/4/20 上午10:24
 */
public class Read {

    public static void main(String[] args) {
        File file = new File("/Users/carloswuu/Desktop/syncdata/userbackupdata/rv_web_user.csv");

        File writeFile = new File("/Users/carloswuu/Desktop/syncdata/rollbackall.sql");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(writeFile));

            String s = null;

            int index = 0;
            while((s =reader.readLine())!= null){

                if(index == 1374983){
                    continue;
                }

                String[] datas = s.split(",");


                String sql =  "update rv_web_user set REGISTER_ACTIVITY_TYPE="+datas[30]+" ,REGISTRATION_CHANNEL="+datas[21]+
                            " ,USER_SOURCE ="+datas[18]+"where id = "+datas[0]+";\n";
                writer.write(sql);
                index++;
                System.out.println(index);

            }

            writer.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
