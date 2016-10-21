package com.zameer.work.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.zameer.work.constant.ResponseMessage;
import com.zameer.work.constant.UrlPath;
import com.zameer.work.service.RoleService;
import com.zameer.work.util.Result;
import com.zameer.work.ws.WSRole;
import com.zameer.work.bean.Role;

@RequestMapping(value = UrlPath.ROLE)
@Controller
public class RoleController {
	
	private static final Logger LOG = LoggerFactory.getLogger(RoleController.class);
	
	@Autowired
	private RoleService roleService;
	
	@Autowired(required = true)
	@Qualifier(value = "mapper")
	private DozerBeanMapper mapper;
	
	@RequestMapping(value = UrlPath.INSERT, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> insert(@RequestBody Role role){
		LOG.info("inside insert method of Role Controller");
		String result = roleService.insert(role);
		Result message = new Result();
		message.setResult(result);
		return new  ResponseEntity<Result>(message,HttpStatus.OK);
		
	}
	
	@RequestMapping(value = UrlPath.DELETE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> delete(@RequestBody Role role){
		LOG.info("inside delete method of Role Controller");
		String result = roleService.delete(role);
		Result message = new Result();
		message.setResult(result);
		return new  ResponseEntity<Result>(message,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = UrlPath.GET_ALL_ROLES, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<WSRole> getUserList(){
		List<Role> roles = new ArrayList<Role>();
		List<WSRole> wsRoleList = new ArrayList<WSRole>();
		WSRole wsRole = new WSRole();
		try{
			
			roles = roleService.getRoleList();
			for(Role role: roles){
				WSRole wsUser =  mapper.map(role, WSRole.class);
				wsRoleList.add(wsUser);
				
			}
			wsRole.setRole(wsRoleList);
			wsRole.setResult(ResponseMessage.SUCCESS);
			return new ResponseEntity<WSRole>(wsRole, HttpStatus.OK);
		}catch(Exception e){
			LOG.error(e.getMessage());
		}
		wsRole.setResult(ResponseMessage.ERROR);
		return new ResponseEntity<WSRole>(wsRole, HttpStatus.OK);
	}

}
