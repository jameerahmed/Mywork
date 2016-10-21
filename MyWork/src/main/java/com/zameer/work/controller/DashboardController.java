package com.zameer.work.controller;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zameer.work.constant.CONSTANT;
import com.zameer.work.constant.UrlPath;
import com.zameer.work.service.DashboardService;

@RequestMapping(value = UrlPath.DASHBOARD)
@Controller
public class DashboardController {

private static final Logger LOG = LoggerFactory.getLogger(DashboardController.class);	
	
	@Autowired
	private DashboardService dashboardService;
	
	@RequestMapping(value = UrlPath.GET_DASHBOARD_COUNT, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> getDashboardCount(){
		List<Integer> dashboardCount = new ArrayList<Integer>();
		try{
			CONSTANT.TOTALCOUNT = dashboardService.getRegisteredUserCount();
			dashboardCount.add(CONSTANT.TOTALCOUNT);
			CONSTANT.TOTALCOUNT = dashboardService.getTotalProducts();
			dashboardCount.add(CONSTANT.TOTALCOUNT);
			return new ResponseEntity<Object>(dashboardCount,HttpStatus.OK);
			
		}catch(Exception e){
			LOG.error(e.getMessage());
		}
		return new ResponseEntity<Object>(dashboardCount,HttpStatus.OK);
	}
}
