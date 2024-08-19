package com.subham.lld.parkinglot.model.parking;

import lombok.Builder;
import lombok.Data;

/**
 * @author subham.paul
 */
@Data
@Builder
public class ParkingFloor {
    private long id;

    private String name;

    private long parkingLotId;
}
