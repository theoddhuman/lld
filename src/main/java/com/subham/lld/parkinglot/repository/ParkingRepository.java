package com.subham.lld.parkinglot.repository;

import com.subham.lld.parkinglot.model.parking.Panel;
import com.subham.lld.parkinglot.model.parking.ParkingAvailability;
import com.subham.lld.parkinglot.model.parking.ParkingFloor;
import com.subham.lld.parkinglot.model.parking.ParkingLot;
import com.subham.lld.parkinglot.model.parking.ParkingSpot;
import com.subham.lld.parkinglot.model.parking.type.ParkingSpotStatus;
import com.subham.lld.parkinglot.model.parking.type.Size;
import com.subham.lld.parkinglot.model.parking.type.UtilityType;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author subham.paul
 */
public class ParkingRepository {
    private static long parkingLotId = 0;
    private static long parkingFloorId = 0;
    private static long parkingSpotId = 0;
    private static long panelId = 0;

    private static final Map<Long, ParkingLot> parkingLotCatalog = new HashMap<>();
    private static final Map<Long, List<Long>> parkingLotToFloorMap = new HashMap<>();
    private static final Map<Long, ParkingFloor> parkingFloorCatalog = new HashMap<>();
    private static final Map<Long, List<Long>> parkingFloorToSpotMap = new HashMap<>();
    private static final Map<Long, ParkingSpot> parkingSpotCatalog= new HashMap<>();

    private static final Map<Long, Panel> panelCatalog = new HashMap<>();

    private static final Map<Long, Map<String, List<Long>>> parkingAvailability = new HashMap<>();
    private static final Map<Size, Map<UtilityType, Double>> rates = new HashMap<>();

    public void saveParkingLot(ParkingLot parkingLot) {
        parkingLot.setId(++parkingLotId);
        parkingLotToFloorMap.put(parkingLot.getId(), new ArrayList<>());
        parkingLotCatalog.put(parkingLot.getId(), parkingLot);
    }

    public void saveParkingFloor(ParkingFloor parkingFloor) {
        parkingFloor.setId(++parkingFloorId);
        parkingFloorCatalog.put(parkingFloor.getId(), parkingFloor);
        parkingLotToFloorMap.get(parkingFloor.getParkingLotId()).add(parkingFloor.getId());
        parkingFloorToSpotMap.put(parkingFloor.getId(), new ArrayList<>());
    }

    public void deleteParkingFloor(long parkingFloorId) {
        ParkingFloor parkingFloor = parkingFloorCatalog.get(parkingFloorId);
        parkingFloorCatalog.remove(parkingFloorId);
        parkingLotToFloorMap.get(parkingFloor.getParkingLotId()).remove(parkingFloorId);
    }

    public void saveParkingSpot(ParkingSpot parkingSpot) {
        parkingSpot.setId(++parkingSpotId);
        parkingSpot.setStatus(ParkingSpotStatus.AVAILABLE);
        parkingSpotCatalog.put(parkingSpot.getId(), parkingSpot);
        parkingFloorToSpotMap.get(parkingSpot.getParkingFloorId()).add(parkingSpot.getId());
    }

    public void updateParkingSpot(ParkingSpot parkingSpot) {
        parkingSpotCatalog.put(parkingSpot.getId(), parkingSpot);
    }

    public ParkingSpot getParkingSpot(long id) {
        return parkingSpotCatalog.get(id);
    }

    public void deleteParkingSpot(long parkingSpotId) {
        ParkingSpot parkingSpot = parkingSpotCatalog.get(parkingSpotId);
        parkingSpotCatalog.remove(parkingSpotId);
        parkingFloorToSpotMap.get(parkingSpot.getParkingFloorId()).remove(parkingSpotId);
    }

    public void savePanel(Panel panel) {
        panel.setId(++panelId);
        panelCatalog.put(panel.getId(), panel);
    }

    public void deletePanel(String panelId) {
        panelCatalog.remove(panelId);
    }

    public ParkingLot get(long id) {
        return parkingLotCatalog.get(id);
    }

