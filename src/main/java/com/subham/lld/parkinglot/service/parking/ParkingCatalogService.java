package com.subham.lld.parkinglot.service.parking;

import com.subham.lld.parkinglot.model.parking.Panel;
import com.subham.lld.parkinglot.model.parking.ParkingFloor;
import com.subham.lld.parkinglot.model.parking.ParkingLot;
import com.subham.lld.parkinglot.model.parking.ParkingSpot;
import com.subham.lld.parkinglot.model.parking.type.Size;
import com.subham.lld.parkinglot.model.parking.type.UtilityType;

/**
 * @author subham.paul
 */
public interface ParkingCatalogService {
    void addParkingLot(ParkingLot parkingLot);

    void addParkingFloor(ParkingFloor parkingFloor);

    void removeParkingFloor(long parkingFloorId);

    void addParkingSpot(ParkingSpot parkingSpot);

    void removeParkingSpot(long parkingSpotId);

    void addPanel(Panel panel);

    void removePanel(String panelId);

    /**
     * View all catalog data of parking lot
     */
    void viewParkingLot(long parkingLotId);

    /**
     * This method should be triggered, everytime admin updates the parking lot.
     */
    void calculateParkingAvailability(long parkingLotId);

    void updateRate(Size size, UtilityType utilityType, double rate);
}
