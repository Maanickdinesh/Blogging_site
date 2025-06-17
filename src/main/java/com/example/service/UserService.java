package com.example.service;

import com.example.dto.LoginRequest;
import com.example.dto.RegisterUserRequest;
import com.example.dto.UserDto;


public interface UserService {
	 UserDto registerUser(RegisterUserRequest request);
	    UserDto login(LoginRequest request);
}
