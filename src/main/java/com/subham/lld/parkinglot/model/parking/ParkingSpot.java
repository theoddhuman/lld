package com.subham.lld.parkinglot.model.parking;

import com.subham.lld.parkinglot.model.parking.type.ParkingSpotStatus;
import com.subham.lld.parkinglot.model.parking.type.Size;
import com.subham.lld.parkinglot.model.parking.type.UtilityType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author subham.paul
 */

@Data
@Builder
public class ParkingSpot {
    private long id;

    private String number;

    private Size size;

    private UtilityType utilityType;

    private ParkingSpotStatus status;

    private Panel electricPanel;

    private long parkingFloorId;
}
