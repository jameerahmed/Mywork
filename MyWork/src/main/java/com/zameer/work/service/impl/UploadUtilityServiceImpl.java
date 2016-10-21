package com.zameer.work.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zameer.work.dao.UploadUtilityDao;
import com.zameer.work.service.UploadUtilityService;
import com.zameer.work.util.Configuration;

@Service
public class UploadUtilityServiceImpl implements UploadUtilityService {
	@Autowired
	private UploadUtilityDao uploadUtilityDao;
	@Override
	@Transactional
	public Configuration getConfiguration(String string) {
		return uploadUtilityDao.getConfiguration(string);
	}

}
