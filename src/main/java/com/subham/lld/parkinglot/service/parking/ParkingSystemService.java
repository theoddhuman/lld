package com.subham.lld.parkinglot.service.parking;

import com.subham.lld.parkinglot.model.parking.ParkingAvailability;
import com.subham.lld.parkinglot.model.parking.ParkingTicket;
import com.subham.lld.parkinglot.model.parking.Vehicle;
import com.subham.lld.parkinglot.model.parking.type.ParkingSpotStatus;
import com.subham.lld.parkinglot.model.parking.type.ParkingTicketStatus;
import com.subham.lld.parkinglot.model.parking.type.Size;
import com.subham.lld.parkinglot.model.parking.type.UtilityType;

/**
 * @author subham.paul
 */
public interface ParkingSystemService {
    /**
     * Get availability in a parking lot by size and utility
     */
    ParkingAvailability getAvailabilityBySizeAndUtility(long parkingLotId, Size size, UtilityType utilityType);

    /**
     * Update parking spot status
     */
    boolean updateStatusParkingSpot(long parkingSpotId, ParkingSpotStatus status);

    /**
     * Assign parking spot to a vehicle, mark the parking spot as occupied and removeFromQueue from availability matrix
     */
    boolean assignParkingSpot(ParkingAvailability parkingAvailability, Vehicle vehicle);

    /**
     * Generate parking ticket
     */
    ParkingTicket buildParkingTicket(ParkingAvailability parkingAvailability, Vehicle vehicle, long panelId);

    /**
     * Fetch parking ticket at exit, calculate bill
     */
    ParkingTicket fetchParkingTicket(String id, long panelId);

    /**
     * Update parking ticket status ( BILLED / PAID)
     */
    void updateParkingTicketStatus(String id, ParkingTicketStatus status);

    /**
     * Get hourly rate for parking size and utility
     */
    double getRate(Size size, UtilityType utilityType);

    /**
     * Update parking status as AVAILABLE and add to availability matrix
     */
    boolean releaseParkingSpot(long parkingSpotId);


    /**
     * Display availability dashboard
     */
    void displayDashboardByFloor(long parkingFloorId);
}
