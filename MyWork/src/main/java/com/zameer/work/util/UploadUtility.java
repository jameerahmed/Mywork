package com.zameer.work.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.zameer.work.service.UploadUtilityService;

public class UploadUtility {

	private static final Logger LOG = LoggerFactory.getLogger(UploadUtility.class);
	@Autowired
	private UploadUtilityService uploadUtilityService;
	static InputStream inputStream = null;
	static FileOutputStream outputStream = null;

	public boolean saveFile(String saveToFolder, String fileName, MultipartFile file) {
		LOG.info("inside UploadUtility saveFile()!");
		Configuration configuration = uploadUtilityService.getConfiguration(saveToFolder);
		if (configuration != null && configuration.getValue() != null && configuration.getValue() != "") {
			try {
				inputStream = file.getInputStream();
				File newFile = new File(configuration.getValue().trim().toString() + "/" + fileName);
				if (!newFile.exists()) {
					
					newFile.createNewFile();
				}
				outputStream = new FileOutputStream(newFile);
				int read = 0;
				byte[] bytes = new byte[1024];

				while ((read = inputStream.read(bytes)) != -1) {
					outputStream.write(bytes, 0, read);
				}
				return true;
			} catch (IOException e) {
				LOG.info("Error Occured in UploadUtility!" + e.getMessage());
				return false;
			}
		}else{
			LOG.error("Invalid path. path not found in configuration table column");
		}
		return false;
	}

}
