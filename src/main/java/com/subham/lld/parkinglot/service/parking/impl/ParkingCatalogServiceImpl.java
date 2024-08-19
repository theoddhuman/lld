package com.subham.lld.parkinglot.service.parking.impl;

import com.subham.lld.parkinglot.model.parking.Panel;
import com.subham.lld.parkinglot.model.parking.ParkingFloor;
import com.subham.lld.parkinglot.model.parking.ParkingLot;
import com.subham.lld.parkinglot.model.parking.ParkingSpot;
import com.subham.lld.parkinglot.model.parking.type.Size;
import com.subham.lld.parkinglot.model.parking.type.UtilityType;
import com.subham.lld.parkinglot.repository.ParkingRepository;
import com.subham.lld.parkinglot.service.parking.ParkingCatalogService;

import java.util.List;

/**
 * @author subham.paul
 */
public class ParkingCatalogServiceImpl implements ParkingCatalogService {
    private final ParkingRepository parkingRepository;

    public ParkingCatalogServiceImpl() {
        parkingRepository = new ParkingRepository();
    }

    @Override
    public void addParkingLot(ParkingLot parkingLot) {
        parkingRepository.saveParkingLot(parkingLot);
    }

    @Override
    public void addParkingFloor(ParkingFloor parkingFloor) {
        parkingRepository.saveParkingFloor(parkingFloor);
    }

    @Override
    public void removeParkingFloor(long parkingFloorId) {
        parkingRepository.deleteParkingFloor(parkingFloorId);
    }

    @Override
    public void addParkingSpot(ParkingSpot parkingSpot) {
        parkingRepository.saveParkingSpot(parkingSpot);
    }

    @Override
    public void removeParkingSpot(long parkingSpotId) {
        parkingRepository.deleteParkingSpot(parkingSpotId);
    }

    @Override
    public void addPanel(Panel panel) {
        parkingRepository.savePanel(panel);
    }

    @Override
    public void removePanel(String panelId) {
        parkingRepository.deletePanel(panelId);
    }

    @Override
    public void viewParkingLot(long parkingLotId) {
        ParkingLot parkingLot = parkingRepository.get(parkingLotId);
        System.out.println(parkingLot);
        System.out.println("--------------------");
        List<ParkingFloor> parkingFloors = parkingRepository.getParkingFloorByParkingLotId(parkingLotId);
        for(ParkingFloor parkingFloor : parkingFloors) {
            System.out.println(parkingFloor);
            System.out.println("...............");
            List<ParkingSpot> parkingSpots = parkingRepository.getParkingSpotByParkingFloorId(parkingFloor.getId());
            for(ParkingSpot parkingSpot : parkingSpots) {
                System.out.println(parkingSpot);
            }
        }
    }

    @Override
    public void calculateParkingAvailability(long parkingLotId) {
        parkingRepository.calculateParkingAvailability(parkingLotId);
    }

    @Override
    public void updateRate(Size size, UtilityType utilityType, double rate) {
        parkingRepository.updateRate(size, utilityType, rate);
    }
}
