package com.tionkior.wallpaper.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tionkior.wallpaper.entity.WallPaper;
import com.tionkior.wallpaper.entity.vo.Result;
import com.tionkior.wallpaper.entity.vo.ResultCodeEnum;
import com.tionkior.wallpaper.service.WallPaperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * @author : TionKior
 * @date : 2022/2/24 12:08
 */
@Slf4j
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
            @RequestParam("imageId") @ApiParam("壁纸id") String imageId) {
        return Result.success(wallPaperService.getById(imageId));
    }

    /**
     * 获取壁纸详细信息
     *
     * @param imageId 壁纸id
     * @return 返回详细信息
     */
    @ApiOperation("获取壁纸详情Post方式")
    @PostMapping("/wallPaperDetailPost")
    public Result<WallPaper> getWallPaperDetailPost(@RequestBody @ApiParam("壁纸id") String imageId) {
        if (StringUtils.isBlank(imageId)) {
            return Result.failure(ResultCodeEnum.NULL);
        }
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

    /**
     * 搜索壁纸所有壁纸加中文分类
     *
     * @return 返回壁纸加分类
     */
    @ApiOperation("壁纸列表和分类")
    @GetMapping("/wallPaperListAndCategory")
    public Result getWallPaperListAndCategory() {
        return wallPaperService.getWallPaperList();
    }

    @ApiOperation("通过壁纸ID删除壁纸")
    @GetMapping("/deleteWallpaperById")
    public Result deleteWallpaperById(@ApiParam("被删除的壁纸ID") String wallpaperId) {
        return wallPaperService.deleteWallpaperById(wallpaperId);
    }

    @ApiOperation("添加壁纸")
    @PostMapping(value = "/addWallPaper")
    public Result addWallPaper(
            @ApiParam("壁纸的文字信息") WallPaper wallPaper,
            String userId,
            @ApiParam("壁纸图片文件") @RequestParam(value = "file", required = false) MultipartFile wallpaperFile) throws IOException {
        return wallPaperService.addWallPaper(wallPaper, wallpaperFile, userId);
    }

    @ApiOperation("收藏壁纸")
    @GetMapping("/favoriteImage")
    public Result favoriteImage(
            @ApiParam("用户ID") String userId,
            @ApiParam("被收藏的壁纸ID") String imageId) {
        return wallPaperService.favoriteImage(userId, imageId);
    }

    @ApiOperation("壁纸是否被收藏")
    @GetMapping("/isFavoriteImage")
    public Result isFavoriteImage(
            @ApiParam("用户ID") String userId,
            @ApiParam("被收藏的壁纸ID") String imageId) {
        return wallPaperService.isFavoriteImage(userId, imageId);
    }

    @ApiOperation("取消收藏壁纸")
    @GetMapping("/notFavoriteImage")
    public Result notFavoriteImage(
            @ApiParam("用户ID") String userId,
            @ApiParam("取消的壁纸ID") String imageId) {
        return wallPaperService.notFavoriteImage(userId, imageId);
    }

    @ApiOperation("用户收藏的壁纸列表")
    @GetMapping("/userFavoriteWallPaperListAndCategory")
    public Result userFavoriteWallPaperListAndCategory(String userId) {
        if (StringUtils.isEmpty(userId)) {
            return Result.failure(ResultCodeEnum.NULL);
        }
        return wallPaperService.userFavoriteWallPaperListAndCategory(userId);
    }

    @PostMapping("/changeWallPaper")
    public Result changeWallPaper(@RequestBody WallPaper wallPaper) {
        return wallPaperService.changeWallPaper(wallPaper);
    }


}
