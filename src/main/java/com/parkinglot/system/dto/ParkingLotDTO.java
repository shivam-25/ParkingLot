package com.parkinglot.system.dto;

import com.parkinglot.system.entities.Slot;
import lombok.Data;

import java.util.List;

@Data
public class ParkingLotDTO {
    private String floorId;
    private List<Slot> slots;
}