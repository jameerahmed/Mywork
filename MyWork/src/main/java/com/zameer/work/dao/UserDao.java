package com.zameer.work.dao;

import java.util.List;

import com.zameer.work.bean.User;

public interface UserDao {

	String insert(User user);

	List<User> getUserList();

	User findByUserId(Integer id);

	String update(User user);

	String delete(User user);

}
