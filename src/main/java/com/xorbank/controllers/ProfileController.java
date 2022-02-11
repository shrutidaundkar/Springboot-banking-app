package com.xorbank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xorbank.model.User;
import com.xorbank.services.ProfileService;

@RestController
//@RequestMapping("/server")
@RequestMapping("${server.context-path}")
@CrossOrigin(origins = "http://localhost:4200")
public class ProfileController {
	
	@Autowired
	private ProfileService profileService;
	
//	@GetMapping("/profile/{userId}")
	@GetMapping("${get.user.profile}")
	public ResponseEntity<User> getUserByEmail(@PathVariable("userId") Integer userId)
	{
		User user = profileService.findByUserId(userId);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
//	@PutMapping("/profile/update")
	@PutMapping("${user.profile.update}")
	public ResponseEntity<User> updateEmployee(@RequestBody User user) 	
	{
		User updatedUser = profileService.updateUser(user);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}
}