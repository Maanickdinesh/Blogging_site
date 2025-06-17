package com.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dto.LoginRequest;
import com.example.dto.RegisterUserRequest;
import com.example.dto.UserDto;
import com.example.entity.User;
import com.example.entity.UserHobby;
import com.example.entity.master.Hobby;
import com.example.exception.UserNotFoundException;
import com.example.mapper.UserMapper;
import com.example.repo.HobbyRepository;
import com.example.repo.UserHobbyRepository;
import com.example.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
    private  UserRepository userRepository;
	@Autowired
    private  BCryptPasswordEncoder passwordEncoder;

	@Autowired
	 private  HobbyRepository hobbyRepository;
	@Autowired
	 private  UserHobbyRepository userHobbyRepository;

    @Override
    public UserDto registerUser(RegisterUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = UserMapper.fromRegisterRequest(request, passwordEncoder);
        
        List<Hobby> hobbies = hobbyRepository.findAllById(request.getHobbyIds());

        // Build userHobbies list
        List<UserHobby> userHobbies = hobbies.stream().map(hobby -> {
            UserHobby userHobby = new UserHobby();
            userHobby.setUser(user); // set user reference
            userHobby.setHobby(hobby);
            return userHobby;
        }).collect(Collectors.toList());

        // Set the hobbies into user
        user.setUserHobbies(userHobbies);

        // Save the user (cascades the hobbies)
        User savedUserwithhobbies = userRepository.save(user);

        return UserMapper.toDto(savedUserwithhobbies); // Will now have hobbies eagerly loaded        
    }

    @Override
    public UserDto login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new UserNotFoundException("Invalid email or password");
        }

        return UserMapper.toDto(user);
    }
}
