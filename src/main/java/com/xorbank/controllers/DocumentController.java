package com.xorbank.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.xorbank.models.Document;
import com.xorbank.services.DocStorageService;

@RestController
@RequestMapping("/server")
@CrossOrigin(origins = "http://localhost:4200")
public class DocumentController {
	@Autowired
	private DocStorageService docStorageService;

	@PostMapping("/uploadFile")
	public ResponseEntity<HttpStatus> uploadFile(@RequestParam("file") MultipartFile file) {
		docStorageService.saveFile(file);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}

	
	@GetMapping("/downloadFile/{id}")
	  public ResponseEntity<byte[]> downloadFile(@PathVariable Integer id) {
	    Document document = docStorageService.getFile(id);

	    return ResponseEntity.ok()
	        .header(javax.ws.rs.core.HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getDocName() + "\"")
	        .body(document.getData());
	  }
	  
}
	

