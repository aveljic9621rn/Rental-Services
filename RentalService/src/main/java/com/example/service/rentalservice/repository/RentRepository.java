package com.example.service.rentalservice.repository;

import com.example.service.rentalservice.domain.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {
    Rent findRentByVehicle(String vehicle);
}
