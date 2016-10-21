'use strict';

myWork.factory('RestFactory', ['$http', function($http){
	
	var RestFactory = {};
	
	RestFactory.performPost = function(apiURL, requestData, contentType){
		return $http({
	        method: "POST",
	        url: apiURL,
	        headers : { 'Content-Type': contentType },
	        data:requestData
		});
	};
	
	RestFactory.performGet = function(apiURL){
		return $http({
	        method: "GET",
	        url: apiURL,
		});
	};
	
	
	RestFactory.performPostFile = function(apiURL, requestData){
		return $http({
	        method: "POST",
	        url: apiURL,
	        headers:{'Content-Type':'multipart/form-data'},
	        data:requestData,
	        transformRequest: function(data, headersGetterFunction) {
	            return data; // do nothing! FormData is very good!
	        }
		});
	};
	return RestFactory;
	
}]);
