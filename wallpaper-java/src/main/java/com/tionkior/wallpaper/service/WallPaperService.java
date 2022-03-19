package com.tionkior.wallpaper.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tionkior.wallpaper.pojo.WallPaper;
import com.tionkior.wallpaper.pojo.vo.Result;

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
}
