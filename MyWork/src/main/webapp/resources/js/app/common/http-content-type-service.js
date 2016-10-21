'use strict';

myWork.factory('HttpContentTypeService', function(){
	
	var HttpContentType = {};
	
	HttpContentType.APPLICATION_JSON = 'application/json';
	HttpContentType.applicationJson = function(){
		return HttpContentType.APPLICATION_JSON;
	};
	HttpContentType.MULTIPART_FORM_DATA = 'multipart/form-data';
	HttpContentType.applicationJson = function(){
		return HttpContentType.APPLICATION_JSON;
	};
	
	return HttpContentType;
	
});
