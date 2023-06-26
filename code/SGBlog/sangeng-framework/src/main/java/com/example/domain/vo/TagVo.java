package com.example.domain.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@ApiModel(value="Tag对象", description="标签")
@SuppressWarnings(value={"serial", "unused"})
@AllArgsConstructor
@NoArgsConstructor
@Data
@Repository
public class TagVo {
    @ApiModelProperty(value="")
    private Long id;

    @ApiModelProperty(value="标签名")
    private String name;
    
    @ApiModelProperty(value="备注")
    private String remark;
    



}

