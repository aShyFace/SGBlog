package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.domain.vo.LinkVo;
import com.example.domain.entity.Link;

import java.util.List;

/**
 * 友链(Link)表服务接口
 *
 * @author Zhi
 * @since 2023-03-29 20:41:53
 */
public interface LinkService extends IService<Link> {
    List<LinkVo> getAllLink();

}

