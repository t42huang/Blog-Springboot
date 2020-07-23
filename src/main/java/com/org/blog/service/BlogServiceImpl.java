package com.org.blog.service;

import com.org.blog.NotFoundException;
import com.org.blog.dao.BlogRepository;
import com.org.blog.po.Blog;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.Date;

@Service
public class BlogServiceImpl implements BlogService {
//
//    @Autowired
//    private BlogRepository blogRepository;
//
//    @Transactional
//    @Override
//    public Blog getBlog(Long id) {
//        return blogRepository.getOne(id);
//    }
//
//    @Transactional
//    @Override
//    public Page<Blog> listBlog(Pageable pageable, Blog blog) {
//        return null;
//    }
//
//    @Transactional
//    @Override
//    public Blog saveBlog(Long is) {
//        return null;
//    }
//
//    @Transactional
//    @Override
//    public Blog updateBlog(Long id, Blog blog) {
//        Blog b = blogRepository.getOne(id);
//
//        return blogRepository.save(b);
//    }
//
//    @Override
//    public void deleteBlog(Long id, Blog blog) {
//
//    }


}
