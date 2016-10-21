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

import com.zameer.work.bean.Role;
import com.zameer.work.constant.ResponseMessage;
import com.zameer.work.dao.RoleDao;
import com.zameer.work.util.BaseDaoImpl;

@Repository
public class RoleDaoImpl extends BaseDaoImpl implements RoleDao {

	private static final Logger LOG = LoggerFactory.getLogger(RoleDaoImpl.class); 
	
	@Autowired(required = true)
	@Qualifier(value = "hibernate4AnnotatedSessionFactory")
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	@Override
	public String insert(Role user) {
		try{
			Session session = sessionFactory.getCurrentSession();
			session.save(user);
			return ResponseMessage.SUCCESS.toString();
		}catch(Exception e){
			LOG.error(e.getMessage());
		}
		return ResponseMessage.ERROR.toString();
	}

	@Override
	public List<Role> getUserList() {
		return null;
	}

	@Override
	public String update(Role user) {
		return null;
	}

	@Override
	public String delete(Role role) {
		try{
			Session session = sessionFactory.getCurrentSession();
			session.delete(role);
			return ResponseMessage.SUCCESS.toString();
		}catch(Exception e){
			LOG.error(e.getMessage());
			
		}
		return ResponseMessage.ERROR.toString();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getRoleList() {
		List<Role> roleList = new ArrayList<Role>();
		try{
		Criteria criteria = getCriteria(Role.class);
		roleList = criteria.list();
		}catch(Exception e){
			LOG.error(e.getMessage());
		}
		return roleList;
	}

}
