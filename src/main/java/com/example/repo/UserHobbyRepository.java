package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.UserHobby;

public interface UserHobbyRepository extends JpaRepository<UserHobby, Long>  {

}
