package com.parkinglot.system.controller;

import com.parkinglot.system.dto.ParkingLotDTO;
import com.parkinglot.system.entities.Vehicle;
import com.parkinglot.system.services.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/parkinglot")
public class ParkingController {

    private final ParkingLotService parkingLotService;

    @Autowired
    public ParkingController(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @PostMapping("/onboard")
    public boolean onBoardNewParkingLot(@RequestBody ParkingLotDTO parkingLotDTO) {
        return parkingLotService.onBoardNewParkingLot(parkingLotDTO.getFloorId(), parkingLotDTO.getSlots());
    }

    @PostMapping("/getslot/{parkingLotId}")
    public ResponseEntity<Map<String, String>> allocateSlot(@PathVariable("parkingLotId") String parkingLotId, @RequestBody Vehicle vehicle) {
        String slotId = parkingLotService.allocateSlot(parkingLotId, vehicle);
        if (slotId.equals("NO SLOT FOUND")) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Map<String, String> response = new HashMap<>();
        response.put("Slot", slotId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/releaseslot/{slotId}")
    public boolean releaseSlot(@PathVariable("slotId") String slotId) {
        return parkingLotService.unparkVehicle(slotId);
    }
}

