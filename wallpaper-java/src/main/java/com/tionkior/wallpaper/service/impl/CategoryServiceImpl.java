package com.tionkior.wallpaper.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tionkior.wallpaper.mapper.CategoryMapper;
import com.tionkior.wallpaper.pojo.Category;
import com.tionkior.wallpaper.service.CategoryService;
import org.springframework.stereotype.Service;

/**
 * @author : TionKior
 * @date : 2022/2/25 9:00
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
