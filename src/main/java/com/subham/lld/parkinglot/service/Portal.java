package com.subham.lld.parkinglot.service;

import com.subham.lld.parkinglot.model.parking.ParkingTicket;

/**
 * @author subham.paul
 */
public interface Portal {
    ParkingTicket scan(ParkingTicket parkingTicket);

    boolean pay(ParkingTicket parkingTicket);
}
