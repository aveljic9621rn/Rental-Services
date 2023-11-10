package com.example.service.rentalservice.repository;

import com.example.service.rentalservice.domain.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleTypeRepository extends JpaRepository<VehicleType, Long> {
    VehicleType findVehicleTypeByName(String name);
}
