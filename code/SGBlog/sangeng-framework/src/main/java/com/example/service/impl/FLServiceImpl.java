package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.constant.LinkConstant;
import com.example.domain.entity.Link;
import com.example.domain.vo.LinkVo;
import com.example.mapper.LinkMapper;
import com.example.service.LinkService;
import com.example.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 友链(Link)表服务实现类
 *
 * @author Zhi
 * @since 2023-03-29 20:41:53
 */
@Service("flinkservice")
public class FLServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {
    @Resource
    private LinkMapper linkMapper;

    public List<LinkVo> getAllLink() {
        LambdaQueryWrapper<Link> lqw = new LambdaQueryWrapper<>();
        List<Link> linkList = linkMapper.selectList(lqw.eq(Link::getStatus, LinkConstant.LINK_IS_VERIFIED));
        List<LinkVo> linkVoList = BeanCopyUtils.copyBeanList(linkList, LinkVo.class);
        return linkVoList;
    }
}
