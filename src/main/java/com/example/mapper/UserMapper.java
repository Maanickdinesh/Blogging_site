package com.example.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.dto.RegisterUserRequest;
import com.example.dto.UserDto;
import com.example.entity.User;

public class UserMapper {
	public static UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setMobileNumber(user.getMobileNumber());
        dto.setRole(user.getRole());
        if (user.getUserHobbies() != null) {
            List<String> hobbies = user.getUserHobbies()
                .stream()
                .map(uh -> uh.getHobby().getName())
                .collect(Collectors.toList());
            dto.setHobbies(hobbies);
        }
        return dto;
    }

    public static User fromRegisterRequest(RegisterUserRequest request, PasswordEncoder encoder) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setMobileNumber(request.getMobileNumber());
        user.setRole("ADMIN");
        
        return user;
    }
}
