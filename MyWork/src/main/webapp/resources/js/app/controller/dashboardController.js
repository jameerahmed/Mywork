
'use strict';

myWork.controller('dashboardController', ['$scope','$rootScope','ApiCallFactory','$location', 'DataFactory', 'Storage','toaster', function ($scope,$rootScope, ApiCallFactory, $location, DataFactory, Storage,toaster) {

	$rootScope.currentuser = null;
	$scope.isLoginFormValid = false;
	$scope.loginFailed = false;
	$scope.currentPage = 1;
	$scope.pageSize = 5;
	$rootScope.pageTitle = 'ADMIN LTE';
	$scope.productList = [];
	
	$scope.init = function(){
		$scope.dashboardCount={
				totalProgram:"",
				totalUser:"",
				totalProducts:""
			};
		$scope.getDashboardCount();
	};

    $scope.getDashboardCount = function() {
    		ApiCallFactory.getDashboardCount().success(function(res,status){
    			DataFactory.setShowLoader(false);
    			if(status === 200){
    				$scope.dashboardCount.totalUser = res[0];
    				$scope.dashboardCount.totalProducts = res[1];
    				$rootScope.homeLogoURL = "#/home";
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
    };
    
  //  $scope.getAllRole();
}]);
