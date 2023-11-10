package com.example.service.userservice.repository;

import com.example.service.userservice.domain.Uloga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankRepository extends JpaRepository<Uloga,Long> {

    void deleteById(int id);

    Uloga findById(int id);
}
