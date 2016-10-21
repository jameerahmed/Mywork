package com.zameer.work.dao;

import java.util.List;

import com.zameer.work.bean.Role;

public interface RoleDao {
	String insert(Role user);
	List<Role> getUserList();
	String update(Role user);
	String delete(Role user);
	List<Role> getRoleList();

}
