package com.zameer.work.constant;

public final class UrlPath {
	
	//common path used to insert update delete functionality
	public static final String INSERT = "/insert";
	public static final String UPDATE = "update";
	public static final String DELETE = "delete";
	
	//user url
	public static final String USER = "user";
	public static final String GET_USRES= "getUser";
	
	//role url
	public static final String ROLE = "role";
	public static final String GET_ALL_ROLES = "getRoles";
	
	//login 
	public static final String AUTHENTICATE = "/authenticate";
	
	
	//product path
	public static final String PRODUCT = "/product";
	public static final String GET_PRODUCT_LIST = "/getProductList";
	
	
	//dashboard path
	public static final String DASHBOARD = "/dashboard";
	public static final String GET_DASHBOARD_COUNT = "/getDashboardCount";
	
	
	//file upload
	public static final String INSERT_WITH_FILE = "/saveUserDataAndFile";

}
