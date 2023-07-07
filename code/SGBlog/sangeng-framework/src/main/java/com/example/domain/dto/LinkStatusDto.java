package com.example.domain.dto;

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
public class LinkStatusDto {
    @ApiModelProperty(value = "id")
    @NotBlank(message = "id不能为空")
    private Long id;

    @ApiModelProperty(value = "0正常，1停用")
    @NotBlank(message = "状态不能为空")
    private String status;
}
