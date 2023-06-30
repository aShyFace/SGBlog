package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.PageParams;
import com.example.common.PageResult;
import com.example.domain.dto.TagDto;
import com.example.domain.entity.Tag;
import com.example.domain.vo.TagVo;

import java.util.List;

/**
 * 标签(Tag)表服务接口
 *
 * @author makejava
 * @since 2023-06-20 21:36:56
 */
public interface TagService extends IService<Tag> {

  PageResult tagList(PageParams pageParams, String name, String remark);

  int addTag(TagDto tagDto);

  int deleteTag(Long tagId);

  TagVo getTagById(Long tagId);

  int updateTag(TagDto tagDto);

  List<TagVo> allTag();
}

