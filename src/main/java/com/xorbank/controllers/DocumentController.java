package com.xorbank.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
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
import org.springframework.http.MediaType;

import com.xorbank.model.Account;
import com.xorbank.model.Document;
import com.xorbank.services.DocStorageService;

@RestController
//@RequestMapping("/server")
@RequestMapping("${server.context-path}")
@CrossOrigin(origins = "http://localhost:4200")
@PropertySource("classpath:xorbankUrl.properties")
public class DocumentController {
	
	@Autowired
	private DocStorageService docStorageService;

	//@PostMapping("/uploadFile/{userId}")
	@PostMapping(value="${UPLOAD_FILE}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> uploadFile(@PathVariable("userId") Integer userId,  @RequestParam("file") MultipartFile file) {
		docStorageService.saveFile(file, userId);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}

	
	//@GetMapping("/downloadFile/{userId}")
	@GetMapping("${DOWNLOAD_FILE}")
	  public ResponseEntity<byte[]> downloadFile(@PathVariable Integer userId) {
	    
	    Document document = docStorageService.getFile(userId);
	    
	    return ResponseEntity.ok()
	        .header(javax.ws.rs.core.HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getDocName() + "\"")
	        .body(document.getData());
	  }
	  
}
	

