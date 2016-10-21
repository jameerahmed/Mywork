'use strict';

myWork.factory('DataFactory', function(){
	
	var DataFactory = {};
	
	DataFactory.loggedInUserName = "";
	DataFactory.setLoggedInUserName = function(loggedInUserName){
		DataFactory.loggedInUserName = loggedInUserName;
	};
	DataFactory.getLoggedInUserName = function(){
		return DataFactory.loggedInUserName;
	};
	
	DataFactory.loggedInUserId = "";
	DataFactory.setLoggedInUserId = function(loggedInUserId){
		DataFactory.loggedInUserId = loggedInUserId;
	};
	DataFactory.getLoggedInUserId = function(){
		return DataFactory.loggedInUserId;
	};
	
	DataFactory.loggedInUserRoleId = "";
	DataFactory.setLoggedInUserRoleId = function(loggedInUserRoleId){
		DataFactory.loggedInUserRoleId = loggedInUserRoleId;
	};
	DataFactory.getLoggedInUserRoleId = function(){
		return DataFactory.loggedInUserRoleId;
	};
	
	DataFactory.editDriverUser = "";
	DataFactory.setEditDriverUser = function(editDriverUser){
		DataFactory.editDriverUser = editDriverUser;
	};
	DataFactory.getEditDriverUser = function(){
		return DataFactory.editDriverUser;
	};
	
	DataFactory.vehicleList = "";
	DataFactory.setVehicleList = function(list){
		DataFactory.vehicleList = list;
	};
	DataFactory.getVehicleList = function(){
		return DataFactory.vehicleList;
	};
	
	DataFactory.vehicleId = "";
	DataFactory.setVehicleId = function(id){
		DataFactory.vehicleId = id;
	};
	DataFactory.getVehicleId = function(){
		return DataFactory.vehicleId;
	};
	
	DataFactory.editVehicleObj = {};
	DataFactory.setEditVehicleObj = function(editVehicleObj){
		DataFactory.editVehicleObj = editVehicleObj;
	};
	DataFactory.getEditVehicleObj = function(){
		return DataFactory.editVehicleObj;
	};
	
	//Populated when customer make a request for schedule
	DataFactory.customerSearchSchedule = {};
	DataFactory.setCustomerSearchSchedule = function(customerSearchSchedule){
		DataFactory.customerSearchSchedule = customerSearchSchedule;
	};
	DataFactory.getCustomerSearchSchedule = function(){
		return DataFactory.customerSearchSchedule;
	};
	
	DataFactory.searchWishlist = {};
	DataFactory.setSearchWishlist = function(searchWishlist){
		DataFactory.searchWishlist = searchWishlist;
	};
	DataFactory.getSearchWishlist = function(){
		return DataFactory.searchWishlist;
	};
	
	DataFactory.checkValidNumber = function($event){
		if ($.inArray($event.keyCode, [46, 8, 9, 27, 13]) !== -1 ||
	             // Allow: Ctrl+A
	            ($event.keyCode == 65 && $event.ctrlKey === true) ||
	             // Allow: Ctrl+C
	            ($event.keyCode == 67 && $event.ctrlKey === true) ||
	             // Allow: Ctrl+X
	            ($event.keyCode == 88 && $event.ctrlKey === true) ||
	             // Allow: home, end, left, right
	            ($event.keyCode >= 35 && $event.keyCode <= 39)) {
	                 // let it happen, don't do anything
	                 return;
	        }
	        // Ensure that it is a number and stop the keypress
	        if (($event.shiftKey || ($event.keyCode < 48 || $event.keyCode > 57)) && ($event.keyCode < 96 || $event.keyCode > 105 || $event.keyCode == 110 || $event.keyCode == 190)) {
	            $event.preventDefault();
	        }
	};
	
	DataFactory.editWishListObj = {};
	DataFactory.setEditWishListObj = function(editWishListObj){
		DataFactory.editWishListObj = editWishListObj;
	};
	DataFactory.getEditWishListObj = function(){
		return DataFactory.editWishListObj;
	};
	
	DataFactory.editScheduleObj = {};
	DataFactory.setEditScheduleObj = function(editScheduleObj){
		DataFactory.editScheduleObj = editScheduleObj;
	};
	DataFactory.getEditScheduleObj = function(){
		return DataFactory.editScheduleObj;
	};
	
	
	DataFactory.showLoader = false;
	DataFactory.setShowLoader = function(showLoader){
		DataFactory.showLoader = showLoader;
	};
	DataFactory.getShowLoader = function(){
		return DataFactory.showLoader;
	};
	
	DataFactory.tripDetailsObj = {};
	DataFactory.setTripDetailsObj = function(tripDetailsObj){
		DataFactory.tripDetailsObj = tripDetailsObj;
	};
	DataFactory.getTripDetailsObj = function(){
		return DataFactory.tripDetailsObj;
	};
	
	
	DataFactory.infoTransporterDetailsObj = {};
	DataFactory.setInfoTransporterDetailsObj = function(infoTransporterDetailsObj){
		DataFactory.infoTransporterDetailsObj = infoTransporterDetailsObj;
	};
	DataFactory.getInfoTransporterDetailsObj = function(){
		return DataFactory.infoTransporterDetailsObj;
	};
	return DataFactory;
});
