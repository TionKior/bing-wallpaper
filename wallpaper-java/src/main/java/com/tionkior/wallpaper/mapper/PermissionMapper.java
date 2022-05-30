package com.tionkior.wallpaper.mapper;

import com.tionkior.wallpaper.entity.Permission;
import com.tionkior.wallpaper.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author : TionKior
 * @date : 2022/3/12 16:40
 */

@Mapper
public interface PermissionMapper {

    @Select("select permission.* from permission\n" +
            "left join role_permissions rp on permission.per_id = rp.per_id\n" +
            "left join role r on r.role_id = rp.role_id\n" +
            "where r.role_id = #{roleId};")
    List<Permission> getPermissionByRoleId(Role role);


}
