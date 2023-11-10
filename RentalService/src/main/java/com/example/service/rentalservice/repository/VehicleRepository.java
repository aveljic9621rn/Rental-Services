package com.example.service.rentalservice.repository;

import com.example.service.rentalservice.domain.Vehicle;
import com.example.service.rentalservice.dto.VehicleDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Vehicle findVehicleByModel(String model);
}
