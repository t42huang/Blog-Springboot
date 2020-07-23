package com.org.blog.service;

import com.org.blog.po.Tag;

public interface TagService {
    Tag saveTag(Tag tag);

    Tag getTag(Long id);

    Tag updateTag(Long id, Tag tag);

    void deleteTag(Long id);
}
