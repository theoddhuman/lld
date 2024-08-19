package com.subham.lld.parkinglot.service.impl;

import com.subham.lld.parkinglot.model.account.Account;
import com.subham.lld.parkinglot.model.account.type.Role;
import com.subham.lld.parkinglot.model.parking.Panel;
import com.subham.lld.parkinglot.model.parking.ParkingFloor;
import com.subham.lld.parkinglot.model.parking.ParkingLot;
import com.subham.lld.parkinglot.model.parking.ParkingSpot;
import com.subham.lld.parkinglot.model.parking.type.Size;
import com.subham.lld.parkinglot.model.parking.type.UtilityType;
import com.subham.lld.parkinglot.service.AdminService;
import com.subham.lld.parkinglot.service.account.AccountService;
import com.subham.lld.parkinglot.service.account.impl.AccountServiceImpl;
import com.subham.lld.parkinglot.service.parking.ParkingCatalogService;
import com.subham.lld.parkinglot.service.parking.impl.ParkingCatalogServiceImpl;

/**
 * @author subham.paul
 */
public class AdminServiceImpl implements AdminService {
    private final AccountService accountService;

    private final ParkingCatalogService parkingCatalogService;

    public AdminServiceImpl() {
        accountService = new AccountServiceImpl();
        parkingCatalogService = new ParkingCatalogServiceImpl();
    }
    @Override
    public Account addParkingAttendant(long adminId, Account account) {
        Account admin = accountService.view(adminId);
        if(Role.ADMIN.equals(admin.getRole())) {
            return accountService.addAccount(account);
        }
        return null;
    }

    @Override
    public void removeParkingAttendant(long adminId, long parkingAttendantId) {
        Account admin = accountService.view(adminId);
        if(Role.ADMIN.equals(admin.getRole())) {
            accountService.deleteAccount(parkingAttendantId);
        }
    }

    @Override
    public void addParkingLot(long adminId, ParkingLot parkingLot) {
        Account admin = accountService.view(adminId);
        if(Role.ADMIN.equals(admin.getRole())) {
            parkingCatalogService.addParkingLot(parkingLot);
        }
    }

    @Override
    public void addParkingFloor(long adminId, ParkingFloor parkingFloor) {
        Account admin = accountService.view(adminId);
        if(Role.ADMIN.equals(admin.getRole())) {
            parkingCatalogService.addParkingFloor(parkingFloor);
        }
    }

    @Override
    public void removeParkingFloor(long adminId, long parkingFloorId) {
        Account admin = accountService.view(adminId);
        if(Role.ADMIN.equals(admin.getRole())) {
            parkingCatalogService.removeParkingFloor(parkingFloorId);
        }
    }

    @Override
    public void addParkingSpot(long adminId, ParkingSpot parkingSpot) {
        Account admin = accountService.view(adminId);
        if(Role.ADMIN.equals(admin.getRole())) {
            parkingCatalogService.addParkingSpot(parkingSpot);
        }
    }

    @Override
    public void removeParkingSpot(long adminId, long parkingSpotId) {
        Account admin = accountService.view(adminId);
        if(Role.ADMIN.equals(admin.getRole())) {
            parkingCatalogService.removeParkingSpot(parkingSpotId);
        }
    }

    @Override
    public void addPanel(long adminId, long parkingLotId, Panel panel) {
        Account admin = accountService.view(adminId);
        if(Role.ADMIN.equals(admin.getRole())) {
            parkingCatalogService.addPanel(panel);
        }
    }

    @Override
    public void removePanel(long adminId, long parkingLotId, String panelId) {
        Account admin = accountService.view(adminId);
        if(Role.ADMIN.equals(admin.getRole())) {
            parkingCatalogService.removePanel(panelId);
        }
    }

    @Override
    public void updateRate(Size size, UtilityType utilityType, double rate) {
        parkingCatalogService.updateRate(size, utilityType, rate);
    }
}
