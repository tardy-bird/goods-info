package com.tardybird.goodsinfo.util;

import com.github.pagehelper.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 响应操作结果
 * <pre>
 *  {
 *      errno： 错误码，
 *      errmsg：错误消息，
 *      data：  响应数据
 *  }
 * </pre>
 *
 * <p>
 * 错误码：
 * <ul>
 * <li> 0，成功；
 * <li> 4xx，前端错误，说明前端开发者需要重新了解后端接口使用规范：
 * <ul>
 * <li> 401，参数错误，即前端没有传递后端需要的参数；
 * <li> 402，参数值错误，即前端传递的参数值不符合后端接收范围。
 * </ul>
 * <li> 5xx，后端错误，除501外，说明后端开发者应该继续优化代码，尽量避免返回后端错误码：
 * <ul>
 * <li> 501，验证失败，即后端要求用户登录；
 * <li> 502，系统内部错误，即没有合适命名的后端内部错误；
 * <li> 503，业务不支持，即后端虽然定义了接口，但是还没有实现功能；
 * <li> 504，更新数据失效，即后端采用了乐观锁更新，而并发更新时存在数据更新失效；
 * <li> 505，更新数据失败，即后端数据库更新失败（正常情况应该更新成功）。
 * </ul>
 * <li> 6xx，小商城后端业务错误码，
 * <li> 7xx，管理后台后端业务错误码
 * </ul>
 *
 * @author nick
 */
public class ResponseUtil {
    public static Object ok() {
        Map<String, Object> objectMap = new HashMap<>(16);
        objectMap.put("errno", 0);
        objectMap.put("errmsg", "成功");
        return objectMap;
    }

    public static Object ok(Object data) {
        Map<String, Object> objectMap = new HashMap<>(16);
        objectMap.put("errno", 0);
        objectMap.put("errmsg", "成功");
        objectMap.put("data", data);
        return objectMap;
    }

    @SuppressWarnings("rawtypes")
    public static Object okList(List list) {
        return getObject(list, list);
    }

    @SuppressWarnings("rawtypes")
    public static Object okList(List list, List pagedList) {
        return getObject(list, pagedList);
    }

    @SuppressWarnings("rawtypes")
    private static Object getObject(List list, List pagedList) {
        Map<String, Object> data = new HashMap<>(16);
        data.put("list", list);

        if (pagedList instanceof Page) {
            Page page = (Page) pagedList;
            data.put("total", page.getTotal());
            data.put("page", page.getPageNum());
            data.put("limit", page.getPageSize());
            data.put("pages", page.getPages());
        } else {
            data.put("total", pagedList.size());
            data.put("page", 1);
            data.put("limit", pagedList.size());
            data.put("pages", 1);
        }

        return ok(data);
    }

    public static Object fail() {
        Map<String, Object> objectMap = new HashMap<>(16);
        objectMap.put("errno", -1);
        objectMap.put("errmsg", "错误");
        return objectMap;
    }

    public static Object fail(int errno, String errmsg) {
        Map<String, Object> objectMap = new HashMap<>(16);
        objectMap.put("errno", errno);
        objectMap.put("errmsg", errmsg);
        return objectMap;
    }

    /*
    401 Unauthorized
    状态码 401 Unauthorized 代表客户端错误，指的是由于缺乏目标资源要求的身份验证凭证，发送的请求未得到满足。
    这个状态码会与WWW-Authenticate 首部一起发送，其中包含有如何进行验证的信息。
    这个状态类似于 403， 但是在该情况下，依然可以进行身份验证
     */
    public static Object badArgument() {
        return fail(401, "参数不对");
    }

    /*
    402 Payment Required
    402 Payment Required 是一个被保留使用的非标准客户端错误状态响应码。
    有时， 这个状态码表明直到客户端付费之后请求才会被处理。
    402状态码被创建最初目的是用于数字现金或微型支付系统，
    表明客户端请求的内容只有付费之后才能获取。目前还不存在标准的使用约定，
    不同的实体可以在不同的环境下使用。
     */
    public static Object badArgumentValue() {
        return fail(402, "参数值不对");
    }

    /*
    501 Not Implemented
    HTTP 501 Not Implemented 服务器错误响应码表示请求的方法不被服务器支持，
    因此无法被处理。服务器必须支持的方法（即不会返回这个状态码的方法）只有 GET 和 HEAD。
    请注意，你无法修复 501 错误，需要被访问的 web 服务器去修复该问题。
    501 响应默认是可缓存的。
     */
    public static Object unLogin() {
        return fail(501, "请登录");
    }

    /*
    502 Bad Gateway
    502 Bad Gateway 是一种HTTP协议的服务器端错误状态代码，它表示作为网关或代理角色的服务器，
    从上游服务器（如tomcat、php-fpm）中接收到的响应是无效的。
    Gateway （网关）在计算机网络体系中可以指代不同的设备，502 错误通常不是客户端能够修复的，
    而是需要由途径的Web服务器或者代理服务器对其进行修复。
     */
    public static Object serious() {
        return fail(502, "系统内部错误");
    }

    /*
    503 Service Unavailable
    503 Service Unavailable 是一种HTTP协议的服务器端错误状态代码，
    它表示服务器尚未处于可以接受请求的状态。
    通常造成这种情况的原因是由于服务器停机维护或者已超载。
    注意在发送该响应的时候，应该同时发送一个对用户友好的页面来解释问题发生的原因。
    该种响应应该用于临时状况下，与之同时，在可行的情况下，应该在 Retry-After 首部字段中包含服务恢复的预期时间。
    缓存相关的首部在与该响应一同发送时应该小心使用，因为 503 状态码通常应用于临时状况下，而此类响应一般不应该进行缓存。
     */
    public static Object unSupport() {
        return fail(503, "业务不支持");
    }

    /*
    504 Gateway Timeout
    504 Gateway Timeout 是一种HTTP协议的服务器端错误状态代码，
    表示扮演网关或者代理的服务器无法在规定的时间内获得想要的响应。
    Gateway （网关）在计算机网络体系中可以指代不同的设备，504 错误通常不是在客户端可以修复的，
    而是需要由途径的Web服务器或者代理服务器对其进行修复。
     */
    public static Object updatedDateExpired() {
        return fail(504, "更新数据已经失效");
    }

    /*
    505 HTTP Version Not Supported
    505 HTTP Version Not Supported 是一种HTTP协议的服务器端错误状态代码，
    表示服务器不支持请求所使用的 HTTP 版本
     */
    public static Object updatedDataFailed() {
        return fail(505, "更新数据失败");
    }

    /*
    506 Variant Also Negotiates
    代表服务器存在内部配置错误：被请求的协商变元资源被配置为在透明内容协商中使用自己，
    因此在一个协商处理中不是一个合适的重点。
     */
    public static Object unAuthorized() {
        return fail(506, "无操作权限");
    }
}

