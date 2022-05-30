package com.tionkior.wallpaper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tionkior.wallpaper.entity.WallPaper;
import com.tionkior.wallpaper.entity.dto.WallPaperDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author : TionKior
 * @date : 2022/2/24 12:01
 */

@Mapper
public interface WallPaperMapper extends BaseMapper<WallPaper> {

    @Select("select wallpaper.id,image_url,image_name,author,address,create_time,category\n" +
            "    from wallpaper left join category c on c.id = wallpaper.category_id")
    List<WallPaperDTO> getWallPaperList();


    @Insert("insert wallpaper_category (wallpaper_id, category_id) values (#{id},#{categoryId});")
    void insertWallPaperIdAndCategoryId(Long id, String categoryId);

    /**
     * 查找用户是否已经喜欢此图片
     *
     * @param userId  用户ID
     * @param imageId 图片ID
     */
    @Select("select count(0) from user_favorite where user_id = #{userId} and wallpaper_id = #{imageId}")
    Integer findFavoriteImage(String userId, String imageId);

    /**
     * 收藏图片
     *
     * @param userId
     * @param imageId
     */
    @Insert("insert into user_favorite (user_id, wallpaper_id) values (#{userId},#{imageId})")
    void favoriteImage(String userId, String imageId);

    /**
     * 取消收藏
     *
     * @param userId  用户ID
     * @param imageId 壁纸ID
     */
    @Delete("delete from user_favorite where user_id = #{userId} and wallpaper_id = #{imageId}")
    void notFavoriteImage(String userId, String imageId);

    /**
     * 查找用户收藏的图片
     *
     * @param userId 用户ID
     */
    @Select("select wallpaper.id, image_url, image_name, author, address, create_time, category\n" +
            "from wallpaper\n" +
            "         left join category c on c.id = wallpaper.category_id\n" +
            "         left join user_favorite uf on wallpaper.id = uf.wallpaper_id\n" +
            "         left join user u on uf.user_id = u.user_id\n" +
            "where u.user_id = #{userId}\n" +
            "ORDER BY wallpaper_id;\n")
    List<WallPaperDTO> userFavoriteWallPaperListAndCategory(String userId);

    /**
     * @param wallpaperId
     */
    @Select("select count(0) from user_favorite where wallpaper_id = #{wallpaperId}")
    Integer selectUserFavoriteByWallpaperId(String wallpaperId);

    /**
     * 删除用户喜爱壁纸中间表
     *
     * @param wallpaperId 壁纸ID
     */
    @Delete("delete from user_favorite where wallpaper_id = #{wallpaperId}")
    void deleteUserFavotiteById(String wallpaperId);
}
