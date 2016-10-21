package com.zameer.work.service;

import com.zameer.work.bean.User;

public interface LoginService {

	User authenticate(String emailId, String password);

}
