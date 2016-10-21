package com.zameer.work.dao.impl;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.zameer.work.dao.UploadUtilityDao;
import com.zameer.work.util.BaseDaoImpl;
import com.zameer.work.util.Configuration;
@Repository
public class UploadUtilityDaoImpl extends BaseDaoImpl implements UploadUtilityDao {

	private static final Logger LOG = LoggerFactory.getLogger(UploadUtilityDaoImpl.class);
	@Autowired(required = true)
	@Qualifier(value = "hibernate4AnnotatedSessionFactory")
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public Configuration getConfiguration(String name) {
		LOG.info("inside dao of getConfiguration");
		try{
			String hql="from Configuration conf where conf.name = :name";
			Configuration configuration = (Configuration) sessionFactory.getCurrentSession().createQuery(hql).setParameter("name", name).uniqueResult();
			if(configuration!=null){
				return configuration;
			}
		}catch(Exception e){
			LOG.error("Error occured in UploadUtilityDaoImpl of getConfiguration()!"+e.getMessage());
			return null;
		}
		return null;
	}

}
