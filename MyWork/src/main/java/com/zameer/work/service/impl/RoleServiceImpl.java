package com.zameer.work.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zameer.work.bean.Role;
import com.zameer.work.dao.RoleDao;
import com.zameer.work.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;
	
	@Override
	@Transactional
	public String insert(Role role) {
		return roleDao.insert(role);
	}

	@Override
	@Transactional
	public List<Role> getUserList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public String update(Role role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public String delete(Role role) {
		// TODO Auto-generated method stub
		return roleDao.delete(role);
	}

	@Override
	@Transactional
	public List<Role> getRoleList() {
		// TODO Auto-generated method stub
		return roleDao.getRoleList();
	}

}
