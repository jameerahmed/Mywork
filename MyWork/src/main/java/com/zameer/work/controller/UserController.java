package com.zameer.work.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.zameer.work.service.UserService;
import com.zameer.work.util.Response;
import com.zameer.work.util.Result;

import java.util.ArrayList;
import java.util.List;


/*import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;*/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequestMapping(value = UrlPath.USER)
@Controller
public class UserController {
	
	
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;


	@RequestMapping(value = UrlPath.INSERT, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> insert(@RequestBody User user){
		LOG.info("inside insert method of User Controller");
		String result1 = userService.insert(user);
		Result error = new Result();
		error.setResult(result1);
		return new  ResponseEntity<Result>(error,HttpStatus.OK);
		
	}
	
	@RequestMapping(value = UrlPath.INSERT_WITH_FILE, method = RequestMethod.POST)
	public ResponseEntity<Result> insertFile(User user){
		LOG.info("inside insert method of User Controller");
		String result1 = userService.insertFile(user);
		Result error = new Result();
		error.setResult(result1);
		return new  ResponseEntity<Result>(error,HttpStatus.OK);
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = UrlPath.GET_USRES, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<? extends Response> getUserList(){
		List<User> userList = new ArrayList<User>();
		try{
			userList = userService.getUserList();
			return (ResponseEntity<? extends Response>) new ResponseEntity<List<User>>(userList, HttpStatus.OK);
		}catch(Exception e){
			LOG.error(e.getMessage());
		}
		Result errorResult = new Result();
		errorResult.setResult("Error");
		return new ResponseEntity<Result>(errorResult, HttpStatus.OK);
	}
	
	@RequestMapping(value = UrlPath.UPDATE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<? extends Result> update(@RequestBody User user){
		Result result = new Result();
		String resultUpdate = ResponseMessage.ERROR;
		User findUserByUserId = userService.findByUserId(user.getId());
		if(findUserByUserId!=null){
			resultUpdate = userService.update(user);
			result.setResult(resultUpdate);
		}else{
			result.setResult(resultUpdate);
			return new ResponseEntity<Result>(result, HttpStatus.OK);
		}
		return new ResponseEntity<Result>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value  = UrlPath.DELETE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<? extends Response> delete(@RequestBody User user){
		Result result = new Result();
		String resultDelete = ResponseMessage.ERROR;
		resultDelete = userService.delete(user);
		result.setResult(resultDelete);
		return new ResponseEntity<Result>(result, HttpStatus.OK);
		
	}
	
	
	/*@RequestMapping(value = "/saveUserDataAndFile", method = RequestMethod.POST)
	@ResponseBody
	public Object saveUserDataAndFile(@RequestBody User user) {
		
		System.out.println("Inside File upload");
		ObjectMapper mapper = new ObjectMapper();
		try {
			Login login = mapper.readValue(userInfo, Login.class);
			 userService.addUser(login);
			System.out.println(login.toString());
		} catch (JsonParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		String rootDirectory = "D:\\testUpload\\";
		System.out.println("Root Directory "+rootDirectory);
		try {
			file.transferTo(new File(rootDirectory  + file.getOriginalFilename()));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}*/

}
