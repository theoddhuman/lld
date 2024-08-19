package com.subham.lld.parkinglot.service.parking.impl;

import com.subham.lld.parkinglot.model.parking.ParkingTicket;
import com.subham.lld.parkinglot.model.parking.Vehicle;
import com.subham.lld.parkinglot.model.parking.type.ParkingTicketStatus;
import com.subham.lld.parkinglot.model.payment.PaymentTransaction;
import com.subham.lld.parkinglot.service.PaymentService;
import com.subham.lld.parkinglot.service.parking.PanelPortal;
import com.subham.lld.parkinglot.service.parking.ParkingSystemService;

/**
 * @author subham.paul
 */
public class ExitPanelPortal implements PanelPortal {
    private final long panelId;
    private final ParkingSystemService parkingSystemService = new ParkingSystemServiceImpl();
    private final PaymentService paymentService = new PaymentService();

    public ExitPanelPortal(long panelId) {
        this.panelId = panelId;
    }
    @Override
    public ParkingTicket printTicket(long parkingLotId, Vehicle vehicle) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ParkingTicket scanTicket(String ticketId) {
        ParkingTicket parkingTicket = parkingSystemService.fetchParkingTicket(ticketId, panelId);
        if(parkingSystemService.releaseParkingSpot(parkingTicket.getParkingSpotId())) {
            return parkingTicket;
        }
        return null;
    }

    @Override
    public <T> boolean pay(ParkingTicket parkingTicket, long collectorId, T paymentModeDetails) {
        PaymentTransaction<T> paymentTransaction = paymentService.buildTransaction(parkingTicket, paymentModeDetails, collectorId);
        if(paymentService.initiatePayment(paymentTransaction)) {
            parkingSystemService.updateParkingTicketStatus(parkingTicket.getId(), ParkingTicketStatus.PAID);
            return true;
        }
        return false;
    }
}
