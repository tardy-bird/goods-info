package com.tardybird.goodsinfo.util;


import java.util.HashMap;
import java.util.Map;

/**
 * @author saltish
 */
public class ResponseUtil {
    public static Object ok() {
        Map<String, Object> object = new HashMap<>();
        object.put("errno", 0);
        object.put("errmsg", "成功");
        return object;
    }

    public static Object ok(Object data) {
        Map<String, Object> object = new HashMap<>();
        object.put("errno", 0);
        object.put("errmsg", "成功");
        object.put("data", data);
        return object;
    }

    public static Object fail() {
        Map<String, Object> object = new HashMap<>();
        object.put("errno", 0);
        object.put("errmsg", "错误");
        return object;
    }

    public static Object fail(int errno, String errorMessage) {
        Map<String, Object> object = new HashMap<>();
        object.put("errno", errno);
        object.put("errmsg", errorMessage);
        return object;
    }

    public static Object badArgument() {
        return fail(401, "参数不对");
    }

    public static Object badArgumentValue() {
        return fail(402, "参数值不对");
    }

    public static Object unLogin() {
        return fail(501, "请登录");
    }

    public static Object serious() {
        return fail(502, "系统内部错误");
    }

    public static Object unSupport() {
        return fail(503, "业务不支持");
    }

    public static Object updatedDateExpired() {
        return fail(504, "更新数据已经失效");
    }

    public static Object updatedDataFailed() {
        return fail(505, "更新数据失败");
    }

    public static Object unAuthorized() {
        return fail(506, "无操作权限");
    }
}



