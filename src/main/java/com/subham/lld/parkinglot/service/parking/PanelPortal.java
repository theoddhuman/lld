package com.subham.lld.parkinglot.service.parking;

import com.subham.lld.parkinglot.model.parking.ParkingTicket;
import com.subham.lld.parkinglot.model.parking.Vehicle;

/**
 * @author subham.paul
 */
public interface PanelPortal {

    /**
     * Print ticket at the entrance, mark parking spot
     */
    ParkingTicket printTicket(long parkingLotId, Vehicle vehicle);

    /**
     * scan ticket at the exit, mark parking spot as available, calculate bill
     */
    ParkingTicket scanTicket(String ticketId);

    /**
     * Get payment details, proceed payment and open the get on successful payment
     */
    <T> boolean pay(ParkingTicket parkingTicket, long collectorId, T paymentModeDetails);
}
