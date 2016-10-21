package com.zameer.work.service;

import java.util.List;

import com.zameer.work.bean.User;

public interface UserService {

	String insert(User user);

	List<User> getUserList();

	User findByUserId(Integer id);

	String update(User user);

	String delete(User user);

	String insertFile(User user);

}