    public List<ParkingFloor> getParkingFloorByParkingLotId(long parkingLotId) {
        return parkingFloorCatalog.values().stream()
                .filter(parkingFloor -> parkingFloor.getParkingLotId() == parkingLotId)
                .collect(Collectors.toList());
    }

    public List<ParkingSpot> getParkingSpotByParkingFloorId(long parkingFloorId) {
        return parkingSpotCatalog.values().stream()
                .filter(parkingSpot -> parkingSpot.getParkingFloorId()== parkingFloorId)
                .collect(Collectors.toList());
    }

    public void calculateParkingAvailability(long parkingLotId) {
        for(Long parkingFloorId : parkingLotToFloorMap.get(parkingLotId)) {
            ParkingFloor parkingFloor = parkingFloorCatalog.get(parkingFloorId);
            Map<String, List<Long>> availabilityMap = new HashMap<>();
            for(Size size : Size.values()) {
                for(UtilityType utilityType : UtilityType.values()) {
                    availabilityMap.put(String.join("_", size.name(), utilityType.name()), new ArrayList<>());
                }
            }
            for(Long parkingSpotId : parkingFloorToSpotMap.get(parkingFloorId)) {
                ParkingSpot parkingSpot = parkingSpotCatalog.get(parkingSpotId);
                if(ParkingSpotStatus.AVAILABLE.equals(parkingSpot.getStatus())) {
                    String availabilityKey = String.join("_", parkingSpot.getSize().name(), parkingSpot.getUtilityType().name());
                    availabilityMap.get(availabilityKey).add(parkingSpot.getId());
                }
            }
            parkingAvailability.put(parkingFloor.getId(), availabilityMap);
        }
    }

    public Map<String, List<Long>> getAvailabilityByFloor(long parkingFloorId) {
        return parkingAvailability.get(parkingFloorId);
    }

    public ParkingAvailability getAvailabilityBySizeAndUtility(long parkingLotId, Size size, UtilityType utilityType) {
        for(Long parkingFloorId : parkingLotToFloorMap.get(parkingLotId)) {
            ParkingFloor parkingFloor = parkingFloorCatalog.get(parkingFloorId);
            List<Long> parkingSpots = parkingAvailability.get(parkingFloor.getId()).get(String.join("_", size.name(), utilityType.name()));
            if(!CollectionUtils.isEmpty(parkingSpots)) {
                ParkingAvailability parkingAvailability = new ParkingAvailability();
                parkingAvailability.setParkingFloorId(parkingFloor.getId());
                parkingAvailability.setParkingSpotId(parkingSpots.get(0));
                return parkingAvailability;
            }
        }
        return null;
    }

    public boolean updateStatusParkingSpot(long parkingSpotId, ParkingSpotStatus status) {
        synchronized (this) {
            ParkingSpot parkingSpot = parkingSpotCatalog.get(parkingSpotId);
            if (!status.equals(parkingSpot.getStatus())) {
                parkingSpot.setStatus(status);
                return true;
            }
        }
        return false;
    }

    public boolean assignVehicle(long parkingSpotId) {
        ParkingSpot parkingSpot = parkingSpotCatalog.get(parkingSpotId);
        List<Long> parkingSpots = parkingAvailability.get(parkingSpot.getParkingFloorId())
                .get(String.join("_", parkingSpot.getSize().name(), parkingSpot.getUtilityType().name()));
        parkingSpots.remove(parkingSpotId);
        return true;
    }

    public boolean releaseVehicle(long parkingSpotId) {
        ParkingSpot parkingSpot = parkingSpotCatalog.get(parkingSpotId);
        List<Long> parkingSpots = parkingAvailability.get(parkingSpot.getParkingFloorId())
                .get(String.join("_", parkingSpot.getSize().name(), parkingSpot.getUtilityType().name()));
        parkingSpots.add(parkingSpotId);
        return true;
    }

    public void updateRate(Size size, UtilityType utilityType, double rate) {
        if(CollectionUtils.isEmpty(rates.get(size))) {
            rates.put(size, new HashMap<>());
        }
        rates.get(size).put(utilityType, rate);
    }

    public double getRate(Size size, UtilityType utilityType) {
        return rates.get(size).get(utilityType);
    }
}
