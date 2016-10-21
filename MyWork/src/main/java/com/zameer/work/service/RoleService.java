package com.zameer.work.service;

import java.util.List;

import com.zameer.work.bean.Role;

public interface RoleService {
	String insert(Role user);
	List<Role> getUserList();
	String update(Role user);
	String delete(Role user);
	List<Role> getRoleList();


}
