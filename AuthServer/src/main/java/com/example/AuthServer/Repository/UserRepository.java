package com.example.AuthServer.Repository;

import com.example.AuthServer.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select u from User u where u.name=:username")
    Optional<User> findByUserName(String username);
}
