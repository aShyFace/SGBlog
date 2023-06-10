package com.example.domain.vo;


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
 * 角色信息表(Role)表实体类
 *
 * @author Zhi
 * @since 2023-05-31 21:57:55
 */
@ApiModel(value="Role对象", description="角色信息表")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Repository
public class RoleVo {
    @ApiModelProperty(value="角色ID")
    private Long id;

    @ApiModelProperty(value="角色名称")
    private String roleName;
    
    @ApiModelProperty(value="角色权限字符串")
    private String roleKey;
    
    @ApiModelProperty(value="显示顺序")
    private Integer roleSort;
    
    @ApiModelProperty(value="角色状态（0正常 1停用）")
    private String status;
    
    @ApiModelProperty(value="删除标志（0代表存在 1代表删除）")
    private String delFlag;
    
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
    



}

