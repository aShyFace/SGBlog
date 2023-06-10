package com.example.domain.dto;


import java.util.Date;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;


/**
 * 菜单权限表(Menu)表实体类
 *
 * @author Zhi
 * @since 2023-05-31 21:14:51
 */
@ApiModel(value="Menu对象", description="菜单权限表")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Repository
public class MenuDto {
    @ApiModelProperty(value="菜单ID")
    private Long id;

    @ApiModelProperty(value="菜单名称")
    private String menuName;
    
    @ApiModelProperty(value="父菜单ID")
    private Long parentId;
    
    @ApiModelProperty(value="显示顺序")
    private Integer orderNum;
    
    @ApiModelProperty(value="路由地址")
    private String path;
    
    @ApiModelProperty(value="组件路径")
    private String component;
    
    @ApiModelProperty(value="是否为外链（0是 1否）")
    private Integer isFrame;
    
    @ApiModelProperty(value="菜单类型（M目录 C菜单 F按钮）")
    private String menuType;
    
    @ApiModelProperty(value="菜单状态（0显示 1隐藏）")
    private String visible;
    
    @ApiModelProperty(value="菜单状态（0正常 1停用）")
    private String status;
    
    @ApiModelProperty(value="权限标识")
    private String perms;
    
    @ApiModelProperty(value="菜单图标")
    private String icon;
    
    @ApiModelProperty(value="创建者")
    private Long createBy;
    
    @ApiModelProperty(value="创建时间")
    private Date createTime;
    
    @ApiModelProperty(value="更新者")
    private Long updateBy;
    
    @ApiModelProperty(value="更新时间")
    private Date updateTime;
    
    @ApiModelProperty(value="备注")
    private String remark;
    
    @ApiModelProperty(value="")
    private String delFlag;
    



}

