package com.tionkior.wallpaper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tionkior.wallpaper.mapper.WallPaperMapper;
import com.tionkior.wallpaper.entity.WallPaper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class WallpaperJavaApplicationTests {

    @Autowired
    private WallPaperMapper wallPaperMapper;

    @Test
    void contextLoads() {
        List<WallPaper> list = wallPaperMapper.selectList(new QueryWrapper<>());
        System.out.println(list);
    }

    @Test
    void logTest() {
        String logStr = "测试这是方法";
        log.debug("This test : {}", logStr);
        log.error("This test : {}", logStr);
    }
}
