package com.zameer.work.util;

import javax.annotation.PostConstruct;

import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.zameer.work.bean.User;
import com.zameer.work.ws.WSUser;


public class DozerMapper extends BeanMappingBuilder {
	@Autowired(required = true)
	@Qualifier(value = "mapper")
	private DozerBeanMapper mapper;

	public DozerMapper() {
	}

	@PostConstruct
	public void initIt() throws Exception {
		mapper.addMapping(this);
	}
	
	@Override
	protected void configure() {
		mapping(User.class, WSUser.class,TypeMappingOptions.mapId("userLazyRole")).exclude("role");
	}
}