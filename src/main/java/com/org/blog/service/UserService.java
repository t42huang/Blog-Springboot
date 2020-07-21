package com.org.blog.service;

import com.org.blog.po.User;

public interface UserService {
    User checkUser(String username, String password);
}
