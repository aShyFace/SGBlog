package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.PageParams;
import com.example.common.PageResult;
import com.example.constant.LinkConstant;
import com.example.constant.MethodConstant;
import com.example.domain.dto.LinkAddDto;
import com.example.domain.dto.LinkStatusDto;
import com.example.domain.dto.LinkUpdateDto;
import com.example.domain.entity.Link;
import com.example.domain.vo.LinkPreviewVo;
import com.example.domain.vo.LinkVo;
import com.example.mapper.LinkMapper;
import com.example.service.LinkService;
import com.example.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 友链(Link)表服务实现类
 *
 * @author Zhi
 * @since 2023-03-29 20:41:53
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {
    @Resource
    private LinkMapper linkMapper;

    public List<LinkVo> getAllLink() {
        LambdaQueryWrapper<Link> lqw = new LambdaQueryWrapper<>();
        List<Link> linkList = linkMapper.selectList(lqw.eq(Link::getStatus, LinkConstant.LINK_IS_VERIFIED));
        List<LinkVo> linkVoList = BeanCopyUtils.copyBeanList(linkList, LinkVo.class);
        return linkVoList;
    }

    @Override
    public PageResult<LinkPreviewVo> allLinkPage(PageParams pageParams, String name, String status) {
        LambdaQueryWrapper<Link> lqw = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(name)){
            lqw.like(Link::getName, name);
        }
        if (StringUtils.hasText(status)) {
            lqw.eq(Link::getStatus, status);
        }
        //lqw.eq(Link::getStatus, LinkConstant.LINK_STATUS_IS_NORMAL);
        Page<Link> page = new Page(pageParams.getPageNum(), pageParams.getPageSize());
        List<Link> linkList = page(page, lqw).getRecords();

        List<LinkPreviewVo> linkVoList = BeanCopyUtils.copyBeanList(linkList, LinkPreviewVo.class);
        PageResult<LinkPreviewVo> pageResult = new PageResult<>(linkVoList, page.getTotal(), pageParams);
        return pageResult;
    }

    @Override
    public LinkPreviewVo getLinkById(Long id) {
        Link link = linkMapper.selectById(id);
        LinkPreviewVo linkPreviewVo = BeanCopyUtils.copyBean(link, LinkPreviewVo.class);
        return linkPreviewVo;
    }

    @Override
    public int addLink(LinkAddDto linkAddDto) {
        Link link = BeanCopyUtils.copyBean(linkAddDto, Link.class);
        int ret = linkMapper.insert(link);
        return MethodConstant.ERROR != ret ? MethodConstant.SUCCESS:MethodConstant.ERROR;
    }

    @Override
    public int updateLinkById(LinkUpdateDto linkUpdateDto) {
        Link link = BeanCopyUtils.copyBean(linkUpdateDto, Link.class);
        int ret = linkMapper.updateById(link);
        return MethodConstant.ERROR != ret ? MethodConstant.SUCCESS:MethodConstant.ERROR;
    }

    @Override
    public int deleteLinkById(Long id) {
        int ret = linkMapper.deleteById(id);
        return MethodConstant.ERROR != ret ? MethodConstant.SUCCESS:MethodConstant.ERROR;
    }

    @Override
    public int updateLinkStatusById(LinkStatusDto linkStatusDto) {
        Link link = BeanCopyUtils.copyBean(linkStatusDto, Link.class);
        int ret = linkMapper.updateById(link);
        return MethodConstant.ERROR != ret ? MethodConstant.SUCCESS:MethodConstant.ERROR;
    }
}
