package com.ceyentra.oauth.demo.dao;

import com.ceyentra.oauth.demo.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
