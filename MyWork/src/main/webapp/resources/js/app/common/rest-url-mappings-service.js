'use strict';

myWork.factory('RestURLMappingService', ['DataFactory','$rootScope', function(DataFactory,$rootScope){
	var RestUrl = {};
	
	RestUrl.authURL = 'authenticate/authenticate';
	RestUrl.getAuthURL = function(){
		return RestUrl.authURL;
	};
	
	//user management
	//add user
	RestUrl.addUserURL = function(){
		return 'user/insert';
	};
	
	//getAllUserUrl
	RestUrl.getAllUserUrl = function(){
		return 'user/getUser';
	};
	
	//get roles
	RestUrl.getAllRoles = function(){
		return 'role/getRoles';
	};
	//add role
	RestUrl.addRoleURL = function(){
		return 'role/insert';
	};
	//map
	RestUrl.getCoordinateURL = function(){
		return 'vehicle/getCoordinates?transporterId='+ DataFactory.getLoggedInUserId();
	};
	
	RestUrl.getAllVehicleURL = function(){
		return 'vehicle/getAllVehicles?transporterId='+ DataFactory.getLoggedInUserId();
	};
	
	//upload file with data
	
	RestUrl.uploadFileWithDataURL = function(){
		return 'user/saveUserDataAndFile';
	};
	
	//product management
	//add product
	RestUrl.addProductURL = function(){
		return 'product/insert';
	};
	//update product
	RestUrl.updateProductURL = function(){
		return 'product/update';
	};
	
	
	//get all product list
	RestUrl.getAllProductURL = function(){
		return 'product/getProductList';
	};
	
	
	//get all product list
	RestUrl.getDashboardCount = function(){
		return 'dashboard/getDashboardCount';
	};
	
	return RestUrl;
	
}]);
