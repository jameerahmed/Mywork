package com.zameer.work.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zameer.work.bean.User;
import com.zameer.work.constant.FilePath;
import com.zameer.work.dao.UserDao;
import com.zameer.work.service.UserService;
import com.zameer.work.util.UploadUtility;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Resource(name = "uploadUtility")
	private UploadUtility uploadUtility;
	@Override
	@Transactional
	public String insert(User user) {
		//String fileName = file.getOriginalFilename();
		// Boolean isSaved = uploadUtility.saveFile(FilePath.UPLOADPHOTOPATH,file.getOriginalFilename(),file);
		// if(isSaved){
			 return userDao.insert(user);
			 //} else return null;
		
		
	}
	@Override
	@Transactional
	public List<User> getUserList() {
		return userDao.getUserList();
	}
	@Override
	@Transactional
	public User findByUserId(Integer id) {
		// TODO Auto-generated method stub
		return userDao.findByUserId(id);
	}
	@Override
	@Transactional
	public String update(User user) {
		return userDao.update(user);
	}
	@Override
	@Transactional
	public String delete(User user) {
		return userDao.delete(user);
	}
	@Override
	public String insertFile(User user) {
		// fileName = user.getFiles().getOriginalFilename();
				 user.setUploadedLogo(user.getFile().getOriginalFilename());
				 Boolean isSaved = uploadUtility.saveFile(FilePath.UPLOAD_PHOTO_PATH,user.getFile().getOriginalFilename(),user.getFile());
				 if(isSaved){
					 return userDao.insert(user);
					 } else return null;
	}

}
