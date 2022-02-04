package com.xorbank.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.xorbank.models.Document;
import com.xorbank.repository.DocRepository;
import com.xorbank.services.DocStorageService;

@Service
public class DocStorageServiceImpl implements DocStorageService{
	
	@Autowired
	private DocRepository docRepository;

	public Document saveFile(MultipartFile file) {
		
		String docname = file.getOriginalFilename();
		try {
			Document document = new Document(docname, file.getContentType(), file.getBytes());
			return docRepository.save(document);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Document getFile(Integer fileId) {
		
		return docRepository.findById(fileId).get();
	}
	
}