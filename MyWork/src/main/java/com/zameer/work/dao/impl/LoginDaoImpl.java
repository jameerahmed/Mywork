package com.zameer.work.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.zameer.work.bean.User;
import com.zameer.work.dao.LoginDao;
import com.zameer.work.util.BaseDaoImpl;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class LoginDaoImpl extends BaseDaoImpl implements LoginDao {
	
	private static final Logger LOG = LoggerFactory.getLogger(LoginDaoImpl.class);
	@Autowired(required = true)
	@Qualifier(value = "hibernate4AnnotatedSessionFactory")
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public User authenticate(String emailId, String password) {
		
		Session session = sessionFactory.getCurrentSession();
		try{
			String SQL_QUERY =" from User as u where u.emailId=? and u.password=? and status<>?";
	        Query query = session.createQuery(SQL_QUERY);
	        query.setParameter(0,emailId);
	        query.setParameter(1,DigestUtils.md5Hex(password));
	        query.setParameter(2,"DeActive");
	        User list = (User) query.uniqueResult();
	        //query.list() returns empty list if no rows found. Will never return null 
	        if (list!=null) {
	        	return list;
	    	}
        }catch(Exception e){
    		LOG.error(e.getMessage());
    		e.printStackTrace();
    	}
		return null;
	}

}
