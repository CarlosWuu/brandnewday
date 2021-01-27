package com.carloswuu.exampleweb.utils;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author: wjb
 * @Date: 2020/3/6 下午10:05
 */
public class ReadUtil {
    public static void main(String[] args) {
        File rv_web_user20bakFile = new File("/Users/carloswuu/Desktop/syncdata/webUser48.csv");

        File rv_web_user10File = new File("/Users/carloswuu/Desktop/syncdata/webuser.csv");



        try {
            BufferedReader f20Reader = new BufferedReader(new InputStreamReader(new FileInputStream(rv_web_user20bakFile)));

            BufferedReader f10Reader = new BufferedReader(new InputStreamReader(new FileInputStream(rv_web_user10File)));

            HashMap<String,User> user20 = new HashMap<>();

            HashMap<String,User> user10 = new HashMap<>();

            String s = null;
            while((s = f20Reader.readLine() )!= null){
                String[] split = s.split(",");
                User user = new User(split[0].replaceAll("\"",""),split[1].replaceAll("\"","'"),split[2].replaceAll("\"","'"),split[3].replaceAll("\"","'"));
                user20.put(split[0].replaceAll("\"",""),user);
            }

            while((s = f10Reader.readLine() )!= null){
                String[] split = s.split(",");
                User user = new User(split[0].replaceAll("\"",""),split[1].replaceAll("\"","'"),split[2].replaceAll("\"","'"),split[3].replaceAll("\"","'"));
                user10.put(split[0].replaceAll("\"",""),user);
            }

            BufferedWriter emptyUser = new BufferedWriter(new FileWriter(new File("/Users/carloswuu/Desktop/syncdata/emptyUserId.txt")));

            BufferedWriter differenceUser = new BufferedWriter(new FileWriter(new File("/Users/carloswuu/Desktop/syncdata/differen.txt")));
            BufferedWriter updateWriter = new BufferedWriter(new FileWriter(new File("/Users/carloswuu/Desktop/syncdata/update.sql")));

            int index = 0;
            int index2 = 0;

            StringBuilder sb = new StringBuilder();
            for(Map.Entry<String,User> entry : user10.entrySet()){
                String id = entry.getKey();
                User user = user20.get(id);
                if(user == null){
                    sb.append(id).append(",");

                    index++;
                }else{
                    if(!entry.getValue().equals(user)){

                        if(user.getRegisterChannel().equals("'西安长安大道服务点'")){
                            continue;
                        }
                        String differenceResult = "origin data:"+JSONObject.toJSONString(user)+"---current data:"+JSONObject.toJSONString(entry.getValue())+"\n";

                        String updateSql = "update rv_web_user set REGISTER_ACTIVITY_TYPE="+
                                user.getRegisterActivityType()+", REGISTRATION_CHANNEL="+user.getRegisterChannel()
                                +", USER_SOURCE = "+user.getUserSource()+" where id="+user.getId()+";\n";
                        if(user.getUserSource().equals("'86010008'")){
                            updateSql = "update rv_web_user set REGISTER_ACTIVITY_TYPE="+
                                    user.getRegisterActivityType()+" where id="+user.getId()+";\n";
                        }

                        updateWriter.write(updateSql);
                        differenceUser.write(differenceResult);
                        index2++;
                    }

                }
            }
            emptyUser.write(sb.toString());
            emptyUser.flush();
            differenceUser.flush();
            updateWriter.flush();

            System.out.println("不存在用户:"+index+" 差异用户:"+index2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    static class User{
        private String id;
        private String userSource;
        private String registerChannel;
        private String registerActivityType;

        public User() {
        }

        public User(String id, String userSource, String registerChannel, String registerActivityType) {
            this.id = id;
            this.userSource = userSource;
            this.registerChannel = registerChannel;
            this.registerActivityType = registerActivityType;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            User user2 = (User)obj;
            boolean equals = user2.getId().equals(this.id) && user2.getRegisterActivityType().equals(convertActivityType(this.registerActivityType))
                            && user2.getRegisterChannel().equals(convertChannel(this.getRegisterChannel())) && user2.getUserSource().equals(this.userSource);

            return equals;
        }

        private String convertActivityType(String registerActivityType){
            if(registerActivityType.equals("'ACTIVITY_MART_COMMON_$_市场线下通用'")){
                return "'市场线下通用'";
            }else if(registerActivityType.equals("'SHOP_$_门市拉新'")){
                return "'门市拉新'";
            }else if(registerActivityType.equals("'DAILY_$_内部日常'")){
                return "'内部日常'";
            }else if(registerActivityType.equals("'ACTIVITY_MART_COMMON_$_市场线下通用'")){
                return "'用户服务中心线下通用'";
            }else if(registerActivityType.equals("'PC_$_PC官网'")){
                return "'PC官网'";
            }else if(registerActivityType.equals("'SERVICE_$_用户服务中心线下通用'")){
                return "'用户服务中心线下通用'";
            }else if(registerActivityType.equals("'SEASONAL_$_季节性项目'")){
                return "'季节性项目'";
            }else if(registerActivityType.equals("'BD_$_BD-油菜花节'")){
                return "'BD-油菜花节'";
            }

            return  registerActivityType;
        }

        private String convertChannel(String registerChannel){
            if(registerChannel.equals("'99'")){
                return "'网站运营部'";
            }else if(registerChannel.equals("'1003'")){
                return "'一嗨'";
            }else if(registerChannel.equals("'2'")){
                return "'众熙'";
            }else if(registerChannel.equals("'3'")){
                return "'房车生活家'";
            }else if(registerChannel.equals("'18'")){
                return "'广州番禺服务点'";
            }else if(registerChannel.equals("'21'")){
                return "'成都植物园服务点'";
            }else if(registerChannel.equals("'10'")){
                return "'上海曹安公路服务点'";
            }else if(registerChannel.equals("'22'")){
                return "'厦门机场服务点'";
            }else if(registerChannel.equals("'20'")){
                return "'三亚机场服务点'";
            }else if(registerChannel.equals("'23'")){
                return "'武汉汉口北服务点'";
            }else if(registerChannel.equals("'16'")){
                return "'北京房山服务点'";
            }else if(registerChannel.equals("'13'")){
                return "'南京聚宝山服务点'";
            }else if(registerChannel.equals("'15'")){
                return "'溧阳服务点'";
            }else if(registerChannel.equals("'19'")){
                return "'海口机场服务点'";
            }else if(registerChannel.equals("'17'")){
                return "'西安长安大道服务点'";
            }else if(registerChannel.equals("'25'")){
                return "'兰州机场服务点'";
            }else if(registerChannel.equals("'103'")){
                return "'膨胀助力油卡'";
            }else if(registerChannel.equals("'66'")){
                return "'苏州东环路服务点'";
            }else if(registerChannel.equals("'53'")){
                return "'上汽MAXUS APP'";
            }else if(registerChannel.equals("'73'")){
                return "'福建省区'";
            }else if(registerChannel.equals("'72'")){
                return "'陕西省区'";
            }else if(registerChannel.equals("'65'")){
                return "'杭州通益路服务点'";
            }else if(registerChannel.equals("'70'")){
                return "'湖北省区'";
            }else if(registerChannel.equals("'83'")){
                return "'中国房车网'";
            }else if(registerChannel.equals("'29'")){
                return "'上海宝隆'";
            }else if(registerChannel.equals("'59'")){
                return "'公司接待-人事行政'";
            }else if(registerChannel.equals("'100'")){
                return "'抖音KOL-李立童'";
            }else if(registerChannel.equals("'109'")){
                return "'房车江湖'";
            }else if(registerChannel.equals("'60'")){
                return "'大通俱乐部1'";
            }else if(registerChannel.equals("'62'")){
                return "'大通俱乐部3'";
            }else if(registerChannel.equals("'42'")){
                return "'上海浦兴'";
            }else if(registerChannel.equals("'51'")){
                return "'pc顶部'";
            }else if(registerChannel.equals("'52'")){
                return "'pc侧边栏'";
            }else if(registerChannel.equals("'78'")){
                return "'云南省区'";
            }else if(registerChannel.equals("'91'")){
                return "'南宁火车东站服务点'";
            }else if(registerChannel.equals("'88'")){
                return "'郑州中州大道服务点'";
            }else if(registerChannel.equals("'108'")){
                return "'自媒体-达人老马'";
            }else if(registerChannel.equals("'81'")){
                return "'房车评'";
            }else if(registerChannel.equals("'80'")){
                return "'房车情报'";
            }else if(registerChannel.equals("'82'")){
                return "'房车市场网'";
            }
            else if(registerChannel.equals("'40'")){
                return "'上海华裕'";
            }else if(registerChannel.equals("'84'")){
                return "'哈尔滨机场路服务点'";
            }else if(registerChannel.equals("'89'")){
                return "'深圳植物园路服务点'";
            }else if(registerChannel.equals("'97'")){
                return "'昆明春雨路服务点'";
            }else if(registerChannel.equals("'111'")){
                return "'租赁出行中心'";
            }else if(registerChannel.equals("'69'")){
                return "'四川省区'";
            }else if(registerChannel.equals("'63'")){

                return "'车身贴'";
            }else if(registerChannel.equals("'14'")){
                return "'媒体1'";
            }else if(registerChannel.equals("'75'")){
                return "'广州省区'";
            }else if(registerChannel.equals("'110'")){
                return "'北京顺义体验服务'";
            }else if(registerChannel.equals("'74'")){
                return "'海南省区'";
            }else if(registerChannel.equals("'107'")){
                return "'重庆渝北区服务点'";
            }else if(registerChannel.equals("'87'")){
                return "'路程网展会'";
            }else if(registerChannel.equals("'68'")){
                return "'上海省区'";
            }else if(registerChannel.equals("'37'")){
                return "'上海云峰'";
            }else if(registerChannel.equals("'64'")){
                return "'无锡先锋汽车城服务'";
            }else if(registerChannel.equals("'79'")){
                return "'房车游讯'";
            }else if(registerChannel.equals("'67'")){
                return "'北京省区'";
            }else if(registerChannel.equals("'76'")){
                return "'南京城市站'";
            }else if(registerChannel.equals("'95'")){

                return "'河南省区'";
            }else if(registerChannel.equals("'114'")){
                return "'重庆省区'";
            }else if(registerChannel.equals("'96'")){
                return "'浙江省区'";
            }else if(registerChannel.equals("'127'")){
                return "'上海大通音乐谷服务点'";
            }else if(registerChannel.equals("'39'")){
                return "'上海鼎汇'";
            }else if(registerChannel.equals("'106'")){
                return "'自嗨锅-联合产品'";
            }else if(registerChannel.equals("'86'")){
                return "'西宁体育公园服务点'";
            }

            return registerChannel;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserSource() {
            return userSource;
        }

        public void setUserSource(String userSource) {
            this.userSource = userSource;
        }

        public String getRegisterChannel() {
            return registerChannel;
        }

        public void setRegisterChannel(String registerChannel) {
            this.registerChannel = registerChannel;
        }

        public String getRegisterActivityType() {
            return registerActivityType;
        }

        public void setRegisterActivityType(String registerActivityType) {
            this.registerActivityType = registerActivityType;
        }
    }


    public static void newListFile(File[] files){
        for(File file:files){
            if(file.isDirectory()){
                listFile(file.listFiles());
            }else{
                boolean pom = file.getName().endsWith(".pom");
                if(pom){
                    try {
                        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));//构造一个BufferedReader类来读取文件
                        String s = null;
                        StringBuilder sb = new StringBuilder("mvn deploy:deploy-file -DgroupId=com.meidusa.venus");


                        while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行


                            if(s.contains("<artifactId>")){
                                sb.append(" -DartifactId=");
                                sb.append(s.replace("<artifactId>","").replace("</artifactId>","").trim());
                            }else if(s.contains("<version>")){
                                sb.append(" -Dversion=");
                                sb.append(s.replace("<version>","").replace("</version>","").trim()).append(" -Dpackaging=jar -Dfile=").append(file.getAbsolutePath().substring(0,file.getAbsolutePath().length()-4)+".jar").append(" -Durl=https://dev-cv.saicmotor.com/nexus/content/repositories/saicmaxus-release/  -DrepositoryId=releases -s /Users/carloswuu/work/tool/maven/apache-maven-3.3.9/conf/saic_deploy_settings.xml");
                                break;
                            }
                        }
                        sb.append(" -DpomFile="+file.getAbsolutePath().substring(0,file.getAbsolutePath().length()-4)+".pom");
                        System.out.println(sb.toString());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

    public static void listFile(File[] files){
        for(File file:files){
            if(file.isDirectory()){
                listFile(file.listFiles());
            }else{
                boolean pom = file.getName().endsWith(".pom");
                if(pom){
                    try {
                        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));//构造一个BufferedReader类来读取文件
                        String s = null;
                        StringBuilder sb = new StringBuilder("mvn deploy:deploy-file ");


                        while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行

                            if(s.contains("<groupId>")){
                                sb.append(" -DgroupId=");
                                sb.append(s.replace("<groupId>","").replace("</groupId>","").trim());
                            }else
                            if(s.contains("<artifactId>")){
                                sb.append(" -DartifactId=");
                                sb.append(s.replace("<artifactId>","").replace("</artifactId>","").trim());
                            }else if(s.contains("<version>")){
                                sb.append(" -Dversion=");
                                sb.append(s.replace("<version>","").replace("</version>","").trim()).append(" -Dpackaging=jar -Dfile=").append(file.getAbsolutePath().substring(0,file.getAbsolutePath().length()-4)+".jar").append(" -Durl=https://dev-cv.saicmotor.com/nexus/content/repositories/saicmaxus-release/  -DrepositoryId=releases -s /Users/carloswuu/work/tool/maven/apache-maven-3.3.9/conf/saic_deploy_settings.xml");
                                break;
                            }
                        }
                        sb.append(" -DpomFile="+file.getAbsolutePath().substring(0,file.getAbsolutePath().length()-4)+".pom");
                        System.out.println(sb.toString());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
}
