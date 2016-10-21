var myWork = angular.module('MyWork', ['ngRoute','angularUtils.directives.dirPagination','ui.grid.pagination','xeditable','toaster']);
myWork.config(function($routeProvider) {
	
	  $routeProvider.when('/', {
			templateUrl : 'resources/views/examples/login.html'
	  }),
	  $routeProvider.when('/home', {
			templateUrl : 'resources/views/landing/landing.html',
	  });
	  $routeProvider.when('/role', {
			templateUrl : 'resources/views/forms/role.html',
	  });
	  $routeProvider.when('/userList', {
			templateUrl : 'resources/views/forms/userList.html',
	  });
	  $routeProvider.when('/newProfile', {
			templateUrl : 'resources/views/forms/newProfile.html',
	  });
	  
	  //product management
	  $routeProvider.when('/addProduct', {
			templateUrl : 'resources/views/forms/addProduct.html',
	  });
	  $routeProvider.when('/productList', {
			templateUrl : 'resources/views/forms/productList.html',
	  });
	  $routeProvider.when('/uploadFiles', {
			templateUrl : 'resources/views/fileUpload.jsp',
	  });
	  
  });