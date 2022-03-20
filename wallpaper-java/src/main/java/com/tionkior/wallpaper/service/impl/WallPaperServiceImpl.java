package com.tionkior.wallpaper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tionkior.wallpaper.mapper.WallPaperMapper;
import com.tionkior.wallpaper.entity.WallPaper;
import com.tionkior.wallpaper.entity.vo.Result;
import com.tionkior.wallpaper.service.WallPaperService;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author : TionKior
 * @date : 2022/2/24 12:01
 */

@Service
public class WallPaperServiceImpl extends ServiceImpl<WallPaperMapper, WallPaper> implements WallPaperService {

    @Override
    public Result<Page<WallPaper>> getWallPaperPage(String currentPage, String categoryId) {
        LambdaQueryWrapper<WallPaper> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(WallPaper::getCategoryId, categoryId);
        wrapper.orderByDesc(WallPaper::getCreateTime);
        Page<WallPaper> page = this.page(
                new Page<>(Integer.parseInt(currentPage), 9),
                wrapper);

        return Result.success(page);
    }

    @Override
    public Result<WallPaper> getWallPaperRandom() {
        Long count = this.count();
        int randomNumber = new Random().nextInt(Integer.parseInt(String.valueOf(count))) + 1;
        LambdaQueryWrapper<WallPaper> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(WallPaper::getId, randomNumber);
        WallPaper wallpaper = this.getOne(wrapper);
        return Result.success(wallpaper);
    }

}