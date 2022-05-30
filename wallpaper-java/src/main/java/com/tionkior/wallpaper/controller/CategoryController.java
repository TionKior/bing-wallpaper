package com.tionkior.wallpaper.controller;

import com.tionkior.wallpaper.entity.Category;
import com.tionkior.wallpaper.entity.vo.Result;
import com.tionkior.wallpaper.entity.vo.ResultCodeEnum;
import com.tionkior.wallpaper.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : TionKior
 * @date : 2022/2/25 9:04
 */

@Slf4j
@Api(tags = "壁纸分类Controller")
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 获取分类
     *
     * @return 返回分类
     */
    @ApiOperation("获取壁纸分类列表")
    @GetMapping("/classify")
    public Result<List<Category>> getClassify() {
        List<Category> categoryList = categoryService.list();
        return Result.success(categoryList);
    }

    @PostMapping("/category/deleteById")
    public Result deleteById(@RequestBody String categoryId) {
        if (StringUtils.isEmpty(categoryId)) {
            return Result.failure(ResultCodeEnum.NULL);
        }
        log.error(categoryId);
        boolean result = categoryService.removeById(categoryId);
        return result ? Result.success() : Result.failure();
    }

    @PostMapping("/category/add")
    public Result addCategory(@RequestBody Category category) {
        if (ObjectUtils.isEmpty(category)) {
            return Result.failure(ResultCodeEnum.NULL);
        }
        boolean result = categoryService.save(category);
        return result ? Result.success() : Result.failure();
    }

}
