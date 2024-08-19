package com.subham.lld.parkinglot.repository;

import com.subham.lld.parkinglot.model.parking.ParkingTicket;
import com.subham.lld.parkinglot.model.parking.type.ParkingTicketStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @author subham.paul
 */
public class ParkingTicketRepository {
    private static final Map<String, ParkingTicket> parkingTicketCatalog = new HashMap<>();

    public ParkingTicket saveOrUpdate(ParkingTicket parkingTicket) {
        if(parkingTicket.getId() == null) {
            parkingTicket.setId(String.join("/", String.valueOf(parkingTicket.getParkingSpotId()),
                    parkingTicket.getVehicle().getNumber(), String.valueOf(System.currentTimeMillis())));
            parkingTicket.setStatus(ParkingTicketStatus.ACTIVE);
        }
        parkingTicketCatalog.put(parkingTicket.getId(), parkingTicket);
        return parkingTicket;
    }

    public void updateStatus(String id, ParkingTicketStatus status) {
        parkingTicketCatalog.get(id).setStatus(status);
    }

    public ParkingTicket get(String id) {
        return parkingTicketCatalog.get(id);
    }
}
