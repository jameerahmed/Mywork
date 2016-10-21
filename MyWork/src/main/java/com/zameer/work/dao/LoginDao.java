package com.zameer.work.dao;

import com.zameer.work.bean.User;

public interface LoginDao {
	User authenticate(String emailId, String password);
}
