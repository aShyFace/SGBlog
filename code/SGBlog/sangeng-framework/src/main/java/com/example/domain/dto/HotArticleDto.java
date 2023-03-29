package com.example.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@NoArgsConstructor
@Repository
@Data
public class HotArticleDto {
    private Long id;

    private String title;

    private String content;

    private Long viewCount;
}
