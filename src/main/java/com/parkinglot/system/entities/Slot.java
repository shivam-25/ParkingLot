package com.parkinglot.system.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String slotId;
    private String size;
    @Column(columnDefinition = "boolean default false")
    private boolean occupied;
    @ManyToOne
    @JoinColumn(name = "floor_id")
    private Floor floor;
    @ManyToOne
    @JoinColumn(name = "parking_lot_id")
    private ParkingLot parkingLot;
}