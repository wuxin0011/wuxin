package com.wuxin.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wuxin.blog.pojo.blog.Comment;
import org.springframework.stereotype.Repository;


@Repository
public interface CommentMapper extends BaseMapper<Comment> {
}