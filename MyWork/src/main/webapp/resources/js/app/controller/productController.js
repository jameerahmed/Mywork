
'use strict';

myWork.controller('productController', ['$scope','$rootScope','ApiCallFactory','$location', 'DataFactory', 'Storage','toaster', function ($scope,$rootScope, ApiCallFactory, $location, DataFactory, Storage,toaster) {

	$rootScope.currentuser = null;
	$scope.isLoginFormValid = false;
	$scope.loginFailed = false;
	$scope.currentPage = 1;
	$scope.pageSize = 5;
	$rootScope.pageTitle = 'ADMIN LTE';
	$scope.productList = [];
	$scope.product={
			name:"",
			price:"",
			description:""
		};
	$scope.init = function(){
		$scope.getAllProduct();
	};
	$scope.addProduct = function(){
		ApiCallFactory.addProduct($scope.product).success(function(res,status){
			DataFactory.setShowLoader(false);
			if(status === 200 && res.result == "Success"){
				toaster.success("Success", "Product Added Successfully");
				// $scope.getAllRole();
				$rootScope.homeLogoURL = "#/addProduct";
				$location.replace();
				$location.path('/addProduct');
				
			}else{
				toaster.error("Faild", "Error Occured Try Some Time");
				$scope.loginFailed = true;
				 
			}
		}).error(function(error){
			DataFactory.setShowLoader(false);
			console.log(error);
		});
	};
	//update product
		$scope.updateProduct = function(data,id){
			$scope.ProductMaster={
					id:id,
					name:data.name,
					description:data.description
				};
		ApiCallFactory.updateProduct($scope.ProductMaster).success(function(res,status){
			DataFactory.setShowLoader(false);
			if(status === 200 && res.result == "Success"){
				toaster.success("Success", "Product Updated Successfully");
				// $scope.getAllRole();
				$rootScope.homeLogoURL = "#/productList";
				$location.replace();
				$location.path('/productList');
				
			}else{
				toaster.error("Faild", "Invalid User Id Or Password");
				$scope.loginFailed = true;
				 
			}
		}).error(function(error){
			DataFactory.setShowLoader(false);
			console.log(error);
		});
	};

    $scope.getAllProduct = function() {
    		ApiCallFactory.getAllProduct().success(function(res,status){
    			DataFactory.setShowLoader(false);
    			if(status === 200 && res.result == "Success"){
    				$scope.productList = res.productMasters;
    				angular.forEach(res.role, function(value, key){
    				     console.log(key + ': ' + value);
    				});
    				$rootScope.homeLogoURL = "#/productList";
    				$location.replace();
    				$location.path('/productList');
    				
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
