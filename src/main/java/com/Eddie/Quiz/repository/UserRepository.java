package com.Eddie.Quiz.repository;

import com.Eddie.Quiz.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    public UserEntity findByUsername(String username);

}
