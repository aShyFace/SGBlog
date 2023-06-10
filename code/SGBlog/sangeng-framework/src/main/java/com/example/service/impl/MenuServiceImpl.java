package com.example.service.impl;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.lang.tree.parser.DefaultNodeParser;
import cn.hutool.core.lang.tree.parser.NodeParser;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.constant.DataBaseConstant;
import com.example.constant.MenuConstant;
import com.example.domain.vo.MenuVo;
import com.example.mapper.MenuMapper;
import com.example.domain.entity.Menu;
import com.example.mapper.UserMapper;
import com.example.service.MenuService;
import com.example.service.RoleService;
import com.example.service.UserService;
import com.example.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单权限表(Menu)表服务实现类
 *
 * @author Zhi
 * @since 2023-05-31 21:14:51
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Resource
    private MenuMapper menuMapper;
    @Resource
    private RoleService roleService;

    public List<String> selectMenusByUserId(Long userId, boolean userIsRoot) {
        List<Menu> menuList = null;
        List<String> permList = null;
        if (userIsRoot){
            LambdaQueryWrapper<Menu> lqw = new LambdaQueryWrapper<>();
            lqw.in(Menu::getMenuType, MenuConstant.MENU_IS_MENU, MenuConstant.MENU_IS_BUTTON);
            lqw.eq(Menu::getStatus, MenuConstant.MENU_STATUS_IS_NORMAL);
            menuList = list(lqw);
        }else{
            menuList = menuMapper.selectMenusByUserId(userId, MenuConstant.MENU_STATUS_IS_NORMAL,
                MenuConstant.MENU_IS_MENU, MenuConstant.MENU_IS_BUTTON, DataBaseConstant.ROW_IS_NOT_DELETE);
        }
        permList = menuList.stream().map(
            Menu::getPerms
        ).collect(Collectors.toList());
        return permList;
    }

    public List<MenuVo> selectRouterMenuTreeByUserId(Long userId, boolean userIsRoot) {
        List<Menu> menuList = null;
        if (userIsRoot){
            // LambdaQueryWrapper<Menu> lqw = new QueryWrapper<Menu>()
            //         .select(String.join(" ", "DISTINCT", MenuConstant.MENU_ID_FIELD)).lambda();
            LambdaQueryWrapper<Menu> lqw = new LambdaQueryWrapper<>();
            lqw.in(Menu::getMenuType, MenuConstant.MENU_IS_MENU, MenuConstant.MENU_IS_CATALOG)
                    .eq(Menu::getStatus, MenuConstant.MENU_STATUS_IS_NORMAL);
            menuList = list(lqw);
        }else{
            menuList = menuMapper.selectMenusByUserId(userId, MenuConstant.MENU_STATUS_IS_NORMAL,
                    MenuConstant.MENU_IS_MENU, MenuConstant.MENU_IS_CATALOG, DataBaseConstant.ROW_IS_NOT_DELETE);
        }

        List<MenuVo> menuTree = menuList2menuTree(menuList, MenuConstant.PARENT_ID);
        return menuTree;
    }


    private List<MenuVo> menuList2menuTree(List<Menu> menuList, Long partenId) {
        List<MenuVo> menuVoList = BeanCopyUtils.copyBeanList(menuList, MenuVo.class);
        return menuVoList.stream()
            .filter(menuVo -> menuVo.getParentId().equals(partenId))
            .map(menuVo -> menuVo.setChildren(getChildren(menuVo, menuVoList)))
            .collect(Collectors.toList());
    }

    private List<MenuVo> getChildren(MenuVo partenMenuVo, List<MenuVo> menuVoList) {
        List<MenuVo> menuVoChildrenList = menuVoList.stream()
            .filter(menuVo -> menuVo.getParentId().equals(partenMenuVo.getId()))
            .map(menuVo -> menuVo.setChildren(getChildren(menuVo, menuVoList)))
            .collect(Collectors.toList());
        return menuVoChildrenList;
    }


}
