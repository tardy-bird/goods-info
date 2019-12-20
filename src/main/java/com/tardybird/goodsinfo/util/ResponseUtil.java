package com.tardybird.goodsinfo.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DIX
 */
public class ResponseUtil {

    public static Object ok(Object data) {
        Map<String, Object> objectMap = new HashMap<>(16);
        objectMap.put("errno", 0);
        objectMap.put("errmsg", "成功");
        objectMap.put("data", data);
        return objectMap;
    }

    public static Object okNoData(Object data) {
        Map<String, Object> objectMap = new HashMap<>(16);
        objectMap.put("errno", 200);
        objectMap.put("errmsg", "成功");
        return objectMap;
    }


    public static Object fail(int errno, String errmsg) {
        Map<String, Object> objectMap = new HashMap<>(16);
        objectMap.put("errno", errno);
        objectMap.put("errmsg", errmsg);
        return objectMap;
    }

    /**
     * goods
     */
    public static Object failAdd() {
        return fail(771, "商品新增失败");
    }

    public static Object failUpdate() {
        return fail(772, "商品修改失败");
    }

    public static Object failDelete() {
        return fail(773, "商品删除失败");
    }

    public static Object failDown() {
        return fail(774, "商品下架失败");
    }

    public static Object cantFind() {
        return fail(775, "该商品不存在");
    }

    public static Object cantFindList() {
        return fail(776, "获取商品列表失败");
    }


    /**
     * product
     */
    public static Object failAddProduct() {
        return fail(781, "产品新增失败");
    }

    public static Object failUpdateProduct() {
        return fail(782, "产品修改失败");
    }

    public static Object failDeleteProduct() {
        return fail(783, "产品删除失败");
    }

    public static Object cantFindProduct() {
        return fail(784, "该产品不存在");
    }

    public static Object cantFindListProduct() {
        return fail(785, "获取产品列表失败");
    }

    public static Object cantUpdateStock() {
        return fail(786, "修改产品库存失败");
    }

    /**
     * brand
     */
    public static Object failAddBrand() {
        return fail(791, "品牌新增失败");
    }

    public static Object failUpdateBrand() {
        return fail(792, "品牌修改失败");
    }

    public static Object failDeleteBrand() {
        return fail(793, "品牌删除失败");
    }

    public static Object cantFindBrand() {
        return fail(794, "该品牌不存在");
    }

    public static Object cantFindListBrand() {
        return fail(795, "获取品牌列表失败");
    }


    /**
     * categories
     */
    public static Object failAddCategory() {
        return fail(801, "分类新增失败");
    }

    public static Object failUpdateCategory() {
        return fail(802, "分类修改失败");
    }

    public static Object failDeleteCategory() {
        return fail(803, "分类删除失败");
    }

    public static Object cantFindCategory() {
        return fail(804, "该分类不存在");
    }

    public static Object cantFindListCategory() {
        return fail(805, "获取分类列表失败");
    }
}

