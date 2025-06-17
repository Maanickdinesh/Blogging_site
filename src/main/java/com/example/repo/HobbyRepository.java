package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.master.Hobby;

public interface HobbyRepository  extends JpaRepository<Hobby, Long>  {

}
