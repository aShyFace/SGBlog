package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.PageParams;
import com.example.common.PageResult;
import com.example.constant.MethodConstant;
import com.example.constant.RedisConstant;
import com.example.constant.RoleConstant;
import com.example.domain.dto.*;
import com.example.domain.entity.LoginUser;
import com.example.domain.entity.Role;
import com.example.domain.entity.User;
import com.example.domain.entity.UserRole;
import com.example.domain.vo.RoleVo;
import com.example.domain.vo.user.UserAdminChildVo;
import com.example.domain.vo.user.UserAdminInfoVo;
import com.example.domain.vo.user.UserPreviewVo;
import com.example.enums.AppHttpCodeEnum;
import com.example.exception.SystemException;
import com.example.mapper.RoleMapper;
import com.example.mapper.UserMapper;
import com.example.service.UserRoleService;
import com.example.service.UserService;
import com.example.utils.BeanCopyUtils;
import com.example.utils.RedisCache;
import com.example.utils.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 用户表(User)表服务实现类
 *
 * @author Zhi
 * @since 2023-04-11 11:20:28
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private PasswordEncoder passwordEncoder;
//    该工具类的方法不是static方法，所以要注入才能使用
//    @Lazy
    @Resource
    private RedisCache redisCache;
    @Resource
    private UserRoleService userRoleService;

    public User updateUserInfo(UserInfoDto userInfoDto) {
        // 1.修改数据库中的user信息（返回更新后的user对象）
        User user = SecurityUtils.getLoginUser().getUser();
        BeanUtils.copyProperties(userInfoDto, user); // 覆盖user中，userVo里有且值不为空的属性
        int ret = userMapper.updateById(user);
        // 插入失败，则用户不存在
        if (ret == 0){
            throw new SystemException(AppHttpCodeEnum.USER_NOT_EXIST);
        }
        // 2-3.用户存在，则更新redis和SecurityContex中的user数据
        updateUserOfSecurityContextAndRedis(user);
        return user;
    }


    public boolean register(UserAuthDto userAuthDto) {
        if (userNameExist(userAuthDto.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        if (nickNameExist(userAuthDto.getNickName())){
            throw new SystemException(AppHttpCodeEnum.NICKNAME_EXIST);
        }

        // 加密后存入数据库
        String password = userAuthDto.getPassword();
        userAuthDto.setPassword(passwordEncoder.encode(password));
        User user = BeanCopyUtils.copyBean(userAuthDto, User.class);
        return save(user);
    }

    @Override
    public PageResult<UserPreviewVo> getUserList(PageParams pageParams, UserPageDto userPageDto) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper();
        if (Objects.nonNull(userPageDto)) {
            if (StringUtils.hasText(userPageDto.getUserName())) {
                lqw.eq(User::getUserName, userPageDto.getUserName());
            }
            if (StringUtils.hasText(userPageDto.getPhonenumber())) {
                lqw.eq(User::getPhonenumber, userPageDto.getPhonenumber());
            }
            if (StringUtils.hasText(userPageDto.getStatus())) {
                lqw.eq(User::getStatus, userPageDto.getStatus());
            }
        }

        Page<User> page = new Page(pageParams.getPageNum(), pageParams.getPageSize());
        List<User> userList = page(page, lqw).getRecords();
        List<UserPreviewVo> userPreviewVoList = BeanCopyUtils.copyBeanList(userList, UserPreviewVo.class);
        return new PageResult(userPreviewVoList, page.getTotal(), pageParams);
    }

    @Transactional
    public int updateUser(UserUpdateDto userUpdateDto) {
        // 更新用户
        User user = BeanCopyUtils.copyBean(userUpdateDto, User.class);
        int ret = userMapper.updateById(user);
        // 更新 用户-角色
        Long userId = user.getId();
        List<UserRole> userRoleList = userUpdateDto.getRoleIds().stream().map(
          roleId -> new UserRole(userId, roleId)
        ).collect(Collectors.toList());
        userRoleService.saveOrUpdateBatchByMultiId(userRoleList);
        return MethodConstant.ERROR != ret ? MethodConstant.SUCCESS:MethodConstant.ERROR;
    }

    @Transactional
    public int addUser(UserAddDto userAddDto) {
        // 判断 用户名、手机号、邮箱 是否重复
        List<Boolean> duplicateList = allUserList().stream().map(
          user -> user.getUserName().equals(userAddDto.getUserName()) ||
                  user.getPhonenumber().equals(userAddDto.getPhonenumber()) ||
                  user.getEmail().equals(userAddDto.getEmail())
        ).collect(Collectors.toList());
        if (duplicateList.contains(Boolean.TRUE)){
            return MethodConstant.ERROR;
        }

        // 添加用户
        User user = BeanCopyUtils.copyBean(userAddDto, User.class);
        int ret = userMapper.insert(user);
        // 添加 用户-角色
        Long userId = user.getId();
        List<UserRole> userRoleList = userAddDto.getRoleIds().stream().map(
          roleId -> new UserRole(userId, roleId)
        ).collect(Collectors.toList());
        userRoleService.saveOrUpdateBatchByMultiId(userRoleList);
        return MethodConstant.ERROR != ret ? MethodConstant.SUCCESS:MethodConstant.ERROR;
    }

    @Override
    public int deleteUserById(Long id) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getId, id);
        if (count(lqw) > 0){ // 大于0表示未删除
            userMapper.deleteById(id); //逻辑删除
            LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
            userRoleService.getBaseMapper().delete(queryWrapper.eq(UserRole::getUserId, id));
        }
        return MethodConstant.SUCCESS;
    }

    @Override
    public int updateUserStatus(UserStatusDto userStatusDto) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        User user = BeanCopyUtils.copyBean(userStatusDto, User.class);
        lqw.eq(User::getId, userStatusDto.getUserId());
        int ret = userMapper.update(user, lqw);
        return MethodConstant.ERROR != ret ? MethodConstant.SUCCESS:MethodConstant.ERROR;
    }

    @Override
    public UserAdminInfoVo getUserById(Long id) {
        // 获取角色id
        LambdaQueryWrapper<UserRole> lqw = new LambdaQueryWrapper<>();
        List<UserRole> userRoleList = userRoleService.getBaseMapper().selectList(lqw.eq(UserRole::getUserId, id));
        List<Long> roleIds = userRoleList.stream().map(UserRole::getRoleId).collect(Collectors.toList());
        // 获取所有role
        LambdaQueryWrapper<Role> lqwRole = new LambdaQueryWrapper<>();
        List<Role> roleList = roleMapper.selectList(lqwRole.eq(Role::getStatus, RoleConstant.ROLE_STATUS_IS_NORMAL));
        List<RoleVo> roleVoList = BeanCopyUtils.copyBeanList(roleList, RoleVo.class);
        // 获取角色信息
        User user = userMapper.selectById(id);
        UserAdminChildVo userAdminChildVo = BeanCopyUtils.copyBean(user, UserAdminChildVo.class);
        UserAdminInfoVo userAdminInfoVo = new UserAdminInfoVo(roleIds, roleVoList, userAdminChildVo);
        return userAdminInfoVo;
    }


    private void updateUserOfSecurityContextAndRedis(User user){
        // 更新SecurityContex中的user数据
        SecurityUtils.getLoginUser().setUser(user);
        // 更新redis中的user数据
        LoginUser loginUser = SecurityUtils.getLoginUser();
        String key = String.join("", RedisConstant.USER_INFO_KEY, loginUser.getUser().getId().toString());
        redisCache.setCacheObject(key, loginUser);
    }
    private boolean userNameExist(String userName){
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUserName, userName);
        return count(lqw) > 0;
    }
    private boolean nickNameExist(String nickName){
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getNickName, nickName);
        return count(lqw) > 0;
    }
    private List<User> allUserList(){
        List<User> userList = userMapper.selectList(null);
        return userList;
    }
}
