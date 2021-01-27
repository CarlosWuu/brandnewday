package com.carloswuu.exampleweb.utils;

import java.io.*;

/**
 * @author: wjb
 * @Date: 2020/4/27 下午1:57
 */
public class ActivePointsUtil {
    static String registerDetailSql = "insert into active_point_send_detail_2020_4 (user_id,assignment_name,assignment_scene_id,assignment_scene_name,active_type_id,active_type_name,send_date,expire_date, order_sum,payment_sum,send_type,send_active_point_num,status,create_time) value\n" +
            "(%d,'注册',9,'注册',3,'用户互动','2020-04-27 15:00:00','2020-04-27 15:00:00',0.00,0.00,1,50,1,'2020-04-27 15:00:00');";

    static String registerRecordSql = "insert into user_assignment_record (user_id,assignment_scene_id,assignment_tag,completion_times,day_tag,create_time)\n" +
            "values (%d,9,2,1,'2020-04-27','2020-04-27 15:00:00');";

    static String registerMember = "insert into user_member_info (user_id,active_points,to_be_overdue_active_points,active_points_in_half_year,star_level,create_time)\n" +
            "values(%d,50,0,0,0,'2020-04-27 15:00:00')ON DUPLICATE KEY UPDATE active_points = active_points + 50;";


    static String userSql = "update user_base_info set activity_value=activity_value+50 where user_id=%d;";


    static String wxDetailSql = "insert into active_point_send_detail_2020_4 (user_id,assignment_name,assignment_scene_id,assignment_scene_name,active_type_id,active_type_name,send_date,expire_date, order_sum,payment_sum,send_type,send_active_point_num,status,create_time) value\n" +
            "(%d,'绑定微信',13,'绑定微信',3,'用户互动','2020-04-27 16:00:00','2021-04-27 15:00:00',0.00,0.00,1,10,1,'2020-04-27 16:00:00');";
    static String wxRecordSql = "insert into user_assignment_record (user_id,assignment_scene_id,assignment_tag,completion_times,day_tag,create_time)\n" +
            "values (%d,13,2,1,'2020-04-27','2020-04-27 16:00:00');";
    static String wxMember = "insert into user_member_info (user_id,active_points,to_be_overdue_active_points,active_points_in_half_year,star_level,create_time)\n" +
            "values(%d,10,0,0,0,'2020-04-27 15:00:00')ON DUPLICATE KEY UPDATE active_points = active_points + 10;";

    static String wxuserSql = "update user_base_info set activity_value=activity_value+10 where user_id=%d;";


    static String wbDetailSql = "insert into active_point_send_detail_2020_4 (user_id,assignment_name,assignment_scene_id,assignment_scene_name,active_type_id,active_type_name,send_date,expire_date, order_sum,payment_sum,send_type,send_active_point_num,status,create_time) value\n" +
            "(%d,'绑定微博',14,'绑定微博',3,'用户互动','2020-04-27 16:00:00','2021-04-27 15:00:00',0.00,0.00,1,10,1,'2020-04-27 16:00:00');";
    static String wbRecordSql = "insert into user_assignment_record (user_id,assignment_scene_id,assignment_tag,completion_times,day_tag,create_time)\n" +
            "values (%d,14,2,1,'2020-04-27','2020-04-27 16:00:00');";
    static String wbMember = "insert into user_member_info (user_id,active_points,to_be_overdue_active_points,active_points_in_half_year,star_level,create_time)\n" +
            "values(%d,10,0,0,0,'2020-04-27 15:00:00')ON DUPLICATE KEY UPDATE active_points = active_points + 10;";

    static String wbuserSql = "update user_base_info set activity_value=activity_value+10 where user_id=%d;";


    static String updatesql = "update active_point_send_detail_2020_4 set expire_date='2021-04-27 15:00:00' where user_id=%d and assignment_scene_id=13; ";


    static String mailDetailSql = "insert into active_point_send_detail_2020_4 (user_id,assignment_name,assignment_scene_id,assignment_scene_name,active_type_id,active_type_name,send_date,expire_date, order_sum,payment_sum,send_type,send_active_point_num,status,create_time) value\n" +
            "(%d,'绑定邮箱',15,'绑定邮箱',3,'用户互动','2020-04-27 16:00:00','2021-04-27 15:00:00',0.00,0.00,1,10,1,'2020-04-27 16:00:00');";
    static String mailRecordSql = "insert into user_assignment_record (user_id,assignment_scene_id,assignment_tag,completion_times,day_tag,create_time)\n" +
            "values (%d,15,2,1,'2020-04-27','2020-04-27 16:00:00');";
    static String mailMember = "insert into user_member_info (user_id,active_points,to_be_overdue_active_points,active_points_in_half_year,star_level,create_time)\n" +
            "values(%d,10,0,0,0,'2020-04-27 15:00:00')ON DUPLICATE KEY UPDATE active_points = active_points + 10;";

    static String mailuserSql = "update user_base_info set activity_value=activity_value+10 where user_id=%d;";

    static String deldetailSql = "delete from active_point_send_detail_2020_4 where user_id=%d and assignment_scene_id=14;";
    static String delrecordSql = "delete from user_assignment_record where day_tag='2020-04-27' and user_id=%d and assignment_scene_id=14;";
    static String minusMembersql = "update user_member_info set active_points = active_points -10 where user_id=%d;";
    static String minusUsersql = "update user_base_info set activity_value = activity_value -10 where user_id=%d;";

    public static void main(String[] args) {
        String registerDetailSqlPath = "/Users/carloswuu/Desktop/activepoints/wxDetailSql.sql";
        String registerRecordSqlPath = "/Users/carloswuu/Desktop/activepoints/wxRecordSql.sql";
        String registerMemberPath = "/Users/carloswuu/Desktop/activepoints/wxMember.sql";
        String userSqlPath = "/Users/carloswuu/Desktop/activepoints/wxuserSql.sql";





        try {
            BufferedWriter detailWriter = new BufferedWriter(new FileWriter(new File(registerDetailSqlPath)));
            BufferedWriter recordWriter = new BufferedWriter(new FileWriter(new File(registerRecordSqlPath)));
            BufferedWriter memberWriter = new BufferedWriter(new FileWriter(new File(registerMemberPath)));
            BufferedWriter userWriter = new BufferedWriter(new FileWriter(new File(userSqlPath)));



            BufferedReader reader = new BufferedReader(new FileReader(new File("/Users/carloswuu/Desktop/activepoints/wxnew/wxuserid.csv")));
            String s;
            while((s = reader.readLine()) != null){

                int userId = Integer.parseInt(s.replace("\"", ""));
                if(userId == 0){
                    continue;
                }
                String detail = String.format(wxDetailSql, userId);
                String record = String.format(wxRecordSql,userId);
                String member = String.format(wxMember,userId);
                String user = String.format(wxuserSql,userId);


                detailWriter.write(detail+"\n");
                recordWriter.write(record+"\n");
                memberWriter.write(member+"\n");
                userWriter.write(user+"\n");
            }

            detailWriter.flush();
            recordWriter.flush();
            memberWriter.flush();
            userWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
