package com.parkinglot.system.entities;

import com.parkinglot.system.entities.Floor;
import com.parkinglot.system.entities.Slot;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Entity
@Data
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL)
    private List<Floor> floors;
    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL)
    private List<Slot> slots;
    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL)
    private List<Vehicle> vehicles;
}
