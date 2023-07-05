package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.constant.DataBaseConstant;
import com.example.constant.MenuConstant;
import com.example.constant.MethodConstant;
import com.example.domain.dto.MenuDto;
import com.example.domain.entity.Menu;
import com.example.domain.vo.MenuManagerVo;
import com.example.domain.vo.MenuPreviewVo;
import com.example.domain.vo.MenuTreeselectVo;
import com.example.domain.vo.MenuVo;
import com.example.mapper.MenuMapper;
import com.example.service.MenuService;
import com.example.service.RoleService;
import com.example.utils.BeanCopyUtils;
import com.example.utils.DeduplicationUtil;
import com.example.utils.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
     * 获取所有菜单
     *
     * @param status   状态
     * @param menuName 菜单名称
     * @return {@link List}<{@link MenuManagerVo}>
     */
    public List<MenuManagerVo> allMenu(String status, String menuName) {
        LambdaQueryWrapper<Menu> lqw = new LambdaQueryWrapper();
        if (StringUtils.hasText(menuName)){
            lqw.like(Menu::getMenuName, menuName);
        }
        if (StringUtils.hasText(status)) {
            lqw.eq(Menu::getRemark, status);
        }
        List<Menu> menuList = menuMapper.selectList(lqw.orderByAsc(Menu::getOrderNum).orderByAsc(Menu::getParentId));
        List<MenuManagerVo> menuManagerVoList = BeanCopyUtils.copyBeanList(menuList, MenuManagerVo.class);
        return menuManagerVoList;
    }

    /**
     * 添加菜单
     *
     * @param menuDto 菜单dto
     * @return int
     */
    public int addMenu(MenuDto menuDto) {
        Menu menu = BeanCopyUtils.copyBean(menuDto, Menu.class);
        int status = menuMapper.insert(menu) != 0 ? MethodConstant.SUCCESS:MethodConstant.ERROR;
        return status;
    }

    public MenuPreviewVo getMenuById(Long id) {
        Menu menu = menuMapper.selectById(id);
        return BeanCopyUtils.copyBean(menu, MenuPreviewVo.class);
    }

    public int deleteMenuById(Long id) {
        int status = 0;
        LambdaQueryWrapper<Menu> lqw = new LambdaQueryWrapper<Menu>().eq(Menu::getId, id);
        List<Menu> menuList = menuMapper.selectList(null);
        List<Long> parentIdList = menuList.stream().map(menu -> menu.getParentId()).collect(Collectors.toList());

        if (parentIdList.contains(id)) {
            status = -1;
        }else if (count(lqw) > 0) {
            int i = menuMapper.deleteById(id);
            status = i != 0 ? MethodConstant.SUCCESS : MethodConstant.ERROR;
        }
        return status;
    }

    public int updateMenuById(MenuDto menuDto) {
        Menu menu = BeanCopyUtils.copyBean(menuDto, Menu.class);
        int ret = menuMapper.updateById(menu);
        return ret;
    }

    /**
     * 获取角色菜单树
     *
     * @param roleId 角色id
     * @return {@link List}<{@link MenuTreeselectVo}>
     */
    @Override
    public List<MenuTreeselectVo> getRoleMenuTreeselectById(Long roleId) {
        List<Menu> menuList = null;
        if (Objects.isNull(roleId)) {
            menuList = menuMapper.selectList(null);
        }else{
            menuList = menuMapper.getRoleMenuTreeselect(roleId);
        }
        // 过滤重复数据
        menuList = menuList.stream().filter(
          DeduplicationUtil.distinctByKey(Menu::getId)
        ).collect(Collectors.toList());
        // 转成VO
        List<MenuTreeselectVo> menuTreeselectVoList = BeanCopyUtils.copyBeanList(menuList, MenuTreeselectVo.class);
        menuTreeselectVoList = menuTreeselectVoList.stream().map(
          m -> m.setLabel(m.getMenuName())
        ).collect(Collectors.toList());
        // 构建菜单树
        menuTreeselectVoList = builderTree(menuTreeselectVoList, MenuConstant.PARENT_ID);
        return menuTreeselectVoList;
    }

    /**
     * 是超级用户`
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

        List<MenuVo> menuVoList = menuList2menuTree(menuList, MenuConstant.PARENT_ID);
        return menuVoList;
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

    /**
     * 树构建器菜单
     *
     * @param menus    菜单
     * @param parentId 父id
     * @return {@link List}<{@link Menu}>
     */
    private List<MenuTreeselectVo> builderTree(List<MenuTreeselectVo> menus, Long parentId) {
        List<MenuTreeselectVo> menuTree = menus.stream()
          .filter(menu -> menu.getParentId().equals(parentId))
          .map(menu -> menu.setChildren(getMenuChildren(menu, menus)))
          .collect(Collectors.toList());
        return menuTree;
    }

    /**
     * 获取存入参数的 子MenuTreeselectVo集合
     * @param menu
     * @param menus
     * @return
     */
    private List<MenuTreeselectVo> getMenuChildren(MenuTreeselectVo menu, List<MenuTreeselectVo> menus) {
        List<MenuTreeselectVo> childrenList = menus.stream()
          .filter(m -> m.getParentId().equals(menu.getId()))
          .map(m->m.setChildren(getMenuChildren(m, menus)))
          .collect(Collectors.toList());
        return childrenList;
    }
}
