package com.example.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

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
public class LinkPreviewVo {
    @ApiModelProperty(value = "")
    private Long id;

    @ApiModelProperty(value = "友链名称")
    private String name;

    @ApiModelProperty(value = "友链logo")
    private String logo;

    @ApiModelProperty(value = "网站地址")
    private String address;

    @ApiModelProperty(value = "描述信息")
    private String description;

    @ApiModelProperty(value = "0正常，1停用")
    private String status;
}
