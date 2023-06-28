package com.example.domain.dto;


import com.example.handler.exception.ValidationGroups;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@ApiModel(value="Tag对象", description="标签")
@SuppressWarnings(value={"serial", "unused"})
@AllArgsConstructor
@NoArgsConstructor
@Data
@Repository
public class TagDto {
    @ApiModelProperty(value="id")
    @NotNull(message = "修改标签时，id不能为空", groups = {ValidationGroups.TagUpdate.class})
    @Null(message = "创建标签时，不能指定id", groups = {ValidationGroups.TagInsert.class})
    private Long id;

    @ApiModelProperty(value="标签名")
    @NotBlank(message = "创建标签时，标签名不能为空", groups = {ValidationGroups.TagInsert.class})
    private String name;

    @ApiModelProperty(value="备注")
    private String remark;


}

