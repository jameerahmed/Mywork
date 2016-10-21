'use strict';

myWork.controller('loginController', ['$scope','$rootScope','ApiCallFactory','$location', 'DataFactory', 'Storage','toaster', function ($scope,$rootScope, ApiCallFactory, $location, DataFactory, Storage,toaster) {

	$rootScope.currentuser = null;
	$scope.isLoginFormValid = false;
	$scope.loginFailed = false;
	
	$rootScope.pageTitle = 'ADMIN LTE';
	
	$scope.credentials={
		emailId:"",
		password:""
	};
	 
    $scope.login = function() {
    		
    	if($scope.credentials.emailId && $scope.credentials.password){
    		$scope.isLoginFormValid = true;
    	}
    	
    	if($scope.isLoginFormValid){
    		DataFactory.setShowLoader(true);
    		ApiCallFactory.doLogin($scope.credentials).success(function(res,status){
    			DataFactory.setShowLoader(false);
    			if(status === 200 && res.result == "Success"){
    				Storage.set('userId', res.user.id);
    				Storage.set('useremailId', res.user.emailId);
    				Storage.set('userFirstemailId', res.user.firstemailId);
    				Storage.set('userRoleId', res.user.role.id);
    				/*DataFactory.setLoggedInUseremailId(res.user.emailId);
    				DataFactory.setLoggedInUserId(res.user.id);
    				DataFactory.setLoggedInUserRoleId(res.user.role.id);*/
    				$rootScope.currentuser="Welcome, " + (res.user.emailId);
    				$rootScope.userid=(res.user.id);
    				$rootScope.useremailId= res.user.emailId;
    				$rootScope.homeLogoURL = "#/home";
    				toaster.success("Success", "Login Success");
    				$location.replace();
    				$location.path('/home');
    				
    			}else{
    				toaster.error("Faild", "Invalid User Id Or Password");
    				$scope.loginFailed = true;
    				 
    			}
    		}).error(function(error){
    			DataFactory.setShowLoader(false);
    			console.log(error);
    		});
    	}
    };
}]);
