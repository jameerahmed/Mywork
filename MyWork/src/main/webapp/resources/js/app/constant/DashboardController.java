package com.controller.dashboard;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bo.dashboard.DashboardBO;
import com.model.dashboard.Notification;
import com.model.dashboard.NotifyUser;
import com.model.dashboard.Reminder;
import com.model.login.Employee;
import com.services.factory.Factory;
import com.util.Base64;
import com.util.Constants;
import com.util.FileUtility;
import com.util.gcm.PushSender;


@RequestMapping(value = "/dashboard")
@Controller
public class DashboardController {
	/**
     * Below code for initialize log4j for this class
     */
    private static final Logger LOGGER = Logger.getLogger(DashboardController.class);
    
    /**
     * To save notification details with photo
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    
    @RequestMapping(value = "/savenotificationwithphoto", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> saveNotificationDetailsWithPhoto(
    		@RequestParam("file") MultipartFile file,
    		HttpServletRequest request,
					HttpServletResponse response) throws Exception {
		LOGGER.info("Inside saveNotificationDetails method");
		try {

			System.out.println(file);
			DashboardBO dashboardbo = Factory.getInstance().getInstanceService(DashboardBO.class);
			String toStr = "", desc = "", fileName = "", validityStr = "", stakeType = "", priority = "";
			Date validity = null;
			JSONObject obj = new JSONObject();
			String[] toArray = null;
			if ((request.getParameter("to") != null) && (request.getParameter("to").isEmpty() == false)) {
				toStr = request.getParameter("to");
				toArray = toStr.split(",");
			}

			int roleId = 0;
			if ((request.getParameter("stakeTypeId") != null)
					&& (request.getParameter("stakeTypeId").isEmpty() == false)) {
				stakeType = request.getParameter("stakeTypeId");
				roleId = Integer.parseInt(stakeType);
			}

			long createdBy = 0;
			if ((request.getParameter("createdBy") != null) && (request.getParameter("createdBy").isEmpty() == false)
					&& (!request.getParameter("createdBy").equals("undefined"))) {
				String createdByStr = request.getParameter("createdBy");
				createdBy = Long.parseLong(createdByStr);
			}

			if ((request.getParameter("desc") != null) && (request.getParameter("desc").isEmpty() == false)) {
				desc = request.getParameter("desc");
			}

			if ((request.getParameter("priority") != null) && (request.getParameter("priority").isEmpty() == false)) {
				priority = request.getParameter("priority");
			}

			if ((request.getParameter("fileName") != null) && (request.getParameter("fileName").isEmpty() == false)) {
				fileName = request.getParameter("fileName");
			}

			Date dateValidity = null;
			if ((request.getParameter("validity") != null) && (request.getParameter("validity").isEmpty() == false)) {
				validityStr = request.getParameter("validity");
				DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
				if (!validityStr.isEmpty()) {
					dateValidity = format.parse(validityStr);
				}
			}
			
			String dataRepositoryPath = Constants.getApplicationResourceValue("ExternalRepository.FileSystem.Location");
			String notifPath = Constants.getApplicationResourceValue("ExternalRepository.FileSystem.Location.notif");
			String dataRepositorycompressedPath=Constants.getApplicationResourceValue("ExternalRepository.FileSystem.Location.thumbnailpath");
			
			String seperatorChar = new String(new char[] { File.separatorChar });
			String realPath = request.getRealPath(new String(new char[] { File.separatorChar }));
			String webapprealPath = realPath.substring(0, realPath.indexOf("webapps") + 8);
			String outputFolderPath1 = webapprealPath + dataRepositoryPath + seperatorChar + notifPath + seperatorChar;
            String outputthumbnailPath1=webapprealPath + dataRepositoryPath + seperatorChar + dataRepositorycompressedPath + seperatorChar;
            
			String pathfolder = "", pathumbnail="",outputFolderPath = null,outputthumbnailPath=null;
			
			//to save attachment path
			File filePath = null;
			if (fileName.isEmpty() == false) {
				pathfolder = "/" + dataRepositoryPath + "/" + notifPath + "/" + fileName;
				outputFolderPath = pathfolder;
				filePath = new File(outputFolderPath1 + fileName);
			}
			
			//to save thumbnail path
			File filethumb = null;
			if (fileName.isEmpty() == false) {
				pathumbnail = "/" + dataRepositorycompressedPath + "/" + outputthumbnailPath1 + "/" + fileName;
				outputthumbnailPath = pathumbnail;
				filethumb = new File(outputthumbnailPath1 + fileName);
			}
		   
			
			Notification nf = null;
			if (((null != filePath) && (filePath.exists()))||((null!=filethumb)&&(filethumb.exists()))){

				obj.put("flag", "File Already Exists With The Same Name, " + fileName);
				return new ResponseEntity<String>(obj.toString(), HttpStatus.OK);
			} else {

				FileUtility f1 = new FileUtility();
				if (!new File(outputFolderPath1).exists()) {
					f1.createDir(outputFolderPath1);
				}
				
				FileUtility f2 = new FileUtility();
				if (!new File(outputthumbnailPath1).exists()) {
					f2.createDir(outputthumbnailPath1);
				}

				if (fileName != null && fileName.isEmpty() == false) {

					int dot = fileName.lastIndexOf('.');
					String base = (dot == -1) ? fileName : fileName.substring(0, dot);
					String extension = (dot == -1) ? "" : fileName.substring(dot + 1);

					if (extension.equals("mp4") || extension.equals("pdf") || extension.equals("wmv")
							|| extension.equals("csv") || extension.equals("txt") || extension.equals("ogg")
							|| extension.equals("ogv") || extension.equals("webm")) {
						FileOutputStream fileOutputStream = new FileOutputStream(filePath);
						fileOutputStream.write(file.getBytes());
						fileOutputStream.flush();
						fileOutputStream.close();
					} else {
						FileOutputStream fin = new FileOutputStream(filePath);
						fin.write(file.getBytes());
						fin.close();
					}
				}

				ArrayList<String> pushEmpList = new ArrayList<String>();
				String pushId;
				
				Notification n = new Notification();
				n.setAttachment(outputFolderPath);
				n.setDescription(desc);
				n.setValidity(dateValidity);
				n.setPriority(priority);
                n.setCreatedDate(new Date());
                
				Employee emp = null;
				if (createdBy > 0) {
					emp = new Employee();
					emp.setEmpId(createdBy);
				}

				n.setCreatedBy(emp);
				n = dashboardbo.saveNotificationData(n);

				if(toArray!=null){
				for (int i = 0; i < toArray.length; i++) {
					String empId = "";
					String groupId = "";

					if (toArray[i].contains("e")) {
						empId = toArray[i].replace("e", "");
					} else if (toArray[i].contains("g")) {
						groupId = toArray[i].replace("g", "");
					}

					if (!empId.isEmpty() && empId != null && !empId.equals("")) {
						pushId = dashboardbo.getPushByEmpId(Long.parseLong(empId));
						if (!pushId.isEmpty()) {
							pushEmpList.add(pushId);
						}

						NotifyUser nu = new NotifyUser();
						nu.setEmpId(Long.parseLong(empId));
						nu.setNotifId(n.getId());
						nu.setUnreadFlag(true);
						nu = dashboardbo.saveNotificationMapping(nu);
					}

					if (!groupId.isEmpty() && groupId != null && !groupId.equals("")) {
						List<Long> employeeList = dashboardbo.getEmpByGroupId(Long.parseLong(groupId));
						for (Long eId : employeeList) {
							pushId = dashboardbo.getPushByEmpId(eId);
							if (!pushId.isEmpty()) {
								pushEmpList.add(pushId);
							}

							NotifyUser nu = new NotifyUser();
							nu.setEmpId(eId);
							nu.setNotifId(n.getId());
							nu.setUnreadFlag(true);
							nu = dashboardbo.saveNotificationMapping(nu);
						}

					}
				  }
				}
				PushSender ps = new PushSender();
				Date date = new Date();
				String mes = "New notification";
				if (pushEmpList.size() > 0) {
					ps.sendPushMessageToList(pushEmpList, mes, desc, date.toString());
				}
				if (n.getId() > 0) {
					obj.put("status", "success");
				} else {
					obj.put("status", "failure");
				}
			}
			String res = obj.toString();
			LOGGER.info("Exit from saveNotificationDetails method");
			return new ResponseEntity<String>(res, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Error in saveNotificationDetails method");
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * to save notification without photo
	 * 
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/savenotification", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> saveNotificationDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LOGGER.info("Inside saveNotificationDetails method");
		try {
			JSONObject obj = new JSONObject();
			DashboardBO dashboardbo = Factory.getInstance().getInstanceService(DashboardBO.class);

			String toStr = "", desc = "", fileName = "", validityStr = "", stakeType = "", priority = "";

			long createdBy = 0;
			if ((request.getParameter("createdBy") != null) && (request.getParameter("createdBy").isEmpty() == false)
					&& (!request.getParameter("createdBy").equals("undefined"))) {
				String createdByStr = request.getParameter("createdBy");
				createdBy = Long.parseLong(createdByStr);
			}

			String[] toArray = null;
			if ((request.getParameter("to") != null) && (request.getParameter("to").isEmpty() == false)) {
				toStr = request.getParameter("to");
				toArray = toStr.split(",");
			}

			if ((request.getParameter("desc") != null) && (request.getParameter("desc").isEmpty() == false)) {
				desc = request.getParameter("desc");
			}

			if ((request.getParameter("priority") != null) && (request.getParameter("priority").isEmpty() == false)) {
				priority = request.getParameter("priority");
			}

			DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
			Date dateValidity = null;
			if ((request.getParameter("validity") != null) && (request.getParameter("validity").isEmpty() == false)) {
				validityStr = request.getParameter("validity");
				dateValidity = format.parse(validityStr);
			}

			Date createdDate = null;
			if ((request.getParameter("createdDate") != null)
					&& (request.getParameter("createdDate").isEmpty() == false)) {
				String createdDateStr = request.getParameter("createdDate");
				createdDate = format.parse(createdDateStr);
			}

			ArrayList<String> pushEmpList = new ArrayList<String>();
			String pushId;

			Notification n = new Notification();
			n.setDescription(desc);
			n.setValidity(dateValidity);
			n.setPriority(priority);
            n.setCreatedDate(new Date());
			Employee emp = null;
			if (createdBy > 0) {
				emp = new Employee();
				emp.setEmpId(createdBy);
			}

			n.setCreatedBy(emp);
			n = dashboardbo.saveNotificationData(n);

			for (int i = 0; i < toArray.length; i++) {

				String empId = "";
				String groupId = "";

				if (toArray[i].contains("e")) {
					empId = toArray[i].replace("e", "");
				} else if (toArray[i].contains("g")) {
					groupId = toArray[i].replace("g", "");
				}

				if (!empId.isEmpty() && empId != null && !empId.equals("")) {
					pushId = dashboardbo.getPushByEmpId(Long.parseLong(empId));
					if (!pushId.isEmpty()) {
						pushEmpList.add(pushId);
					}

					NotifyUser nu = new NotifyUser();
					nu.setEmpId(Long.parseLong(empId));
					nu.setNotifId(n.getId());
					nu.setUnreadFlag(true);
					nu = dashboardbo.saveNotificationMapping(nu);
				}

				if (!groupId.isEmpty() && groupId != null && !groupId.equals("")) {
					List<Long> employeeList = dashboardbo.getEmpByGroupId(Long.parseLong(groupId));
					for (Long eId : employeeList) {
						pushId = dashboardbo.getPushByEmpId(eId);
						if (!pushId.isEmpty()) {
							pushEmpList.add(pushId);
						}

						NotifyUser nu = new NotifyUser();
						nu.setEmpId(eId);
						nu.setNotifId(n.getId());
						nu.setUnreadFlag(true);
						nu = dashboardbo.saveNotificationMapping(nu);
					}

				}

			}

			PushSender ps = new PushSender();
			Date date = new Date();
			String mes = "New notification";
			if (pushEmpList.size() > 0) {
				ps.sendPushMessageToList(pushEmpList, mes, desc, date.toString());
			}

			if (n.getId() > 0) {
				obj.put("status", "success");
			} else {
				obj.put("status", "failure");
			}
			String res = obj.toString();

			LOGGER.info("Exit from saveNotificationDetails method");
			return new ResponseEntity<String>(res, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Error in saveNotificationDetails method");
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * To get notification list
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/notificationlist", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> getNotificationList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LOGGER.info("Inside getNotificationList method");
		try {
			long empId = 0;
			if ((request.getParameter("empId") != null) && (request.getParameter("empId").isEmpty() == false)
					&& (!request.getParameter("empId").equals("undefined"))) {
				String empIdStr = request.getParameter("empId");
				empId = Long.parseLong(empIdStr);
			}

			JSONArray ja = new JSONArray();
			String res = null;
			DashboardBO dashboardbo = Factory.getInstance().getInstanceService(DashboardBO.class);
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
			String realPath = request.getRealPath(new String(new char[] { File.separatorChar }));
			String webapprealPath = realPath.substring(0, realPath.indexOf("webapps") + 8);
			
			List<Notification> nfList = dashboardbo.getNotificationListByEmpId(empId);

			if (nfList != null && nfList.size() > 0) {
				for (Notification n : nfList) {
					JSONObject jo = new JSONObject();
					
					if((null==n.getAttachment())||(n.getAttachment().isEmpty()==true)){
						jo.put("attachment", "");
					}else{
						jo.put("attachment", n.getAttachment());
					}
    				jo.put("id", n.getId());
					jo.put("senderName", n.getCreatedBy().getFirstName() + " " + n.getCreatedBy().getLastName());
					jo.put("description", n.getDescription());
					jo.put("priority", n.getPriority());
					jo.put("createdDate", formatter.format(n.getCreatedDate()));
					
					if((null==n.getAttachment())||(n.getAttachment().isEmpty()==true)) {
						jo.put("thumb", "");
					}else{
						jo.put("thumb", getAttachmentPhoto(n.getAttachment(), webapprealPath));
					}

					ja.put(jo);
				}
			}
			res = ja.toString();
			LOGGER.info("Exit from getNotificationList method");
			return new ResponseEntity<String>(res, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Error in getNotificationList method");
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
    
    private String getAttachmentPhoto(String attachment, String webapprealPath) {

    	Blob img;
		byte[] b = null;
		String path=attachment;
		path=webapprealPath + path;
		File photoimg=new File(path);

		b = new byte[(int) photoimg.length()];
		try {
			//convert file into array of bytes
			FileInputStream fileInputStream = new FileInputStream(photoimg);
			fileInputStream.read(b);
			fileInputStream.close();

			for (int i = 0; i < b.length; i++) {
				//  	System.out.print((char)b[i]);
			}

			//  System.out.println("Done");
		}catch(Exception e){
			e.printStackTrace();
		}
		if(b!=null){
			String byteToString = new String(Base64.encodeBytes(b));

			return byteToString;
		} else return null;
	
    }

	/*
     * to register new reminder
     * 
     */
 	@RequestMapping(value = "/registerreminder", method = RequestMethod.POST, produces = "application/json")
 	public @ResponseBody ResponseEntity<String> registerReminder(HttpServletRequest request) {
 		LOGGER.info("Inside registerReminder method");
 		   System.out.println("Inside registerReminder method");
 		 try {
 			DashboardBO dashboardbo = Factory.getInstance().getInstanceService(DashboardBO.class);
				 String content="";		
 				 Date remindOnDate=null;
 			     Date remindOnTime=null;
 			     long reminderId = 0;
 			     long createdBy=0;
 			     
 			     
 			    if(request.getParameter("content")!=null && request.getParameter("content").isEmpty()==false)
 				{
 			    	content=request.getParameter("content");
 				}
 			    
 			   if(request.getParameter("reminderId")!=null && request.getParameter("reminderId").isEmpty()==false)
				{
			    	String reminderIdStr=request.getParameter("reminderId");
			    	reminderId=Long.parseLong(reminderIdStr);
				}
			    			    
 				SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
 				if (request.getParameter("remindOnDate") != null && request.getParameter("remindOnDate").isEmpty() == false) {
 					String reminderOnDateStr = request.getParameter("remindOnDate");
 					remindOnDate=sdf.parse(reminderOnDateStr);
 				}

 				 DateFormat formatter = new SimpleDateFormat("hh:mm a");
 		          if(request.getParameter("remindOnTime")!=null && request.getParameter("remindOnTime").isEmpty()==false){
 				  String timeStr=request.getParameter("remindOnTime");
 				 remindOnTime=formatter.parse(timeStr);
 				}
 		         		JSONObject json=new JSONObject();          
 		          if(request.getParameter("createdBy")!=null && request.getParameter("createdBy").isEmpty()==false){
 		        	 String createdByStr=request.getParameter("createdBy");
 		        	createdBy=Long.parseLong(createdByStr);
 		          }else{
 		        	  json.put("status", "requiredCreatedBy");
 		        	 return new ResponseEntity<String>(json.toString(), HttpStatus.OK);
 		          }
 			   
 			
 				Reminder rem=dashboardbo.getReminderId(reminderId);
 				if(null!=rem){
 					rem.setRemindOnDate(remindOnDate);
 					rem.setRemindOnTime(remindOnTime);
 				}else{
 					rem=new Reminder();
 					rem.setContent(content);
  					rem.setRemindOnDate(remindOnDate);
 					rem.setRemindOnTime(remindOnTime);
 					Employee emp=new Employee();
 					emp.setEmpId(createdBy);
                    rem.setCreatedBy(emp); 		
                    rem.setActive(true);
 				}
 	        	 String status=dashboardbo.addNewReminder(rem);
 	                 
 	            LOGGER.info("Exit from registerReminder method");
 	            return new ResponseEntity<String>(status, HttpStatus.OK);
 	        } catch(Exception e) {
 	        	LOGGER.error("Error in registerReminder  method");
 	            e.printStackTrace();
 	            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
 	        }
 }

 	
 	/**
 	 * To get list of reminder
 	 * @param request
 	 * @param response
 	 * @return
 	 * @throws Exception
 	 */
 	@RequestMapping(value = "/showreminder", method = RequestMethod.GET)
 	public @ResponseBody
 	ResponseEntity<String> showReminder(HttpServletRequest request,
 	        HttpServletResponse response) throws Exception {
 	    LOGGER.info("Inside showReminder method"); 	
 	    try {
 	    	DashboardBO dashboardbo = Factory.getInstance().getInstanceService(DashboardBO.class);
 	    	 
 		    	long empId=0;
 	    	  if(request.getParameter("empId")!=null && request.getParameter("empId").isEmpty()==false)
				{
 	    		String empIdStr=request.getParameter("empId");
 	    		empId=Long.parseLong(empIdStr); 	    		
				}
 	          SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
 	          DateFormat formatter = new SimpleDateFormat("h:mm a");
 	    	   	List<Reminder> reminderlist=dashboardbo.getReminderList(empId);
 	    	JSONArray ja = new JSONArray();
 	    	if(null!=reminderlist && reminderlist.size()>0){
 	    		for(Reminder reminder:reminderlist){
 	    			JSONObject json=new JSONObject();
 	    			json.put("reminderId",reminder.getReminderId());
 	    			json.put("content",reminder.getContent());
 	    			if(reminder.getRemindOnDate()!=null){
  	    			json.put("remindOnDate",sdf.format(reminder.getRemindOnDate()));
 	    			}else{
 	    				json.put("remindOnDate", "");
 	    			}
 	    			if(reminder.getRemindOnTime()!=null){
 	    				json.put("remindOnTime",formatter.format(reminder.getRemindOnTime()));
 	    			}else{
 	    				json.put("remindOnTime", "");
 	    			}
 	    			
 	    		//	json.put("createdBy",reminder.getCreatedBy());
 	    			ja.put(json);
 	    		}
 	    	
 	    	}
 	        String res=ja.toString();
 	        LOGGER.info("Exit from showReminder method");
 	        return new ResponseEntity<String>(res, HttpStatus.OK);
 	    } catch (Exception e) {
 	    	LOGGER.error("Error in showReminder method");
 	        e.printStackTrace();
 	        return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
 	    }

 	}

 	
 	
 	
 	/**
 	 * to delete reminder
 	 * @param request
 	 * @return
 	 */
 	@RequestMapping(value = "/deletereminder", method = RequestMethod.POST, produces = "application/json")
 	public @ResponseBody ResponseEntity<String> deleteReminder(HttpServletRequest request) {
 		LOGGER.info("Inside deleteReminder method in DashboardController");
 	
 		try{
 			DashboardBO dashboardbo = Factory.getInstance().getInstanceService(DashboardBO.class);
 			JSONObject res=new JSONObject();
 		 	long reminderId=0;
	    	  if(request.getParameter("reminderId")!=null && request.getParameter("reminderId").isEmpty()==false)
				{
	    		String reminderIdStr=request.getParameter("reminderId");
	    		reminderId=Long.parseLong(reminderIdStr); 	    		
				}
 			Reminder rem =dashboardbo.getReminderId(reminderId);
 			 if(rem!=null)
 		        { 
 				rem.setActive(false);	
 				rem=dashboardbo.deleteReminderRecord(rem);
 		        	res.put("status", "success");
 		        }
 		        else{
 		        	res.put("status", "fail");
 		        }
 			LOGGER.info("Exit from deletereminder method in LeadController");

 			return new ResponseEntity<String>(res.toString(), HttpStatus.OK);
 		} catch (Exception e) {

 			LOGGER.error("Exception in deletereminder method in LeadController", e);
 			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
 		}

 	}

