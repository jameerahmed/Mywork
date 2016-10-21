'use strict';

myWork.controller('userController', ['$scope','$rootScope','ApiCallFactory','$location', 'DataFactory', 'Storage','toaster', function ($scope,$rootScope, ApiCallFactory, $location, DataFactory, Storage,toaster) {

	$rootScope.currentuser = null;
	$scope.isLoginFormValid = false;
	$scope.loginFailed = false;
	$scope.currentPage = 1;
	$scope.pageSize = 10;
	
	$rootScope.pageTitle = 'ADMIN LTE';
	$scope.userList = [];
	$scope.role = [];
	$scope.selectedFile = [];
	$scope.init = function(){
		 $scope.getAllUser();
		
	};
	 //var files = $scope.selectedFile[0];
     //$scope.notification.fileName=files.name;
	$scope.users={
			firstName:"",
			lastName:"",
			emailId:"",
			password:"",
			mobileNumber:"",
			state:"",
			city:"",
			address:"",
			status:"",
			role:{
				id:""
				
			}
		};
	
	$scope.addUser = function() {
		var files = $scope.myFile;
		//var files = $scope.selectedFile[0];
		$scope.users.file = files;
		//$scope.users.uploadedLogo = files.name;
		$scope.users.role.id = $scope.users.role.id;
		//ApiCallFactory.addUser($scope.users).success(function(res,status){
		ApiCallFactory.uploadFileWithData($scope.users).success(function(res,status){
			DataFactory.setShowLoader(false);
			if(status === 200 && res.result == "Success"){
				toaster.success("Success", "User Added Successfully");
				 $scope.getAllRole();
				$rootScope.homeLogoURL = "#/newProfile";
				$location.replace();
				$location.path('/newProfile');
				
			}else{
				toaster.error("Faild", "Error Occured");
				$scope.loginFailed = true;
				 
			}
		}).error(function(error){
			DataFactory.setShowLoader(false);
			console.log(error);
		});
	};

    $scope.getAllUser = function() {
    		ApiCallFactory.getAllUser().success(function(res,status){
    			DataFactory.setShowLoader(false);
    			if(status === 200){
    				$scope.userList = res;
    				
    				$rootScope.homeLogoURL = "#/userList";
    				$location.replace();
    				$location.path('/userList');
    				
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
				
			}else{
			}
		}).error(function(error){
			console.log(error);
		});
};
$scope.getAllRole();
    
}]);
/*
myWork.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            
            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);*/
