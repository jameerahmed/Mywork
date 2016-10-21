package com.zameer.work.util;

import org.hibernate.Criteria;
import org.hibernate.Session;

public interface BaseDao {
	/**
	 * This method is used to get database session 
	 * @return
	 */
	Session getCurrentSession();
	/**
	 * This method is used to create criteria for class
	 * @param clazz
	 * @return
	 */
	Criteria getCriteria(@SuppressWarnings("rawtypes") Class clazz);
}
