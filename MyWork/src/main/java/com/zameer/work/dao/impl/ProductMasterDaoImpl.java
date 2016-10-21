package com.zameer.work.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.zameer.work.bean.ProductMaster;
import com.zameer.work.constant.ResponseMessage;
import com.zameer.work.dao.ProductMasterDao;
import com.zameer.work.util.BaseDaoImpl;

@Repository
public class ProductMasterDaoImpl extends BaseDaoImpl implements ProductMasterDao {
	private static final Logger LOG = LoggerFactory.getLogger(ProductMasterDaoImpl.class); 
	
	@Autowired(required = true)
	@Qualifier(value = "hibernate4AnnotatedSessionFactory")
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	@Override
	public String insert(ProductMaster productMaster) {
		try{
			Session session = sessionFactory.getCurrentSession();
			session.save(productMaster);
			return ResponseMessage.SUCCESS.toString();
		}catch(Exception e){
			LOG.error(e.getMessage());
		}
		return ResponseMessage.ERROR.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductMaster> getProductList() {
		List<ProductMaster> productMastersList = new ArrayList<ProductMaster>();
		try{
		Criteria criteria = getCriteria(ProductMaster.class);
		productMastersList = criteria.list();
		}catch(Exception e){
			LOG.error(e.getMessage());
		}
		return productMastersList;
	}

	@Override
	public String update(ProductMaster productMaster) {
		try{
			Session session=this.sessionFactory.getCurrentSession();
			session.update(productMaster);
			LOG.info("user updated successfully!");
			return ResponseMessage.SUCCESS.toString();
		}catch(Exception e){
			LOG.error(e.getMessage());
		}
		return ResponseMessage.ERROR.toString();
	}

	@Override
	public String delete(ProductMaster user) {
		return null;
	}

}
