package com.zameer.work.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zameer.work.bean.User;
import com.zameer.work.dao.LoginDao;
import com.zameer.work.service.LoginService;
@Service
public class LoginServiceImpl implements LoginService {

	
	@Autowired
	private LoginDao loginDao;
	@Override
	@Transactional
	public User authenticate(String emailId, String password) {
		return loginDao.authenticate(emailId, password);
	}

}
