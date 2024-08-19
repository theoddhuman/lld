package com.subham.lld.parkinglot.service.impl;

import com.subham.lld.parkinglot.model.parking.ParkingTicket;
import com.subham.lld.parkinglot.service.Portal;

/**
 * @author subham.paul
 */
public class ParkingAttendantPortal implements Portal {
    @Override
    public ParkingTicket scan(ParkingTicket parkingTicket) {
        return null;
    }

    @Override
    public boolean pay(ParkingTicket parkingTicket) {
        return false;
    }
}
