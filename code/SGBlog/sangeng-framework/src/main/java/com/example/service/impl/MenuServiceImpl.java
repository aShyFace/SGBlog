package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.constant.DataBaseConstant;
import com.example.constant.MenuConstant;
import com.example.domain.entity.Menu;
import com.example.domain.vo.MenuVo;
import com.example.mapper.MenuMapper;
import com.example.service.MenuService;
import com.example.service.RoleService;
import com.example.utils.BeanCopyUtils;
import com.example.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
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

    /**
     * 选择菜单用户id
     *
     * @param userId    用户id
     * @param status    状态（默认0,表示正常使用）
     * @param menuType1 菜单类型1（可选，M目录 C菜单(默认) F按钮）
     * @param menuType2 菜单类型2（可选，M目录 C菜单 F按钮(默认)）
     * @param del_flag  逻辑删除
     * @return {@link List}<{@link List}>
     */
    public List<String> selectMenusByUserId(Long userId, String status, String menuType1,
                                          String menuType2, String del_flag) {
        if (Objects.isNull(status)){
            status = MenuConstant.MENU_STATUS_IS_NORMAL;
        }
        if (Objects.isNull(menuType1)){
            menuType1 = MenuConstant.MENU_IS_MENU;
        }
        if (Objects.isNull(menuType2)){
            menuType2 = MenuConstant.MENU_IS_BUTTON;
        }
        if (Objects.isNull(del_flag)){
            del_flag = DataBaseConstant.ROW_IS_NOT_DELETE;
        }

        List<Menu> menuList = menuMapper.selectMenusByUserId(userId, status, menuType1, menuType2, del_flag);
        List<String> permList = menuList.stream().map(
            Menu::getPerms
        ).collect(Collectors.toList());
        return permList;
    }

    /**
     * 是超级用户
     * @param permission 用户所具有的权限
     * @return boolean
     */
    public boolean isRoot(String permission){
        if (SecurityUtils.isRoot()) {
            return true;
        }
        //否则  获取当前登录用户所具有的权限列表 如何判断是否存在permission
        List<String> permissions = SecurityUtils.getLoginUser().getPermissionList();
        return permissions.contains(permission);
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
