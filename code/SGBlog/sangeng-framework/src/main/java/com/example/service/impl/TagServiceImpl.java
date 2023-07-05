package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.PageParams;
import com.example.common.PageResult;
import com.example.constant.MethodConstant;
import com.example.domain.dto.TagDto;
import com.example.domain.entity.Tag;
import com.example.domain.vo.TagVo;
import com.example.mapper.TagMapper;
import com.example.service.TagService;
import com.example.utils.BeanCopyUtils;
import io.jsonwebtoken.lang.Strings;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;


/**
 * 标签(Tag)表服务实现类
 *
 * @author makejava
 * @since 2023-06-20 21:43:09
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
    @Resource
    private TagMapper tagMapper;


    public PageResult tagList(PageParams pageParams, String name, String remark) {
        LambdaQueryWrapper<Tag> lqw = new LambdaQueryWrapper();
        if (Objects.nonNull(name)){
            lqw.eq(Tag::getName, name);
        }
        if (StringUtils.hasText(remark)) {
            lqw.eq(Tag::getRemark, remark);
        }

        Page<Tag> page = new Page(pageParams.getPageNum(), pageParams.getPageSize());
        List<Tag> tagList = page(page, lqw).getRecords();
        List<TagVo> tagVoList = BeanCopyUtils.copyBeanList(tagList, TagVo.class);
        return new PageResult(tagVoList, page.getTotal(), pageParams);
    }


    public int addTag(TagDto tagDto) {
        int status = MethodConstant.ERROR;
        if (Strings.hasText(tagDto.getName())){
            Tag tag = BeanCopyUtils.copyBean(tagDto, Tag.class);
            status = save(tag) ? MethodConstant.SUCCESS : MethodConstant.ERROR;
        }
        return status;
    }

    public int deleteTag(Long tagId) {
        LambdaQueryWrapper<Tag> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Tag::getId, tagId);
        int status = MethodConstant.ERROR;
        if (count(lqw) > 0){ // 大于0表示未删除
            int i = tagMapper.deleteById(tagId); //逻辑删除
            status = i != 0 ? MethodConstant.SUCCESS:status;
        }
        return status;
    }

    public TagVo getTagById(Long tagId) {
        Tag tag = tagMapper.selectById(tagId);
        TagVo tagVo = null;
        if (Objects.nonNull(tag)) {
            tagVo = BeanCopyUtils.copyBean(tag, TagVo.class);
        }
        return tagVo;
    }

    public int updateTag(TagDto tagDto) {
        int res = tagMapper.updateById(BeanCopyUtils.copyBean(tagDto, Tag.class));
        return MethodConstant.SUCCESS == res ? MethodConstant.SUCCESS:MethodConstant.ERROR;
    }

    public List<TagVo> allTag() {
        LambdaQueryWrapper<Tag> lqw = new LambdaQueryWrapper<>();
        lqw.select(Tag::getId, Tag::getName);
        List<Tag> tagList = tagMapper.selectList(lqw);
        List<TagVo> tagVoList = BeanCopyUtils.copyBeanList(tagList, TagVo.class);
        return tagVoList;
    }
}

