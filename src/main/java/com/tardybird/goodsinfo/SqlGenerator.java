package com.tardybird.goodsinfo;

import com.tardybird.goodsinfo.util.IdUtil;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author nick
 */
public class SqlGenerator {

    public static String values(String[] columns) {
        StringBuilder result = new StringBuilder();
        int n = columns.length;
        for (int i = 0; i < n - 1; i++) {
            result.append(columns[i]).append(",");
        }
        result.append(columns[n - 1]);
        return result.toString();
    }

    public static String brands(int length) {

        String[] columns = {
                "`name`",
                "`describe`",
                "`pic_url`",
                "`gmt_create`",
                "`gmt_modified`",
                "`is_deleted`"
        };


        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= length; i++) {
            String sql = "insert into brand " +
                    "(" + values(columns) + ")" + " values('" +
                    IdUtil.getValue(8) + "','" +
                    IdUtil.getValue(8) + "','" + IdUtil.getValue(16)
                    + "','" + Timestamp.valueOf(LocalDateTime.now()) + "','" +
                    Timestamp.valueOf(LocalDateTime.now()) + "',false);";
            builder.append(sql);
        }
        return builder.toString();
    }

    public static String goods(int length) {

        String[] columns = {
                "`gmt_create`",
                "`gmt_modified`",
                "`name`",
                "`good_sn`",
                "`short_name`",
                "`description`",
                "`brief`",
                "`pic_url`",
                "`detail`",
                "`status`",
                "`share_url`",
                "`gallery`",
                "`goods_category_ids`",
                "`brand_id`",
                "`is_deleted`",
                "`weight`",
                "`volume`",
                "`special_freight_id`",
                "`is_special`"
        };

        StringBuilder builder = new StringBuilder();

        SecureRandom random = new SecureRandom();
        for (int i = 1; i <= length; i++) {
            String sql = "insert into goods " +
                    "(" + values(columns) + ")" + " values('" +
                    Timestamp.valueOf(LocalDateTime.now()) + "','" +
                    Timestamp.valueOf(LocalDateTime.now()) + "','" +
                    IdUtil.getValue(16) + "','" +
                    IdUtil.getValue(32) + "','" +
                    IdUtil.getValue(16) + "','" +
                    IdUtil.getValue(16) + "','" +
                    IdUtil.getValue(16) + "','" +
                    IdUtil.getValue(16) + "','" +
                    IdUtil.getValue(16) + "',0,'" +
                    IdUtil.getValue(32) + "','" +
                    IdUtil.getValue(32) + "','" +
                    IdUtil.getValue(16) + "'," +
                    random.nextInt(10) + ",0," +
                    random.nextInt(1000) + ",'" +
                    IdUtil.getValue(16) + "'," +
                    random.nextInt(1000) + ",0);\n";
            builder.append(sql);
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String goods = goods(10);
        System.out.println(goods);
        String brands = brands(10);
        System.out.println(brands);
    }


}

