package com.zameer.work.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zameer.work.bean.ProductMaster;
import com.zameer.work.dao.ProductMasterDao;
import com.zameer.work.service.ProductMasterService;

@Service
public class ProductMasterServiceImpl implements ProductMasterService {
	
	private static Logger LOG = LoggerFactory.getLogger(ProductMasterServiceImpl.class);

	@Autowired
	private  ProductMasterDao  productMasterDao;
	@Override
	@Transactional
	public String insert(ProductMaster productMaster) {
		LOG.info("");
		return productMasterDao.insert(productMaster);
	}

	@Override
	@Transactional
	public List<ProductMaster> getProductList() {
		return productMasterDao.getProductList();
	}

	@Override
	@Transactional
	public String update(ProductMaster productMaster) {
		// TODO Auto-generated method stub
		return productMasterDao.update(productMaster);
	}

	@Override
	public String delete(ProductMaster user) {
		// TODO Auto-generated method stub
		return null;
	}

}
