package com.zameer.work.util;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class BaseDaoImpl implements BaseDao{

	@Autowired(required=true)
	@Qualifier(value="hibernate4AnnotatedSessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Criteria getCriteria(@SuppressWarnings("rawtypes") Class clazz) {
		return getCurrentSession().createCriteria(clazz);
	}

}
