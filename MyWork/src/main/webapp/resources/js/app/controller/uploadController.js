'use strict';

myWork.controller('uploadController', ['$scope','$rootScope','ApiCallFactory','$location', 'DataFactory', 'Storage','toaster', function ($scope,$rootScope, ApiCallFactory, $location, DataFactory, Storage,toaster) {

	$rootScope.currentuser = null;
	$scope.isLoginFormValid = false;
	$scope.loginFailed = false;
	
	$rootScope.pageTitle = 'ADMIN LTE';
	$scope.role = [];
	
	$scope.roles={
			name:""
		};
	
	
	
	$scope.uploadFileWithData = function() {
		 var files = $scope.selectedFile;
		var file = $scope.myFile;
		
		
		ApiCallFactory.uploadFileWithData(files,$scope.user).success(function(res,status){
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
	
	 $scope.onFileSelect = function ($files) {
         $scope.uploadProgress = 0;
         $scope.selectedFile = $files;
     };

}]);

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
}]);
