'use strict';

myWork.controller('userProfileController', ['$scope', '$location', '$rootScope', 'Storage', 'DataFactory', '$timeout','$route', function ($scope, $location, $rootScope, Storage, DataFactory, $timeout,$route) {
	var url = $location.absUrl();
	var name = url.split("=")[1];
	if(name)
		$location.path('/editUserProfile/'+name);
	$scope.DataFactory = DataFactory;
	
	$rootScope.homeLogoURL = "#";
	
	$rootScope.currentuser = null;
	
	$rootScope.pageTitle = 'TruckRentz - Portal to rent truck';
	
	$scope.logout = function(){
		$rootScope.currentuser = null;
		$rootScope.userName = null;
		DataFactory.setLoggedInUserName("");
		DataFactory.setLoggedInUserId("");
		DataFactory.setLoggedInUserRoleId("");
		Storage.clear();
		angular.element('#welcomeDivId').css({ display: "none" });
		angular.element('#menuDivId').css({ display: "none" });
		$rootScope.homeLogoURL = "#";
		$location.path('/');
	};
	
	$scope.loadUserProfile = function(){
		$location.path('/editUserProfile/currentUser');
	};
	
	$scope.userProfileControllerInit = function(){
		if(Storage.get('userId')){
			DataFactory.setLoggedInUserId(Storage.get('userId'));
			$rootScope.userid = Storage.get('userId');
		}
		if(Storage.get('userFirstName')){
			DataFactory.setLoggedInUserName(Storage.get('userFirstName'));
			$rootScope.currentuser = Storage.get('userFirstName');
			angular.element('#welcomeDivId').css({ display: "block" });
			angular.element('#menuDivId').css({ display: "block" });
		}
		if(Storage.get('userName')){
			$rootScope.userName = Storage.get('userName');
		}
		if(Storage.get('userRoleId')){
			DataFactory.setLoggedInUserRoleId(Storage.get('userRoleId'));
		}
	};
	
	$scope.$on('$routeChangeStart', function(event, next, current) {
        if($rootScope.currentuser || next.$$route.originalPath == '/PrivacyPolicy' || next.$$route.originalPath == '/faq' || next.$$route.originalPath == '/Disclaimer' || next.$$route.originalPath == '/aboutUs' || next.$$route.originalPath == '/contactUs' || next.$$route.originalPath == '/feedback' || next.$$route.originalPath == '/addExternalUser' || next.$$route.originalPath == '/forgotPassword'){
        	var pageName = (next.$$route.originalPath).split('/');
        	$rootScope.pageTitle = "TruckRentz - " + pageName[1];
        }else{
        	$location.path('/');
        }
    });
	
	$scope.newScheduleClick = function(){
		DataFactory.setEditScheduleObj({});
		$route.reload();
	};
	
	$scope.newWishListClick = function(){
		DataFactory.setEditWishListObj({});
		$route.reload();
	};
	
	$scope.newVehicleClick = function(){
		DataFactory.setEditVehicleObj({});
		$route.reload();
	};
}]);