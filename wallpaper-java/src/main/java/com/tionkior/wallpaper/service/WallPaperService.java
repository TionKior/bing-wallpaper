package com.tionkior.wallpaper.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tionkior.wallpaper.entity.WallPaper;
import com.tionkior.wallpaper.entity.vo.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author : TionKior
 * @date : 2022/2/24 12:00
 */

public interface WallPaperService extends IService<WallPaper> {
    /**
     * 分页查询所有壁纸
     *
     * @param currentPage 当前页
     * @param categoryId  查询条数
     * @return 返回结果
     */
    Result<Page<WallPaper>> getWallPaperPage(String currentPage, String categoryId);

    /**
     * 获取随机壁纸
     *
     * @return 返回随机壁纸
     */
    Result<WallPaper> getWallPaperRandom();

    /**
     * 获取所有壁纸,分类是中文名
     *
     * @return 返回所有壁纸
     */
    Result getWallPaperList();

    /**
     * 根据ID删除壁纸
     *
     * @param wallpaperId 壁纸ID
     * @return 返回结果
     */
    Result deleteWallpaperById(String wallpaperId);

    /**
     * 添加壁纸
     *
     * @param wallPaper     壁纸表实体类
     * @param multipartFile 图片文件
     * @param userId        用户ID
     * @return 返回结果
     */
    Result addWallPaper(WallPaper wallPaper, MultipartFile multipartFile, String userId) throws IOException;

    /**
     * 用户喜爱图片
     *
     * @param userId  用户ID
     * @param imageId 图片ID
     * @return 返回结果
     */
    Result favoriteImage(String userId, String imageId);

    /**
     * 查看是壁纸否被收藏
     *
     * @param userId  用户ID
     * @param imageId 图片ID
     * @return 返回结果
     */
    Result isFavoriteImage(String userId, String imageId);

    /**
     * 取消收藏壁纸
     *
     * @param userId  用户ID
     * @param imageId 壁纸ID
     * @return 返回结果
     */
    Result notFavoriteImage(String userId, String imageId);

    /**
     * 获取用户收藏壁纸列表
     *
     * @param userId 用户ID
     * @return 返回结果
     */
    Result userFavoriteWallPaperListAndCategory(String userId);

    /**
     * 修改壁纸
     *
     * @param wallPaper 修改的壁纸对象
     * @return 返回结果
     */
    Result changeWallPaper(WallPaper wallPaper);
}
