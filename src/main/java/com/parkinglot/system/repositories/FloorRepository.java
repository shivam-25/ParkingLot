package com.parkinglot.system.repositories;

import com.parkinglot.system.entities.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FloorRepository extends JpaRepository<Floor, Long> {
    Floor findByFloorId(String floorId);
    List<Floor> findAll();
}
