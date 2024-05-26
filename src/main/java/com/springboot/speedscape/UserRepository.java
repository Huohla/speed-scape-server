package com.springboot.speedscape;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository
    <UserEntity, String> {
        List<UserEntity> findByUsernameContains(String partialUsername);
    
        List<UserEntity> findByPasswordContains(String partialPassword);
    
        List<UserEntity> findByUsernameContainsOrPasswordContains(String partialUsername,
            String partialPassword);
}
