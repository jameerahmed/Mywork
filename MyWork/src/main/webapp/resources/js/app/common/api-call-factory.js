'use strict';

myWork.factory('ApiCallFactory', ['RestFactory', 'RestURLMappingService', 'HttpContentTypeService', function(RestFactory, RestURLMappingService, HttpContentTypeService){
	
	var ApiCallFactory = {};
	
	ApiCallFactory.doLogin = function(credentials){
		return RestFactory.performPost(RestURLMappingService.getAuthURL(), credentials, HttpContentTypeService.APPLICATION_JSON);
	};
	
	
	//user management
	//add user
	ApiCallFactory.addUser = function(user){
		return RestFactory.performPost(RestURLMappingService.addUserURL(),user, HttpContentTypeService.APPLICATION_JSON);
	};
	
	//getAllUser
	ApiCallFactory.getAllUser = function(){
		return RestFactory.performGet(RestURLMappingService.getAllUserUrl());
	};
	
	//map
	ApiCallFactory.getCoordinate = function(credentials){
		return RestFactory.performPost(RestURLMappingService.getCoordinateURL(),credentials, HttpContentTypeService.APPLICATION_JSON);
	};
	//getAllRole
	ApiCallFactory.getAllRole = function(){
		return RestFactory.performGet(RestURLMappingService.getAllRoles());
	};
	
	//add new role
	ApiCallFactory.addRole = function(role){
		return RestFactory.performPost(RestURLMappingService.addRoleURL(),role, HttpContentTypeService.APPLICATION_JSON);
	};
	
	//update data with file
	ApiCallFactory.uploadFileWithData = function(user){
		return RestFactory.performPostFile(RestURLMappingService.uploadFileWithDataURL(),user);
	};
	
	//product managament
	//add product
	ApiCallFactory.addProduct = function(products){
		return RestFactory.performPost(RestURLMappingService.addProductURL(),products, HttpContentTypeService.APPLICATION_JSON);
	};
	//update product
	ApiCallFactory.updateProduct = function(products){
		return RestFactory.performPost(RestURLMappingService.updateProductURL(),products, HttpContentTypeService.APPLICATION_JSON);
	};
	
	//get product list
	ApiCallFactory.getAllProduct = function(){
		return RestFactory.performGet(RestURLMappingService.getAllProductURL());
	};
	
	//get all dashboard count  list
	ApiCallFactory.getDashboardCount = function(){
		return RestFactory.performGet(RestURLMappingService.getDashboardCount());
	};
	
	
	return ApiCallFactory;
}]);