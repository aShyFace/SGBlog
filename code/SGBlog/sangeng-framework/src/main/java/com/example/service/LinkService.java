package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.PageParams;
import com.example.common.PageResult;
import com.example.domain.dto.LinkAddDto;
import com.example.domain.dto.LinkStatusDto;
import com.example.domain.dto.LinkUpdateDto;
import com.example.domain.vo.LinkPreviewVo;
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

  PageResult<LinkPreviewVo> allLinkPage(PageParams pageParams, String name, String status);

  LinkPreviewVo getLinkById(Long id);

  int addLink(LinkAddDto linkAddDto);

  int updateLinkById(LinkUpdateDto linkUpdateDto);

  int deleteLinkById(Long id);

  int updateLinkStatusById(LinkStatusDto linkStatusDto);
}

