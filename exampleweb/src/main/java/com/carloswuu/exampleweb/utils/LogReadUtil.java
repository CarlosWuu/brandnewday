package com.carloswuu.exampleweb.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: wjb
 * @Date: 2020/4/17 下午4:39
 */
public class LogReadUtil {

    public static void main(String[] args) {
        File logFile = new File("/Users/carloswuu/Desktop/syncdata/login_log.csv");

        File logtxt = new File("/Users/carloswuu/Desktop/syncdata/log.txt");

        File differencetxt = new File("/Users/carloswuu/Desktop/syncdata/differenceNew.txt");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        HashMap<String, List<Log>> logmap = new HashMap<>();

        HashMap<String,Log> dealedLogMap = new HashMap<>();

        HashMap<String,Log> emptyUserMap = new HashMap<>();

        HashMap<String,Log> resultLogMap = new HashMap<>();

        try {
            BufferedReader logReader = new BufferedReader(new InputStreamReader(new FileInputStream(logFile)));

            BufferedWriter differencetxtWriter = new BufferedWriter(new FileWriter(differencetxt));

            String s = null;
            while((s = logReader.readLine()) != null){
                String[] split = s.split(",");

                String id = split[0].replaceAll("\"","");
                String loginChannelSource = split[1].replaceAll("\"","");
                String loginSource = split[2].replaceAll("\"","");
                String stringDate = split[3].replaceAll("\"","");

                Date date = simpleDateFormat.parse(stringDate);

                Log log = new Log();
                log.setCreateDate(date);
                log.setRegisterChannel(loginChannelSource);
                log.setUserSource(loginSource);
                log.setId(id);

                List<Log> logs = logmap.get(id);
                if(CollectionUtils.isEmpty(logs)){

                    List<Log> ls = new ArrayList<>();
                    ls.add(log);
                    logmap.put(id,ls);
                }else{
                    logs.add(log);
                }
            }

            for(Map.Entry<String,List<Log>> entry : logmap.entrySet()){
                List<Log> value = entry.getValue();

                Optional<Log> first = value.stream().sorted(Comparator.comparing(Log::getCreateDate)).findFirst();

                dealedLogMap.put(entry.getKey(),first.get());
            }

            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/Users/carloswuu/Desktop/syncdata/update/emptyUser.txt")));

            String emptyUser = null;
            while((emptyUser=bufferedReader.readLine())!=null){
                Log log = JSONObject.parseObject(emptyUser,Log.class);
                emptyUserMap.put(log.getId(),log);
            }


            List<String> dealedId = new ArrayList<>();
            int index = 0;
            int index2 = 0;
            for(Map.Entry<String,Log> entry : dealedLogMap.entrySet()){
                Log value = entry.getValue();
                String id = entry.getKey();

                Log emptyLog = emptyUserMap.get(id);

                if((!value.getRegisterChannel().equals(emptyLog.getRegisterChannel().replaceAll("'",""))) && (!value.getUserSource().equals(emptyLog.getUserSource().replaceAll("'","")))){
                    resultLogMap.put(id,value);
                    differencetxtWriter.write(index+"、origin data:"+JSONObject.toJSONString(value)+"current data:"+JSONObject.toJSONString(emptyLog)+"\n");
                    if("86010004".equals(value.getUserSource())){
                        String result = "update rv_web_user set USER_SOURCE='"+value.getUserSource()+"',set REGISTER_ACTIVITY_TYPE='1001' where id="+value.getId();
                        index2++;
                        dealedId.add(value.getId());
                        System.out.println(result);
                    }
                    index++;
                }
            }
            System.out.println(index);
            System.out.println(index2);

            differencetxtWriter.flush();

            for(String id :dealedId){
                resultLogMap.remove(id);
            }

            for(Map.Entry<String,Log> entry : resultLogMap.entrySet()){
                Log value = entry.getValue();
                String id = entry.getKey();

                Log emptyLog = emptyUserMap.get(id);

                System.out.println("origin data:"+JSONObject.toJSONString(value)+"current data:"+JSONObject.toJSONString(emptyLog)+"\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    static class Log{
        private String id;

        private String registerActivityType;

        private String registerChannel;

        private String userSource;

        private Date createDate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRegisterActivityType() {
            return registerActivityType;
        }

        public void setRegisterActivityType(String registerActivityType) {
            this.registerActivityType = registerActivityType;
        }

        public String getRegisterChannel() {
            return registerChannel;
        }

        public void setRegisterChannel(String registerChannel) {
            this.registerChannel = registerChannel;
        }

        public String getUserSource() {
            return userSource;
        }

        public void setUserSource(String userSource) {
            this.userSource = userSource;
        }

        public Date getCreateDate() {
            return createDate;
        }

        public void setCreateDate(Date createDate) {
            this.createDate = createDate;
        }
    }
}
