package com.example.service.rentalservice.repository;

import com.example.service.rentalservice.domain.Firm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirmRepository extends JpaRepository<Firm, Long> {
    Firm findFirmByName(String name);
}
