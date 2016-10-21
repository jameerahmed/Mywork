angular
		.module('WFApp', [ '720kb.datepicker' ], [ 'ngMessages' ])
		.controller(
				'notificationCtrl',
				function($scope, $filter, $http, $upload, $localStorage ,SweetAlert,toaster) {
					'use strict';
					// $scope.sendNotification=true;
					$scope.notification = {};
					$scope.notificationList = [];
					
					//$scope.notification.createdBy = $localStorage.empId;
					
					$scope.notification.createdBy = $localStorage.empId;
					console.log($scope.notification.createdBy);
					$scope.notification.priority = "low";
					$scope.notification.stakeTypeId = 1;
					//$scope.notification.files = null;
					$scope.selectedDate = "";
					$scope.date = new Date();
					$scope.notification.createdDate = $filter('date')(
							$scope.date, "dd/MM/yyyy");

					/* hide show */
					$scope.create = true;
					$scope.msg =false;
					
					//$scope.viewNotification = true;
					$scope.createNotification = true;// change temporary  from $scope.createNotification = false;
					$scope.view = false;
						
					$scope.optionsList = [ {
						id : 0,
						name : 'Current month'
					}, {
						id : 1,
						name : 'Two month'
					}, {
						id : 2,
						name : 'Current quarter'
					}, {
						id : 3,
						name : 'Six month'
					}, {
						id : 4,
						name : 'Current Year'
					}, {
						id : 5,
						name : 'No validity'
					} ];
					$scope.notification.validity='Current month';
					
				      $scope.getRecipientsList = function(){
				  		   var url = '/SRM/master/recipientslist';
				  		   $http({
				  				   method  :'GET',
				  				   url     :url,
				  				   cache : false
				  		   }).success(function(data){	
				  			   $scope.toOptionsList=data;
				  			  
				  			 }).error(function(data){
				  			   console.log('Error In get recipient List');
				  		   });
				  		   
				      }
				      $scope.getRecipientsList();    
				      					
					$scope.viewCreateForm = function() {
						$scope.create = false;
						$scope.viewNotification = false;
						$scope.view = true;
						$scope.createNotification = true;

					}
					$scope.viewCreateForm();//on load call create notification temporary

					$scope.viewList = function() {
					}


					$scope.setPriority = function() {

						if ($scope.notification.priority == "low") {
							$scope.notification.priority = "high";
							$scope.setGrey = 'setRed';

						} else {
							$scope.notification.priority = "low";
							$scope.setGrey = 'setGrey';
						}

					}

					
					  $scope.model = {};
			            $scope.selectedFile = [];
			            $scope.uploadProgress = 0;
			            
			            
					$scope.addNote = function() {

						$scope.m = $scope.date.getMonth();
						$scope.d = $scope.date.getDate();
						$scope.y = $scope.date.getYear();

						if ($scope.notification.validity == 'No validity') {
							$scope.newDate = null;
						}
						if ($scope.notification.validity == 'Current month') {
							$scope.selectedDate = new Date($scope.date
									.getFullYear(), $scope.date.getMonth() + 1,
									0);

						}
						if ($scope.notification.validity == 'Two month') {
							$scope.selectedDate = new Date($scope.date
									.getFullYear(), $scope.date.getMonth() + 2,
									0);

						}
						if ($scope.notification.validity == 'Current quarter') {
							$scope.selectedDate = new Date($scope.date
									.getFullYear(), $scope.date.getMonth() + 4,
									0);

						}
						if ($scope.notification.validity == 'Six month') {
							$scope.selectedDate = new Date($scope.date
									.getFullYear(), $scope.date.getMonth() + 6,
									0);

						}
						if ($scope.notification.validity == 'Current Year') {
							$scope.selectedDate = new Date($scope.date
									.getFullYear(),
									$scope.date.getMonth() + 12, 0);
						}

						$scope.newDate = $filter('date')($scope.selectedDate,"dd/MM/yyyy");
						$scope.notification.validity = $scope.newDate;
						
						var url1;
						url1 = '/SRM/dashboard/savenotificationwithphoto';
					    var data1 =$scope.notification;
						/**************test code****/
					      
					
					    console.log($scope.notification);
					   // 
		                var files = $scope.selectedFile[0];
		                $scope.notification.fileName=files.name;
		                $scope.upload = $upload.upload({
		                    url: '/SRM/dashboard/savenotificationwithphoto',
		                    method: 'POST',
		                    data:angular.toJson( $scope.notification),
		                    file: files
		                }).success(function (data) {
		                    //do something
		                	console.log(data);
		                	alert(data)
		                });
					    /**************test code  End****/
					      
						/*console.log(JSON.stringify(data1));
						$upload.upload({
							url: '/SRM/dashboard/savenotificationwithphoto',
				               //  file:  $scope.notification,
				                 method: 'POST',
				                 headers: {'Content-Type': 'multipart/form-data'}, // only for html5
				                 fileName: $scope.notification.fileName,
				                 data: $scope.notification
						 	}).success(function(data) {	
								if (data !== null) {
									if (data.status == 'success') {
										toaster.pop('success','Message sent successfully');
										$scope.notification.to = '';
										$scope.notification.validity='Current month';
										$scope.notification.desc = '';
										$scope.msg =false;
									} else {
										toaster.pop('error','Failed to sent message');
									}
									//$scope.reset();
									//$scope.notification.validity='Current month';
								}
								$scope.createNotification = true;

			 
						 	}).error(function(data){
						 		  console.log('Error In  save noti'+data	);
						 	})
*/						/*
						$scope.notification.to = '';
						$scope.notification.validity = '';
						$scope.notification.desc = '';*/
						//$scope.notification.files = null;
						/*$("#photofileSel").val('');
						$scope.msg =false;*/
					
					};
					
					/* upload image*/
					$scope.uploadedFile = function(files) {
						console.log(files);
					
					}
					
					$scope.onFileSelect1 = function($files) {
						$scope.notification.files = $files[0];
					};
					
					
					/*$scope.readImageUrl = function() {
						  var input = document.getElementById("fileSel");
						  if (input.files && input.files[0]) {
						   var xhr = new XMLHttpRequest();
						   xhr.open('GET', input.files[0], true);
						   xhr.responseType = 'blob';
						   xhr.onload = function(e) {
						    localSrc = URL.createObjectURL(this.response);
						   };
						   xhr.send();
						   var reader = new FileReader();
						   reader.onload = function(e) {
						    $('#profilePic').attr('src', e.target.result);
						   }
						   reader.readAsDataURL(input.files[0]);
						  }
						 }
					*/

					
					
					/* get notification list */
					$scope.getNotificationList = function() {
						$scope.view = false;
						$scope.create = true;
						$scope.viewNotification = true;
						$scope.createNotification = false;
						var url = 'dashboard/notificationlist?empId='+76;
						$http({
							method : 'GET',
							url : url,
							cache : false
						}).success(function(data) {
							$scope.notificationList = data;
						}).error(function(data) {
							console.log('Error In get notification  List');
						});
					}
					//$scope.getNotificationList();// comment tem[porary

					$scope.reset = function() {
						$scope.notification.to = '';
						//$scope.notification.validity = '';
						$scope.notification.desc = '';
						$scope.notification.priority = "low";
						$scope.setGrey = 'setGrey';
						$scope.create = true;
						$scope.viewNotification = false;//temporary chage true to false
						$scope.createNotification = true;//temporary chnage false to true
						$scope.view = false;
					}
					
					
					
				
					
					 
					  
			            
			       /*     $scope.uploadFile = function () {
			            	console.log($scope.notification);
			                var file = $scope.selectedFile[0];
			                $scope.upload = $upload.upload({
			                    url: '/SRM/dashboard/savenotificationwithphoto',
			                    method: 'POST',
			                    data: $scope.notification,
			                    file: file
			                }).success(function (data) {
			                    //do something
			                });
			            };*/

			            $scope.onFileSelect = function ($files) {
			                $scope.uploadProgress = 0;
			                $scope.selectedFile = $files;
			            };
			
					
				
})