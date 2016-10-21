'use strict';

myWork.controller('roleController', ['$scope','$rootScope','ApiCallFactory','$location', 'DataFactory', 'Storage','toaster', function ($scope,$rootScope, ApiCallFactory, $location, DataFactory, Storage,toaster) {

	$rootScope.currentuser = null;
	$scope.isLoginFormValid = false;
	$scope.loginFailed = false;
	$scope.currentPage = 1;
	$scope.pageSize = 5;
	$rootScope.pageTitle = 'ADMIN LTE';
	$scope.role = [];
	
	$scope.roles={
			name:""
		};
	
	$scope.addRole = function() {
		ApiCallFactory.addRole($scope.roles).success(function(res,status){
			DataFactory.setShowLoader(false);
			if(status === 200 && res.result == "Success"){
				toaster.success("Success", "Role Added Successfully");
				 $scope.getAllRole();
				$rootScope.homeLogoURL = "#/role";
				$location.replace();
				$location.path('/role');
				
			}else{
				toaster.error("Faild", "Invalid User Id Or Password");
				$scope.loginFailed = true;
				 
			}
		}).error(function(error){
			DataFactory.setShowLoader(false);
			console.log(error);
		});
	};

    $scope.getAllRole = function() {
    		ApiCallFactory.getAllRole().success(function(res,status){
    			DataFactory.setShowLoader(false);
    			if(status === 200 && res.result == "Success"){
    				$scope.role = res.role;
    				angular.forEach(res.role, function(value, key){
    				     console.log(key + ': ' + value);
    				});
    				$rootScope.homeLogoURL = "#/role";
    				$location.replace();
    				$location.path('/role');
    				
    			}else{
    				toaster.error("Faild", "Invalid User Id Or Password");
    				$scope.loginFailed = true;
    				 
    			}
    		}).error(function(error){
    			DataFactory.setShowLoader(false);
    			console.log(error);
    		});
    };
    
    $scope.getAllRole();
}]);
