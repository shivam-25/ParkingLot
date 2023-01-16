package com.parkinglot.system.entities;

import com.parkinglot.system.entities.Floor;
import com.parkinglot.system.entities.Slot;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Entity
@Table(name = "parking_lot")
@Data
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL)
    private List<Floor> floors;

    @ElementCollection
    @CollectionTable(name="slot_sizes", joinColumns=@JoinColumn(name="parking_lot_id"))
    @MapKeyColumn(name="size")
    @Column(name="count")
    private Map<String, Integer> slotSizes;

    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL)
    private Map<String, Slot> slots;

}
