package com.parkinglot.system.services;

import com.parkinglot.system.entities.Floor;
import com.parkinglot.system.entities.Slot;
import com.parkinglot.system.entities.Vehicle;
import com.parkinglot.system.repositories.FloorRepository;
import com.parkinglot.system.repositories.ParkingLotRepository;
import com.parkinglot.system.repositories.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingLotService {
    @Autowired
    private ParkingLotRepository parkingLotRepository;
    @Autowired
    private SlotRepository slotRepository;
    @Autowired
    private FloorRepository floorRepository;

    public boolean onBoardNewParkingLot(String floorId, List<Slot> newSlots) {
        Floor floor = floorRepository.findByFloorId(floorId);
        if (floor != null) {
            return false;
        }
        floor = new Floor();
        floor.setFloorId(floorId);
        floor.setSlots(newSlots);
        floorRepository.save(floor);
        for (Slot slot : newSlots) {
            slot.setFloor(floor);
            slotRepository.save(slot);
        }
        return true;
    }

    public String allocateSlot(String parkingLotId, Vehicle vehicle) {
        List<Floor> floors = floorRepository.findByParkingLotId(parkingLotId);
        String[] priority = {"XLarge", "Large", "Medium", "Small"};
        for (Floor floor : floors) {
            for (String size : priority) {
                if (vehicle.getSize().equals(size)) {
                    List<Slot> slots = slotRepository.findBySizeAndOccupied(size, false);
                    if (!slots.isEmpty()) {
                        Slot slot = slots.get(0);
                        slot.setOccupied(true);
                        slotRepository.save(slot);
                        return floor.getFloorId() + ":" + slot.getId();
                    }
                }
            }
        }
        return "NO SLOT FOUND";
    }

    public boolean unparkVehicle(String slotId) {
        Slot slot = slotRepository.findBySlotId(slotId);
        if (slot != null && slot.isOccupied()) {
            slot.setOccupied(false);
            slotRepository.save(slot);
            return true;
        }
        return false;
    }

    public long getAvailableSlots(String size) {
        return slotRepository.countBySizeAndOccupied(size, false);
    }
}
