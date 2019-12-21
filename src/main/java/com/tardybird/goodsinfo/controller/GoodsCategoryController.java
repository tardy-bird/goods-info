package com.tardybird.goodsinfo.controller;

import com.tardybird.goodsinfo.client.LogClient;
import com.tardybird.goodsinfo.domain.GoodsCategory;
import com.tardybird.goodsinfo.domain.Log;
import com.tardybird.goodsinfo.po.GoodsCategoryPo;
import com.tardybird.goodsinfo.service.GoodsCategoryService;
import com.tardybird.goodsinfo.util.ResponseUtil;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author nick
 */
@RestController
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
    public Object listGoodsCategory(@RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "10") Integer limit) {

        Log log;
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
     * 获取分类详情 3 u4
     */
    @GetMapping("/categories/{id}")
    public Object getSingleCategory(@PathVariable("id") Integer id) {
        Log log;
        if (id <= 0) {

            log = new Log.LogBuilder().type(0).status(0).actions("获取分类详情").actionId(id).build();
            logClient.addLog(log);

            return ResponseUtil.badArgument();
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
        Log log;
        log = new Log.LogBuilder().type(0).status(1).actions("获取一级分类").build();
        logClient.addLog(log);

        List<GoodsCategory> goodsCategories = goodsCategoryService.getLevelOneCategories();
        return ResponseUtil.ok(goodsCategories);
    }


    /**
     * 获取当前一级分类下的二级分类 u3
     */
    @GetMapping("/categories/l1/{id}/l2")
    public Object listSecondLevelGoodsCategoryById(@PathVariable("id") Integer pid) {
        Log log;


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

    /*
     * ========= following are admin apis ==============
     */

    /**
     * 新建一个分类 2
     */
    @PostMapping("/categories")
    public Object addGoodsCategory(@RequestBody GoodsCategoryPo goodsCategory) {
        Log log;
        if (goodsCategory.getName() != null
                || goodsCategory.getPicUrl() != null
                || goodsCategory.getPid() != null) {

            goodsCategoryService.createCategory(goodsCategory);

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
     * 修改分类信息 4
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

        if (!ok) {

            log = new Log.LogBuilder().type(2).status(0).actions("修改分类信息").actionId(id).build();
            logClient.addLog(log);

            return ResponseUtil.failUpdateCategory();
        }

        log = new Log.LogBuilder().type(2).status(1).actions("修改分类信息").actionId(id).build();
        logClient.addLog(log);

        return ResponseUtil.ok(goodsCategory);
    }

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
        boolean ok = goodsCategoryService.updateParentCategory(goodsCategoryPo);

        if (!ok) {

            log = new Log.LogBuilder().type(2).status(0).actions("修改分类信息").actionId(id).build();
            logClient.addLog(log);

            return ResponseUtil.failUpdateCategory();
        }

        log = new Log.LogBuilder().type(2).status(1).actions("修改分类信息").actionId(id).build();
        logClient.addLog(log);

        goodsCategoryPo = goodsCategoryService.getCategory(goodsCategoryPo.getId());

        return ResponseUtil.ok(goodsCategoryPo);
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

            return ResponseUtil.badArgument();
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
