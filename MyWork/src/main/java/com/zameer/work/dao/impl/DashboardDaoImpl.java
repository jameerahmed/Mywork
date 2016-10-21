package com.zameer.work.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.zameer.work.bean.ProductMaster;
import com.zameer.work.bean.User;
import com.zameer.work.constant.CONSTANT;
import com.zameer.work.dao.DashboardDao;
import com.zameer.work.util.BaseDaoImpl;

@Repository
public class DashboardDaoImpl extends BaseDaoImpl implements DashboardDao {

	
	private static final Logger LOG = LoggerFactory.getLogger(DashboardDaoImpl.class);
	@Autowired(required = true)
	@Qualifier(value = "hibernate4AnnotatedSessionFactory")
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Integer getRegisteredUserCount() {
		CONSTANT.TOTALCOUNT=0;
		try{
			Criteria criteria = getCriteria(User.class);
			criteria.setProjection(Projections.count("id"));
			CONSTANT.TOTALCOUNT =Integer.parseInt(criteria.uniqueResult().toString());
		}catch(Exception e){
			LOG.error(e.getMessage());
		}
		return CONSTANT.TOTALCOUNT;
	}

	@Override
	public Integer getTotalProducts() {
		CONSTANT.TOTALCOUNT=0;
		try{
			Criteria criteria = getCriteria(ProductMaster.class);
			criteria.setProjection(Projections.count("id"));
			CONSTANT.TOTALCOUNT =Integer.parseInt(criteria.uniqueResult().toString());
		}catch(Exception e){
			LOG.error(e.getMessage());
		}
		
		return CONSTANT.TOTALCOUNT;
	}

}
