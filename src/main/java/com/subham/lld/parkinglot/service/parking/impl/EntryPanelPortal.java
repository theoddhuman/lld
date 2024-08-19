package com.subham.lld.parkinglot.service.parking.impl;

import com.subham.lld.parkinglot.model.parking.ParkingAvailability;
import com.subham.lld.parkinglot.model.parking.ParkingTicket;
import com.subham.lld.parkinglot.model.parking.Vehicle;
import com.subham.lld.parkinglot.service.parking.PanelPortal;
import com.subham.lld.parkinglot.service.parking.ParkingSystemService;

import java.util.Objects;

/**
 * @author subham.paul
 */
public class EntryPanelPortal implements PanelPortal {
    private long panelId;
    private final ParkingSystemService parkingSystemService;

    public EntryPanelPortal(long panelId) {
        this.panelId = panelId;
        parkingSystemService = new ParkingSystemServiceImpl();
    }
    @Override
    public ParkingTicket printTicket(long parkingLotId, Vehicle vehicle) {
        while(true) {
            ParkingAvailability parkingAvailability = parkingSystemService
                    .getAvailabilityBySizeAndUtility(parkingLotId, vehicle.getSize(), vehicle.getUtilityType());
            if (Objects.isNull(parkingAvailability)) {
                System.out.println("No Parking available");
                return null;
            }
            if (parkingSystemService.assignParkingSpot(parkingAvailability, vehicle)) {
                return parkingSystemService.buildParkingTicket(parkingAvailability, vehicle, panelId);
            }
        }
    }

    @Override
    public ParkingTicket scanTicket(String ticketId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> boolean pay(ParkingTicket parkingTicket, long collectorId, T paymentModeDetails) {
        throw new UnsupportedOperationException();
    }
}
