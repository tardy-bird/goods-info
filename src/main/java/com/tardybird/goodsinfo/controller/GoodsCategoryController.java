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
@RequestMapping("/goodsInfoService")
public class GoodsCategoryController {
    /*
     * ========= following are wx apis ==============
     */

    /**
     * 获取分类数据
     */
    final
    GoodsCategoryService goodsCategoryService;
    final
    LogClient logClient;

    public GoodsCategoryController(GoodsCategoryService goodsCategoryService, LogClient logClient) {
        this.goodsCategoryService = goodsCategoryService;
        this.logClient = logClient;
    }

    /**
     * xxx 1 u1
     *
     * @return x
     */
    @GetMapping("/categories")
    public Object listGoodsCategory() {

        Log log;
        log = new Log.LogBuilder().type(0).status(0).build();
        logClient.addLog(log);

        return ResponseUtil.okList(goodsCategoryService.getAllCategories());
    }

    /**
     * 获取分类详情 3 u4
     */
    @GetMapping("/categories/{id}")
    public Object getSingleCategory(@PathVariable("id") Integer id) {
        Log log;
        if (id <= 0) {

            log = new Log.LogBuilder().type(0).status(0).actions("获取分类详情").actionId(id).build();
            logClient.addLog(log);

            return ResponseUtil.badArgumentValue();
        }

        log = new Log.LogBuilder().type(0).status(1).actions("获取分类详情").actionId(id).build();
        logClient.addLog(log);

        return ResponseUtil.ok(goodsCategoryService.getCategory(id));
    }

    /**
     * 获取1级种类 u2
     */
    @GetMapping("/categories/l1")
    public Object listOneLevelGoodsCategory() {
        List<GoodsCategory> goodsCategories = goodsCategoryService.getLevelOneCategories();
        return ResponseUtil.okList(goodsCategories);
    }


    /**
     * 获取当前一级分类下的二级分类 u3
     */
    @GetMapping("/categories/l1/{id}/l2")
    public Object listSecondLevelGoodsCategoryById(@PathVariable("id") Integer pid) {
        if (pid <= 0) {
            return ResponseUtil.badArgumentValue();
        }
        List<GoodsCategory> goodsCategories = goodsCategoryService.getLevelTwoByPid(pid);
        return ResponseUtil.okList(goodsCategories);
    }

    /*
     * ========= following are admin apis ==============
     */

    /**
     * 新建一个分类 2
     */
    @PostMapping("/categories")
    public Object addGoodsCategory(@RequestBody GoodsCategory goodsCategory) {
        Log log;
        if (goodsCategory == null) {

            log = new Log.LogBuilder().type(1).status(0).actions("新建一个分类").build();
            logClient.addLog(log);

            return ResponseUtil.badArgument();
        }

        GoodsCategoryPo goodsCategoryPo = goodsCategoryService.createCategory(goodsCategory);

        if (goodsCategoryPo == null) {

            log = new Log.LogBuilder().type(1).status(0).actions("新建一个分类").build();
            logClient.addLog(log);

            return ResponseUtil.badArgumentValue();
        }

        if (goodsCategoryPo.getId() <= 0) {

            log = new Log.LogBuilder().type(1).status(0).actions("新建一个分类").build();
            logClient.addLog(log);

            return ResponseUtil.serious();
        }

        log = new Log.LogBuilder().type(1).status(1).actions("新建一个分类").build();
        logClient.addLog(log);

        return ResponseUtil.ok(goodsCategoryPo);
    }

    /**
     * 修改分类信息 4
     */
    @PutMapping("/categories/{id}")
    public Object updateCategory(@PathVariable("id") Integer id, @RequestBody GoodsCategory goodsCategory) {
        Log log;
        if (id <= 0) {

            log = new Log.LogBuilder().type(2).status(0).actions("修改分类信息").actionId(id).build();
            logClient.addLog(log);

            return ResponseUtil.badArgumentValue();
        }

        if (goodsCategory == null) {

            log = new Log.LogBuilder().type(2).status(0).actions("修改分类信息").actionId(id).build();
            logClient.addLog(log);

            return ResponseUtil.badArgument();
        }
        goodsCategory.setId(id);
        Boolean ok = goodsCategoryService.updateCategory(goodsCategory);
        if (!ok) {

            log = new Log.LogBuilder().type(2).status(0).actions("修改分类信息").actionId(id).build();
            logClient.addLog(log);

            return ResponseUtil.updatedDataFailed();
        }

        log = new Log.LogBuilder().type(2).status(1).actions("修改分类信息").actionId(id).build();
        logClient.addLog(log);

        return ResponseUtil.ok(goodsCategory);
    }

    /**
     * 删除单个分类 5
     */
    @DeleteMapping("/categories/{id}")
    public Object deleteCategory(@PathVariable("id") Integer id) {
        Log log;
        if (id <= 0) {

            log = new Log.LogBuilder().type(3).status(0).actions("删除单个分类").actionId(id).build();
            logClient.addLog(log);

            return ResponseUtil.badArgumentValue();
        }
        Boolean ok = goodsCategoryService.deleteCategory(id);
        if (!ok) {

            log = new Log.LogBuilder().type(3).status(0).actions("删除单个分类").actionId(id).build();
            logClient.addLog(log);

            return ResponseUtil.serious();
        }

        log = new Log.LogBuilder().type(3).status(1).actions("删除单个分类").actionId(id).build();
        logClient.addLog(log);

        return ResponseUtil.ok();
    }

}
