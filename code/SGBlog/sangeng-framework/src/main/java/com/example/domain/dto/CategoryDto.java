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

    private Long id;


    private String name;


    private Long pid;


    private String description;


    private String status;
}
