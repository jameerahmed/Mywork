package com.zameer.work.controller;


import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.zameer.work.constant.FilePath;
import com.zameer.work.util.UploadUtility;

@RequestMapping(value="files")
@Controller
public class FileUploadController {
	private static final Logger LOG = LoggerFactory.getLogger(FileUploadController.class);
	
	
	@Resource(name = "uploadUtility")
	private UploadUtility uploadUtility;
	
	@RequestMapping(value="/uploadFile",method = RequestMethod.POST)
	public String uploadFiles(@RequestParam("file") MultipartFile file,@RequestParam("name") String name) {

		if (!file.isEmpty()) {
			try {
				LOG.info("name"+name);
				 Boolean isSaved = uploadUtility.saveFile(FilePath.UPLOAD_PHOTO_PATH,file.getOriginalFilename(),file);
				 if(isSaved){
					 LOG.info("File Saved Success");
					 } else return null;
				
				return null;
			} catch (Exception e) {
				return "You failed to upload " + e.getMessage();
			}
		} else {
			return "You failed to upload because the file was empty.";
		}
	}

}
