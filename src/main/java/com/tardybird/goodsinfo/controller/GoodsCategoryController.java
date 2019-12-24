package com.tardybird.goodsinfo.controller;

import com.tardybird.goodsinfo.client.LogClient;
import com.tardybird.goodsinfo.domain.GoodsCategory;
import com.tardybird.goodsinfo.domain.Log;
import com.tardybird.goodsinfo.po.GoodsCategoryPo;
import com.tardybird.goodsinfo.service.GoodsCategoryService;
import com.tardybird.goodsinfo.util.ResponseUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author nick
 */
@RestController
public class GoodsCategoryController {

    final GoodsCategoryService goodsCategoryService;
    final LogClient logClient;

    private Log log;

    public GoodsCategoryController(GoodsCategoryService goodsCategoryService, LogClient logClient) {
        this.goodsCategoryService = goodsCategoryService;
        this.logClient = logClient;
    }
    /**
     * 获取分类数据
     *
     * @param page  页数
     * @param limit 每页大小
     * @return 分类列表
     */
    @GetMapping("/categories")
    public Object listGoodsCategory(@RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "10") Integer limit) {

        if (page <= 0 || limit <= 0) {

            log = new Log.LogBuilder().type(0).actions("查看分类").status(0).build();
            logClient.addLog(log);

            return ResponseUtil.badArgument();
        }

        log = new Log.LogBuilder().type(0).actions("查看分类").status(1).build();
        logClient.addLog(log);

        Object object = goodsCategoryService.getAllCategories(page, limit);

        return ResponseUtil.ok(object);
    }

    /**
     * 获取分类详情
     *
     * @param id 分类ID
     * @return 分类详情
     */
    @GetMapping("/categories/{id}")
    public Object getSingleCategory(@PathVariable("id") Integer id) {
        if (id <= 0) {

            log = new Log.LogBuilder().type(0).status(0).actions("获取分类详情").actionId(id).build();
            logClient.addLog(log);

            return ResponseUtil.badArgument();
        }

        log = new Log.LogBuilder().type(0).status(1).actions("获取分类详情").actionId(id).build();
        logClient.addLog(log);

        GoodsCategoryPo goodsCategoryPo = goodsCategoryService.getCategory(id);

        if (goodsCategoryPo == null) {
            return ResponseUtil.cantFindCategory();
        }
        return ResponseUtil.ok(goodsCategoryPo);
    }

    /**
     * 获取1级种类
     *
     * @return 分类信息
     */
    @GetMapping("/categories/l1")
    public Object listOneLevelGoodsCategory() {
        Log log;
        log = new Log.LogBuilder().type(0).status(1).actions("获取一级分类").build();
        logClient.addLog(log);

        List<GoodsCategory> goodsCategories = goodsCategoryService.getLevelOneCategories();
        return ResponseUtil.ok(goodsCategories);
    }


    /**
     * 获取当前一级分类下的二级分类
     *
     * @param pid 一级分类ID
     * @return 分类信息
     */
    @GetMapping("/categories/l1/{id}/l2")
    public Object listSecondLevelGoodsCategoryById(@PathVariable("id") Integer pid) {

        if (pid <= 0) {

            log = new Log.LogBuilder().type(0).status(0).actions("获取分类详情").actionId(pid).build();
            logClient.addLog(log);

            return ResponseUtil.badArgument();
        }

        log = new Log.LogBuilder().type(0).status(1).actions("获取分类详情").actionId(pid).build();
        logClient.addLog(log);

        List<GoodsCategoryPo> goodsCategories = goodsCategoryService.getLevelTwoByPid(pid);
        return ResponseUtil.ok(goodsCategories);
    }

    /**
     * 新建一个分类
     *
     * @param goodsCategory 新的分类信息
     * @return 分类信息
     */
    @PostMapping("/categories")
    public Object addGoodsCategory(@RequestBody GoodsCategoryPo goodsCategory) {

        // 分类的这三个字段不能同时为空
        if (goodsCategory.getName() != null
                || goodsCategory.getPicUrl() != null
                || goodsCategory.getPid() != null) {

            GoodsCategoryPo goodsCategoryPoTmp = goodsCategoryService.createCategory(goodsCategory);
            if (goodsCategoryPoTmp == null) {
                return ResponseUtil.failAddCategory();
            }

            log = new Log.LogBuilder().type(1).status(1).actions("新建一个分类").build();
            logClient.addLog(log);

            Integer id = goodsCategory.getId();
            GoodsCategoryPo goodsCategoryPo = goodsCategoryService.getCategory(id);

            return ResponseUtil.ok(goodsCategoryPo);
        }

        log = new Log.LogBuilder().type(1).status(0).actions("新建一个分类").build();
        logClient.addLog(log);

        return ResponseUtil.failAddCategory();
    }

    /**
     * 修改分类信息
     *
     * @param id            分类ID
     * @param goodsCategory 新的分类信息
     * @return 分类信息
     */
    @PutMapping("/categories/{id}")
    public Object updateCategory(@PathVariable("id") Integer id,
                                 @RequestBody GoodsCategoryPo goodsCategory) {
        Log log;
        if (id <= 0) {

            log = new Log.LogBuilder().type(2).status(0).actions("修改分类信息").actionId(id).build();
            logClient.addLog(log);

            return ResponseUtil.badArgument();
        }

        if (goodsCategory.getPicUrl() == null && goodsCategory.getName() == null) {

            log = new Log.LogBuilder().type(2).status(0).actions("修改分类信息").actionId(id).build();
            logClient.addLog(log);

            return ResponseUtil.failUpdateCategory();
        }

        goodsCategory.setId(id);
        Boolean ok = goodsCategoryService.updateCategory(goodsCategory);

        // 检查更新是否成功
        if (!ok) {

            log = new Log.LogBuilder().type(2).status(0).actions("修改分类信息").actionId(id).build();
            logClient.addLog(log);

            return ResponseUtil.failUpdateCategory();
        }

        log = new Log.LogBuilder().type(2).status(1).actions("修改分类信息").actionId(id).build();
        logClient.addLog(log);

        return ResponseUtil.ok(goodsCategory);
    }

    /**
     * 修改二级分类信息
     *
     * @param id              分类ID
     * @param goodsCategoryPo 新的分类信息
     * @return 分类信息
     */
    @PutMapping("/categories/l2/{id}")
    public Object updateParentCategory(@PathVariable("id") Integer id, @RequestBody GoodsCategoryPo goodsCategoryPo) {
        Log log;
        if (id <= 0) {

            log = new Log.LogBuilder().type(2).status(0).actions("修改分类信息").actionId(id).build();
            logClient.addLog(log);

            return ResponseUtil.badArgument();
        }

        if (goodsCategoryPo.getPid() == 0) {

            log = new Log.LogBuilder().type(2).status(0).actions("修改分类信息").actionId(id).build();
            logClient.addLog(log);

            return ResponseUtil.failUpdateCategory();
        }

        goodsCategoryPo.setId(id);

        // 更新分类信息
        boolean ok = goodsCategoryService.updateParentCategory(goodsCategoryPo);

        if (!ok) {

            log = new Log.LogBuilder().type(2).status(0).actions("修改分类信息").actionId(id).build();
            logClient.addLog(log);

            return ResponseUtil.failUpdateCategory();
        }

        log = new Log.LogBuilder().type(2).status(1).actions("修改分类信息").actionId(id).build();
        logClient.addLog(log);

        // 用给定的ID从数据库中查找分类
        goodsCategoryPo = goodsCategoryService.getCategory(goodsCategoryPo.getId());

        return ResponseUtil.ok(goodsCategoryPo);
    }

    /**
     * 删除单个分类
     *
     * @param id 分类
     * @return Response
     */
    @DeleteMapping("/categories/{id}")
    public Object deleteCategory(@PathVariable("id") Integer id) {
        Log log;
        if (id <= 0) {

            log = new Log.LogBuilder().type(3).status(0).actions("删除单个分类").actionId(id).build();
            logClient.addLog(log);

            return ResponseUtil.badArgument();
        }

        GoodsCategoryPo goodsCategoryPo = goodsCategoryService.getCategory(id);

        if (goodsCategoryPo == null) {
            return ResponseUtil.cantFindCategory();
        }

        Boolean ok = goodsCategoryService.deleteCategory(id);

        if (!ok) {

            log = new Log.LogBuilder().type(3).status(0).actions("删除单个分类").actionId(id).build();
            logClient.addLog(log);

            return ResponseUtil.failDeleteCategory();
        }

        log = new Log.LogBuilder().type(3).status(1).actions("删除单个分类").actionId(id).build();
        logClient.addLog(log);

        return ResponseUtil.okNoData();
    }

}
