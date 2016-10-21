package com.zameer.work.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zameer.work.dao.DashboardDao;
import com.zameer.work.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {

	private static final Logger LOG = LoggerFactory.getLogger(DashboardServiceImpl.class);
	@Autowired
	private DashboardDao dashboardDao;
	
	
	@Override
	@Transactional
	public Integer getRegisteredUserCount() {
		LOG.info("");
		return dashboardDao.getRegisteredUserCount();
	}


	@Override
	@Transactional
	public Integer getTotalProducts() {
		return dashboardDao.getTotalProducts();
	}

}
