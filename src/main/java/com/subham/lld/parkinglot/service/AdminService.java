package com.subham.lld.parkinglot.service;

import com.subham.lld.parkinglot.model.account.Account;
import com.subham.lld.parkinglot.model.parking.Panel;
import com.subham.lld.parkinglot.model.parking.ParkingFloor;
import com.subham.lld.parkinglot.model.parking.ParkingLot;
import com.subham.lld.parkinglot.model.parking.ParkingSpot;
import com.subham.lld.parkinglot.model.parking.type.Size;
import com.subham.lld.parkinglot.model.parking.type.UtilityType;

/**
 * @author subham.paul
 */
public interface AdminService {
    /**
     * Add parking attendant
     */
    Account addParkingAttendant(long adminId, Account account);

    /**
     * Remove parking attendant
     */
    void removeParkingAttendant(long adminId, long parkingAttendantId);

    /**
     * Add parking lot
     */
    void addParkingLot(long adminId, ParkingLot parkingLot);

    /**
     * Add parking floor
     */
    void addParkingFloor(long adminId, ParkingFloor parkingFloor);

    /**
     * remove parking floor
     */
    void removeParkingFloor(long adminId, long parkingFloorId);

    /**
     * Add parking spot
     */
    void addParkingSpot(long adminId, ParkingSpot parkingSpot);

    /**
     * Remove parking spot
     */
    void removeParkingSpot(long adminId, long parkingSpotId);

    /**
     * Add panel
     */
    void addPanel(long adminId, long parkingLotId, Panel panel);

    /**
     * Remove panel
     */
    void removePanel(long adminId, long parkingLotId, String panelId);

    /**
     * Update hourly fare by size and utility
     */
    void updateRate(Size size, UtilityType utilityType, double rate);
}
