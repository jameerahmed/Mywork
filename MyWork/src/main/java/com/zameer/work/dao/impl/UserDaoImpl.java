package com.zameer.work.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.zameer.work.bean.User;
import com.zameer.work.constant.ResponseMessage;
import com.zameer.work.dao.UserDao;
import com.zameer.work.util.BaseDaoImpl;

@Repository
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

	private static final Logger LOG = LoggerFactory.getLogger(UserDaoImpl.class);
	@Autowired(required = true)
	@Qualifier(value = "hibernate4AnnotatedSessionFactory")
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public String insert(User user) {
		try{
			if(user.getPassword()!=null && user.getPassword()!=""){
				user.setPassword(DigestUtils.md5Hex(user.getPassword()));
			}
			
			Session session = sessionFactory.getCurrentSession();
			session.save(user);
			return ResponseMessage.SUCCESS.toString();
		}catch(Exception e){
			System.out.println(e);
			LOG.error(e.getMessage());
		}
		return ResponseMessage.ERROR.toString();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserList() {
		List<User> usersList = new ArrayList<User>();
		try{
		Criteria criteria = getCriteria(User.class);
		usersList = criteria.list();
		}catch(Exception e){
			LOG.error(e.getMessage());
		}
		return usersList;
	}
	@Override
	public User findByUserId(Integer id) {
		try{
			String hql="from User u where u.id =:id";
			User user = (User) sessionFactory.getCurrentSession().createQuery(hql).setParameter("id", id).uniqueResult();
			LOG.info("find by user email"+user);
			if(user!=null){
				return user;
			}
		}catch(Exception e){
			LOG.error(e.getMessage());
		}
		return null;
	}
	@Override
	public String update(User user) {
		try{
			Session session=this.sessionFactory.getCurrentSession();
			session.update(user);
			LOG.info("user updated successfully!");
			return ResponseMessage.SUCCESS.toString();
		}catch(Exception e){
			LOG.error(e.getMessage());
		}
		return ResponseMessage.ERROR.toString();
	}
	@Override
	public String delete(User user) {
		try{
			Session session = this.sessionFactory.getCurrentSession();
			session.delete(user);
			return ResponseMessage.SUCCESS.toString();
		}catch(Exception e){
			LOG.error(e.getMessage());
		}
		return ResponseMessage.ERROR.toString();
	}

}
