package com.example.controller;


import com.example.constant.MethodConstant;
import com.example.domain.ResponseResult;
import com.example.domain.dto.MenuDto;
import com.example.domain.vo.MenuManagerVo;
import com.example.domain.vo.MenuPreviewVo;
import com.example.domain.vo.MenuTreeselectVo;
import com.example.enums.AppHttpCodeEnum;
import com.example.handler.exception.ValidationGroups;
import com.example.service.MenuService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Min;
import java.util.HashMap;
import java.util.List;

/**
 * 菜单权限表(Menu)表控制层
 *
 * @author Zhi
 * @since 2023-07-04 09:29:03
 */
@Slf4j
@Validated
@RestController
@Api(tags = "登录接口")
@RequestMapping("/system/menu")
//@CrossOrigin(origins = "*")
public class MenuController {
  /**
   * 服务对象
   */
  @Resource
  private MenuService menuService;



  @GetMapping("/roleMenuTreeselect/{id}")
  public ResponseResult getRoleMenuTreeselectById(@Min(1L) @PathVariable Long id){
    log.debug("||||| {}::{} |||||", new Exception().getStackTrace()[0].getMethodName(), id);
    List<MenuTreeselectVo> menuTreeselectVoList = menuService.getRoleMenuTreeselectById(id);
    HashMap<String, List> dataMap = new HashMap<>();
    dataMap.put("menus", menuTreeselectVoList);
    return ResponseResult.okResult(dataMap);
  }

  @GetMapping("/treeselect")
  public ResponseResult getRoleMenuTreeselect(){
    log.debug("||||| {}::{} |||||", new Exception().getStackTrace()[0].getMethodName());
    List<MenuTreeselectVo> menuTreeselectVoList = menuService.getRoleMenuTreeselectById(null);
    return ResponseResult.okResult(menuTreeselectVoList);
  }

  @GetMapping("/list")
  public ResponseResult allMenu(String status, String menuName) {
    log.debug("||||| {}::{}, {} |||||", new Exception().getStackTrace()[0].getMethodName(),
      status, menuName);
    List<MenuManagerVo> menuManagerVoList = menuService.allMenu(status, menuName);
    return ResponseResult.okResult(menuManagerVoList);
  }

  @GetMapping("{id}")
  public ResponseResult queryMenu(@Min(1L) @PathVariable Long id) {
    log.debug("||||| {}::{}, {} |||||", new Exception().getStackTrace()[0].getMethodName(),
      id);
    MenuPreviewVo menuPreviewVo = menuService.getMenuById(id);
    return ResponseResult.okResult(menuPreviewVo);
  }

  @DeleteMapping ("{id}")
  public ResponseResult deleteMenu(@Min(1L) @PathVariable Long id) {
    log.debug("||||| {}::{}, {} |||||", new Exception().getStackTrace()[0].getMethodName(),
      id);
    int ret = menuService.deleteMenuById(id);
    if (MethodConstant.SUCCESS == ret) {
      return ResponseResult.okResult();
    }else if (MethodConstant.ERROR == ret) {
      return ResponseResult.errorResult(AppHttpCodeEnum.DELETE_ERROR);
    }else {
      return ResponseResult.errorResult(599, "存在子菜单，不允许删除");
    }
  }

  @PostMapping("")
  public ResponseResult addMenu(@Validated(value = ValidationGroups.MenuInsert.class)
                                  @RequestBody MenuDto menuDto) {
    log.debug("||||| {}::{} |||||", new Exception().getStackTrace()[0].getMethodName(),
      menuDto.toString());
    int ret = menuService.addMenu(menuDto);
    if (MethodConstant.SUCCESS == ret) {
      return ResponseResult.okResult();
    }else {
      return ResponseResult.errorResult(AppHttpCodeEnum.INSTER_ERROR);
    }
  }

  @PutMapping("")
  public ResponseResult updateMenu(@Validated(value = ValidationGroups.MenuUpdate.class)
                                     @RequestBody MenuDto menuDto) {
    log.debug("||||| {}::{} |||||", new Exception().getStackTrace()[0].getMethodName(),
      menuDto.toString());
    int ret = menuService.updateMenuById(menuDto);
    if (MethodConstant.SUCCESS == ret) {
      return ResponseResult.okResult();
    }else {
      return ResponseResult.errorResult(AppHttpCodeEnum.INSTER_ERROR);
    }
  }


}

