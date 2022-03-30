package com.xorbank.services;

import org.springframework.web.multipart.MultipartFile;

import com.xorbank.model.Document;

public interface DocStorageService {
	
	public Document saveFile(MultipartFile file, Integer userId);
	public Document getFile(Integer userId);	
}
