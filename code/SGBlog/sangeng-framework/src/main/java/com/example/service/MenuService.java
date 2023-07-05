package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.domain.dto.MenuDto;
import com.example.domain.entity.Menu;
import com.example.domain.vo.MenuManagerVo;
import com.example.domain.vo.MenuPreviewVo;
import com.example.domain.vo.MenuTreeselectVo;
import com.example.domain.vo.MenuVo;

import java.util.List;

/**
 * 菜单权限表(Menu)表服务接口
 *
 * @author Zhi
 * @since 2023-05-31 21:14:51
 */
public interface MenuService extends IService<Menu> {

    List<String> selectMenusByUserId(Long userId, boolean userIsRoot);

    List<MenuVo> selectRouterMenuTreeByUserId(Long userId, boolean userIsRoot);

    List<String> selectMenusByUserId(Long userId, String status, String menuType1,
                                          String menuType2, String del_flag);

  List<MenuManagerVo> allMenu(String status, String menuName);

  int addMenu(MenuDto menuDto);

  MenuPreviewVo getMenuById(Long id);

  int deleteMenuById(Long id);

  int updateMenuById(MenuDto menuDto);

  List<MenuTreeselectVo> getRoleMenuTreeselectById(Long roleId);
}

