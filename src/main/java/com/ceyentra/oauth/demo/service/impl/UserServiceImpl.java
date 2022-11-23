package com.ceyentra.oauth.demo.service.impl;

import com.ceyentra.oauth.demo.dao.UserDao;
import com.ceyentra.oauth.demo.entity.User;
import com.ceyentra.oauth.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private BCryptPasswordEncoder encoder;

	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		User user = userDao.findByUsername(userId);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
	}

	/*private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}*/

	private List<SimpleGrantedAuthority> getAuthority(User user) {
		System.out.println(user.getRole());
		if (user.getRole().equals("ROLE_ADMIN")){
			return Arrays.asList(new SimpleGrantedAuthority(user.getRole()));
		}
		throw new UsernameNotFoundException("Access Denied");
	}

	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		userDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		userDao.delete(id);
	}

	@Override
    public User save(User user) {
		System.out.println(user);
		user.setPassword(encoder.encode(user.getPassword()));
		System.out.println(user);
		return userDao.save(user);
    }
}
