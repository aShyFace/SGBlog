package com.example.domain.vo;


import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryExcelVo {
  @ExcelProperty(value = "分类名称")
  private String name;

  @ExcelProperty(value = "描述")
  private String description;

  @ExcelProperty(value = "状态0:正常,1禁用")
  private String status;
}
