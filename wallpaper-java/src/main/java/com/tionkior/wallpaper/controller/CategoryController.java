package com.tionkior.wallpaper.controller;

import com.tionkior.wallpaper.pojo.Category;
import com.tionkior.wallpaper.pojo.vo.Result;
import com.tionkior.wallpaper.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : TionKior
 * @date : 2022/2/25 9:04
 */
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

}