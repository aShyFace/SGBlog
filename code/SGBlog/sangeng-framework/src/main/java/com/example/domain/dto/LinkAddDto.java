package com.example.domain.dto;

import com.example.handler.exception.ValidationGroups;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName: LinkVo
 * @Description:
 * @author: Zhi
 * @date: 2023/3/29 下午8:50
 */
@ApiModel(value = "友链实体类")
@AllArgsConstructor
@NoArgsConstructor
@Repository
@Data
public class LinkAddDto {
    @ApiModelProperty(value = "友链名称")
    @NotBlank(message = "名称不能为空", groups = ValidationGroups.LinkInsert.class)
    private String name;

    @ApiModelProperty(value = "友链logo")
    private String logo;

    @ApiModelProperty(value = "网站地址")
    @NotBlank(message = "地址不能为空", groups = ValidationGroups.LinkInsert.class)
    private String address;

    @ApiModelProperty(value = "描述信息")
    private String description;

    @ApiModelProperty(value = "0正常，1停用")
    @NotBlank(message = "状态不能为空", groups = ValidationGroups.LinkInsert.class)
    private String status;
}
