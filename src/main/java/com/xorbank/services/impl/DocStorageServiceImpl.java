package com.xorbank.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.xorbank.model.Document;
import com.xorbank.model.User;
import com.xorbank.repository.DocRepository;
import com.xorbank.repository.UserRepository;
import com.xorbank.services.DocStorageService;

@Service
public class DocStorageServiceImpl implements DocStorageService{
	
	@Autowired
	private DocRepository docRepository;
	@Autowired
	private UserRepository userRepo;

	public Document saveFile(MultipartFile file, Integer userId) {
		
		String docname = file.getOriginalFilename();
		User user = userRepo.findByUserId(userId);
		try {
			Document document = new Document(docname, file.getContentType(), file.getBytes(),user);
			return docRepository.save(document);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Document getFile(Integer userID) {
		
		return (Document) docRepository.findByUserUserId(userID);
	}
	
}