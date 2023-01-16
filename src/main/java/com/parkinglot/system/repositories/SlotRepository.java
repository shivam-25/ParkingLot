package com.parkinglot.system.repositories;

import com.parkinglot.system.entities.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Long> {
    List<Slot> findBySizeAndOccupied(String size, boolean occupied);
    Slot findBySlotId(String slotId);
    long countBySizeAndOccupied(String size, boolean occupied);
}