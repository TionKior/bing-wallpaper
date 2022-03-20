package com.tionkior.wallpaper.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tionkior.wallpaper.entity.WallPaper;
import com.tionkior.wallpaper.entity.vo.Result;
import com.tionkior.wallpaper.service.WallPaperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author : TionKior
 * @date : 2022/2/24 12:08
 */
@Api(tags = "壁纸Controller")
@RestController
public class WallPaperController {

    @Autowired
    private WallPaperService wallPaperService;

    /**
     * 获取壁纸页表
     *
     * @param currentPage 当前页
     * @param categoryId  类型
     * @return 返回壁纸列表
     */
    @ApiOperation("获取壁纸页")
    @GetMapping("/wallPaperList")
    public Result<Page<WallPaper>> getWallPaperList(
            @RequestParam(value = "currentPage", required = false, defaultValue = "1")
            @ApiParam("当前页") String currentPage,
            @RequestParam(value = "categoryId", required = false, defaultValue = "1")
            @ApiParam("分类id,用于分类查询") String categoryId) {
        return wallPaperService.getWallPaperPage(currentPage, categoryId);
    }

    /**
     * 获取壁纸详细信息
     *
     * @param imageId 壁纸id
     * @return 返回详细信息
     */
    @ApiOperation("获取壁纸详情")
    @GetMapping("/wallPaperDetail")
    public Result<WallPaper> getWallPaperDetail(
            @RequestParam(value = "imageId", defaultValue = "1") @ApiParam("壁纸id") String imageId) {
        return Result.success(wallPaperService.getById(imageId));
    }

    /**
     * 获取随机壁纸
     *
     * @return 返回随机壁纸
     */
    @ApiOperation("获取随机壁纸")
    @GetMapping("/wallPaperRandom")
    public Result<WallPaper> getWallPaperRandom() {
        return wallPaperService.getWallPaperRandom();
    }

}
