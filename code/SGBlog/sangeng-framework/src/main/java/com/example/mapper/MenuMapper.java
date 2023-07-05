package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.domain.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单权限表(Menu)表数据库访问层
 *
 * @author Zhi
 * @since 2023-05-31 21:14:51
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 选择菜单用户id
     *
     * @param userId    用户id
     * @param status    状态
     * @param menuType1 菜单类型1
     * @param menuType2 菜单类型2
     * @param del_flag  逻辑删除字段
     * @return {@link List}<{@link Menu}>
     */
    List<Menu> selectMenusByUserId(@Param("userId")Long userId, @Param("status") String status,
                                   @Param("menuType1") String menuType1, @Param("menuType2") String menuType2,
                                   @Param("del_flag") String del_flag);

    List<Menu> getRoleMenuTreeselect(@Param("roleId")Long roleId);
}

