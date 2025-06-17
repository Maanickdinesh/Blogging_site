package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.master.Hobby;
import com.example.repo.HobbyRepository;

@Service
public class MasterHobbyServiceImpl implements MasterHobbyService {

	@Autowired
	private HobbyRepository hobbyRepository;
	
	@Override
	public List<Hobby> getAllHobbies() {
		// TODO Auto-generated method stub
        return hobbyRepository.findAll();
	}

}
