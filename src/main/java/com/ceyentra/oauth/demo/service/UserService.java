package com.ceyentra.oauth.demo.service;

import com.ceyentra.oauth.demo.entity.User;

import java.util.List;

public interface UserService {

    User save(User user);
    List<User> findAll();
    void delete(long id);
}
