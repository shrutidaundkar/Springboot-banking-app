package com.xorbank.services;

import org.springframework.web.multipart.MultipartFile;

import com.xorbank.models.Document;

public interface DocStorageService {
	
	public Document saveFile(MultipartFile file);
	public Document getFile(Integer fileId);	
}
