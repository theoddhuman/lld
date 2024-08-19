package com.subham.lld.parkinglot.service.parking.impl;

import com.subham.lld.parkinglot.model.parking.ParkingAvailability;
import com.subham.lld.parkinglot.model.parking.ParkingTicket;
import com.subham.lld.parkinglot.model.parking.Vehicle;
import com.subham.lld.parkinglot.model.parking.type.ParkingSpotStatus;
import com.subham.lld.parkinglot.model.parking.type.ParkingTicketStatus;
import com.subham.lld.parkinglot.model.parking.type.Size;
import com.subham.lld.parkinglot.model.parking.type.UtilityType;
import com.subham.lld.parkinglot.repository.ParkingRepository;
import com.subham.lld.parkinglot.repository.ParkingTicketRepository;
import com.subham.lld.parkinglot.service.parking.ParkingSystemService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

/**
 * @author subham.paul
 */
public class ParkingSystemServiceImpl implements ParkingSystemService {
    private final ParkingRepository parkingRepository = new ParkingRepository();
    private final ParkingTicketRepository parkingTicketRepository = new ParkingTicketRepository();

    @Override
    public ParkingAvailability getAvailabilityBySizeAndUtility(long parkingLotId, Size size, UtilityType utilityType) {
        return parkingRepository.getAvailabilityBySizeAndUtility(parkingLotId, size, utilityType);
    }

    @Override
    public boolean updateStatusParkingSpot(long parkingSpotId, ParkingSpotStatus status) {
        return parkingRepository.updateStatusParkingSpot(parkingSpotId, status);
    }

    @Override
    public boolean assignParkingSpot(ParkingAvailability parkingAvailability, Vehicle vehicle) {
        if(updateStatusParkingSpot(parkingAvailability.getParkingSpotId(), ParkingSpotStatus.OCCUPIED)) {
            return parkingRepository.assignVehicle(parkingAvailability.getParkingSpotId());
        }
        return false;
    }

    @Override
    public ParkingTicket buildParkingTicket(ParkingAvailability parkingAvailability, Vehicle vehicle, long panelId) {
        ParkingTicket parkingTicket = ParkingTicket.builder()
                .parkingSpotId(parkingAvailability.getParkingSpotId())
                .vehicle(vehicle)
                .entryPanelId(panelId)
                .issueTime(LocalDateTime.now())
                .build();
        return parkingTicketRepository.saveOrUpdate(parkingTicket);
    }

    @Override
    public ParkingTicket fetchParkingTicket(String id, long panelId) {
        ParkingTicket parkingTicket = parkingTicketRepository.get(id);
        Vehicle vehicle = parkingTicket.getVehicle();
        if(ParkingTicketStatus.ACTIVE.equals(parkingTicket.getStatus())) {
            parkingTicket.setExitTime(LocalDateTime.now());
            parkingTicket.setExitPanelId(panelId);
            int hours = (int) (ChronoUnit.HOURS.between(parkingTicket.getIssueTime(), parkingTicket.getIssueTime()) + 1);
            double rate = getRate(vehicle.getSize(), vehicle.getUtilityType());
            parkingTicket.setPayableAmount(hours * rate);
            parkingTicket.setStatus(ParkingTicketStatus.BILLED);
            parkingTicketRepository.saveOrUpdate(parkingTicket);
        }
        return parkingTicket;
    }

    @Override
    public void updateParkingTicketStatus(String id, ParkingTicketStatus status) {
        parkingTicketRepository.updateStatus(id, status);
    }

    @Override
    public double getRate(Size size, UtilityType utilityType) {
        return parkingRepository.getRate(size, utilityType);
    }

    @Override
    public boolean releaseParkingSpot(long parkingSpotId) {
       if(parkingRepository.updateStatusParkingSpot(parkingSpotId, ParkingSpotStatus.AVAILABLE)) {
           return parkingRepository.releaseVehicle(parkingSpotId);
       }
       return false;
    }

    public void displayDashboardByFloor(long parkingFloorId) {
        Map<String, List<Long>> availabilityByFloor = parkingRepository.getAvailabilityByFloor(parkingFloorId);
        System.out.println("Available spots:  ");
        for(Map.Entry<String, List<Long>> availability : availabilityByFloor.entrySet()) {
            System.out.println(availability.getKey() + ": " + availability.getValue().size());
        }
    }
}
