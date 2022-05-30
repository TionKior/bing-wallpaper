package com.tionkior.wallpaper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tionkior.wallpaper.common.Constant;
import com.tionkior.wallpaper.entity.dto.WallPaperDTO;
import com.tionkior.wallpaper.entity.vo.ResultCodeEnum;
import com.tionkior.wallpaper.mapper.WallPaperMapper;
import com.tionkior.wallpaper.entity.WallPaper;
import com.tionkior.wallpaper.entity.vo.Result;
import com.tionkior.wallpaper.service.UserService;
import com.tionkior.wallpaper.service.WallPaperService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @author : TionKior
 * @date : 2022/2/24 12:01
 */

@Service
public class WallPaperServiceImpl extends ServiceImpl<WallPaperMapper, WallPaper> implements WallPaperService {

    @Autowired
    private WallPaperMapper wallPaperMapper;

    @Autowired
    private UserService userService;

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

    @Override
    public Result getWallPaperList() {
        List<WallPaperDTO> wallPaperDTOList = wallPaperMapper.getWallPaperList();
        return CollectionUtils.isEmpty(wallPaperDTOList) ? Result.failure(ResultCodeEnum.NOT_HAVE_WALLPAPER) : Result.success(wallPaperDTOList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result deleteWallpaperById(String wallpaperId) {
        // 如果图片被收藏则删除图片
        Integer count = wallPaperMapper.selectUserFavoriteByWallpaperId(wallpaperId);
        if (count > 0) {
            wallPaperMapper.deleteUserFavotiteById(wallpaperId);
        }

        boolean result = this.removeById(wallpaperId);
        return result ? Result.success() : Result.failure();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result addWallPaper(WallPaper wallPaper, MultipartFile multipartFile, String userId) throws IOException {
        if (ObjectUtils.isEmpty(wallPaper)) {
            return Result.failure(ResultCodeEnum.NULL);
        }
        // 保存图片
        if (ObjectUtils.isNotEmpty(multipartFile)) {
            //获取上传文件名,包含后缀
            String originalFilename = multipartFile.getOriginalFilename();
            //获取后缀
            String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
            //保存的文件名
            String dFileName = UUID.randomUUID() + substring;
            String path = System.getProperty("user.dir") + "/src/main/resources/static/images/bingwallpaper/";
            if (!new File(path).exists()) {
                new File(path).mkdirs();
            }
            //生成保存文件
            File uploadFile = new File(path + dFileName);
            System.out.println(uploadFile);
            // 保存文件
            multipartFile.transferTo(uploadFile);
            // 保存到数据库
            String filePath = "http://localhost:8080/images/bingwallpaper/" + dFileName;
            wallPaper.setImageUrl(filePath);
            wallPaper.setCreateTime(new Timestamp(System.currentTimeMillis()));
        }
        boolean result = this.save(wallPaper);
        // 向类别中间表插入数据
        wallPaperMapper.insertWallPaperIdAndCategoryId(wallPaper.getId(), wallPaper.getCategoryId());
        // 给用户加积分
        userService.addUserIntegral(userId, Constant.ADD_POINTS_FOR_ONE_UPLOAD);

        return result ? Result.success() : Result.failure();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result favoriteImage(String userId, String imageId) {
        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(imageId)) {
            return Result.failure(ResultCodeEnum.NOT_LOGIN);
        }
        Integer favoriteImageCount = wallPaperMapper.findFavoriteImage(userId, imageId);
        if (favoriteImageCount > 0) {
            return Result.failure(ResultCodeEnum.FAVORITE_IMAGE_EXIST);
        }
        wallPaperMapper.favoriteImage(userId, imageId);

        return Result.success();
    }

    @Override
    public Result isFavoriteImage(String userId, String imageId) {
        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(imageId)) {
            return Result.failure(ResultCodeEnum.NOT_LOGIN);
        }
        Integer favoriteImageCount = wallPaperMapper.findFavoriteImage(userId, imageId);
        return favoriteImageCount > 0 ? Result.failure(ResultCodeEnum.FAVORITE_IMAGE_EXIST) : Result.success();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result notFavoriteImage(String userId, String imageId) {
        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(imageId)) {
            return Result.failure(ResultCodeEnum.NULL);
        }
        Integer favoriteImageCount = wallPaperMapper.findFavoriteImage(userId, imageId);
        if (favoriteImageCount == 0) {
            return Result.failure(ResultCodeEnum.NOT_FAVORITE_IMAGE);
        }
        wallPaperMapper.notFavoriteImage(userId, imageId);
        return Result.success();
    }

    @Override
    public Result userFavoriteWallPaperListAndCategory(String userId) {
        List<WallPaperDTO> wallPaperDTOList = wallPaperMapper.userFavoriteWallPaperListAndCategory(userId);
        return CollectionUtils.isEmpty(wallPaperDTOList) ?
                Result.failure(ResultCodeEnum.NOT_HAVE_WALLPAPER) : Result.success(wallPaperDTOList);
    }

    @Override
    public Result changeWallPaper(WallPaper wallPaper) {
        boolean result = this.updateById(wallPaper);
        return result ? Result.success() : Result.failure();
    }

}