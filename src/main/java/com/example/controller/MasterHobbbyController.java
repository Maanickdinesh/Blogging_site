package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.master.Hobby;
import com.example.service.MasterHobbyService;

@RestController
@RequestMapping("/api")
@CrossOrigin

public class MasterHobbbyController {
	
	@Autowired
	private MasterHobbyService hobbyService;
	
	@GetMapping("/hobbies")
    public ResponseEntity<List<Hobby>> getAllHobbies() {
        List<Hobby> hobbies = hobbyService.getAllHobbies();
        return ResponseEntity.ok(hobbies); 
    }

}
