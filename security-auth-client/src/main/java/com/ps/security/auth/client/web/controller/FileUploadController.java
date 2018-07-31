package com.ps.security.auth.client.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.ps.security.auth.client.dto.FileInfo;

@RestController
public class FileUploadController {
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	private String localFileStore = "C:\\Users\\Administrator\\git\\security-auth\\security-auth-client\\filestore";
	
	@PostMapping("/file")
	public FileInfo uploadFile(MultipartFile file) throws IllegalStateException, IOException {
		if(file == null) {
			System.out.println("input file is null......");
		}
		logger.info(file.getName());
		logger.info(file.getOriginalFilename());
		logger.info(String.valueOf(file.getSize()));
		File localFile = new File(localFileStore,new Date().getTime() + ".txt");
		file.transferTo(localFile);
		return new FileInfo(localFile.getAbsolutePath());
	}
	@GetMapping("/file/{id}")
	public void downloadFile(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
		try {
			InputStream inputStream = new FileInputStream(new File(this.localFileStore,id + ".txt"));
			OutputStream outputStream = response.getOutputStream();
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition", "attachment;filename=test.txt");
			IOUtils.copy(inputStream, outputStream);
			outputStream.flush();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
