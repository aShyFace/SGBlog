package com.example.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@NoArgsConstructor
@Repository
@Data
public class CategoryDto {
    @ApiModelProperty(value="")
    private Long id;

    @ApiModelProperty(value="分类名")
    private String name;

    @ApiModelProperty(value="父分类id，如果没有父分类为-1")
    private Long pid;

    @ApiModelProperty(value="描述")
    private String description;

    @ApiModelProperty(value="状态0:正常,1禁用")
    private String status;
}
