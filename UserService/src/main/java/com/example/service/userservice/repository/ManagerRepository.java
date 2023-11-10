package com.example.service.userservice.repository;

import com.example.service.userservice.domain.Manager;
import com.example.service.userservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Optional<Manager> findManagerByUsernameAndPassword(String username, String password);
    Optional<Manager> findManagerByUsername(String username);
    Manager findByManagerId(int managerId);


}
