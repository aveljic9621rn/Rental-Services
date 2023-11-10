package com.example.service.userservice.repository;

import com.example.service.userservice.domain.Admin;
import com.example.service.userservice.domain.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findAdminByUsernameAndPassword(String username, String password);

    Admin findByAdminId(int adminId);

    void deleteByAdminId(int adminId);
}
