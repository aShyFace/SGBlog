package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Link;

/**
 * 友链(Link)表数据库访问层
 *
 * @author Zhi
 * @since 2023-03-29 20:41:53
 */
@Mapper
public interface LinkMapper extends BaseMapper<Link> {

}