 	/**
 	 * to get last visit sale of particular sr for the route
 	 * @param request
 	 * @param response
 	 * @return
 	 * @throws Exception
 	 */
 	 @RequestMapping(value = "/lastvisitsale", method = RequestMethod.POST)
     public @ResponseBody
     ResponseEntity<String> getLastVisitSaleOfSR(HttpServletRequest request,
             HttpServletResponse response) throws Exception {
         LOGGER.info("Enter getLastVisitSaleOfSR method");
         try {
        	 DashboardBO dashboardbo = Factory.getInstance().getInstanceService(DashboardBO.class);
        	 long routeId=0;
        	 if((request.getParameter("routeId")!=null) && (!request.getParameter("username").equals("undefined"))){
        		 String routeIdStr = request.getParameter("routeId");
        		 routeId=Long.parseLong(routeIdStr);
        	 }
            
             LOGGER.info("Exit getLastVisitSaleOfSR method");
             return new ResponseEntity<>(null, HttpStatus.OK);
         } catch (Exception e) {
             e.printStackTrace();
             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
         }

     }
 	 
 	/**
  	 * to delete reminder
  	 * @param request
  	 * @return
  	 */
  	@RequestMapping(value = "/readnotification", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseEntity<String> readNotification(HttpServletRequest request) throws Exception {
		LOGGER.info("Inside readNotification method in DashboardController");

		try {
			DashboardBO dashboardbo = Factory.getInstance().getInstanceService(DashboardBO.class);
			JSONObject res = new JSONObject();
			long empId = 0;
			if (request.getParameter("empId") != null && request.getParameter("empId").isEmpty() == false) {
				String empIdStr = request.getParameter("empId");
				empId = Long.parseLong(empIdStr);
			}
			long id = dashboardbo.readNotification(empId);
			res.put("status", "success");
			LOGGER.info("Exit from readNotification method in LeadController");

			return new ResponseEntity<String>(res.toString(), HttpStatus.OK);
		} catch (Exception e) {

			LOGGER.error("Exception in readNotification method in LeadController", e);
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
    
}
