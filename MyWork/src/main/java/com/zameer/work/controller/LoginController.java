package com.zameer.work.controller;


import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zameer.work.bean.User;
import com.zameer.work.constant.ResponseMessage;
import com.zameer.work.constant.UrlPath;
import com.zameer.work.service.LoginService;
import com.zameer.work.ws.Authenticate;
import com.zameer.work.ws.WSUser;

@Controller
@RequestMapping(value = UrlPath.AUTHENTICATE)
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired(required = true)
	@Qualifier(value = "mapper")
	private DozerBeanMapper mapper;

	private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value = UrlPath.AUTHENTICATE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Authenticate> authenticate(@RequestBody User user) {
		Authenticate authenticate = new Authenticate();
		LOG.debug("userName " + user.getEmailId());
		
		User userExists = loginService.authenticate(user.getEmailId(), user.getPassword());
		if(userExists!=null){
			WSUser wsUser = mapper.map(userExists, WSUser.class);
			authenticate.setUser(wsUser);
			authenticate.setResult(ResponseMessage.SUCCESS);
			return new ResponseEntity<Authenticate>(authenticate, HttpStatus.OK);
		}
		
		authenticate.setResult(ResponseMessage.ERROR);
		return new ResponseEntity<Authenticate>(authenticate, HttpStatus.OK);
		
	}

}
