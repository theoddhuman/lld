package com.subham.lld.parkinglot;

import com.subham.lld.parkinglot.model.account.Account;
import com.subham.lld.parkinglot.model.account.type.Role;
import com.subham.lld.parkinglot.model.parking.ParkingFloor;
import com.subham.lld.parkinglot.model.parking.ParkingLot;
import com.subham.lld.parkinglot.model.parking.ParkingSpot;
import com.subham.lld.parkinglot.model.parking.ParkingTicket;
import com.subham.lld.parkinglot.model.parking.Vehicle;
import com.subham.lld.parkinglot.model.parking.type.PanelType;
import com.subham.lld.parkinglot.model.parking.type.Size;
import com.subham.lld.parkinglot.model.parking.type.UtilityType;
import com.subham.lld.parkinglot.model.payment.CCPaymentModeDetails;
import com.subham.lld.parkinglot.repository.AccountRepository;
import com.subham.lld.parkinglot.service.AdminService;
import com.subham.lld.parkinglot.service.account.AccountService;
import com.subham.lld.parkinglot.service.account.impl.AccountServiceImpl;
import com.subham.lld.parkinglot.service.impl.AdminServiceImpl;
import com.subham.lld.parkinglot.service.parking.PanelPortal;
import com.subham.lld.parkinglot.service.parking.ParkingCatalogService;
import com.subham.lld.parkinglot.service.parking.ParkingSystemService;
import com.subham.lld.parkinglot.service.parking.impl.EntryPanelPortal;
import com.subham.lld.parkinglot.service.parking.impl.ExitPanelPortal;
import com.subham.lld.parkinglot.service.parking.impl.ParkingCatalogServiceImpl;
import com.subham.lld.parkinglot.service.parking.impl.ParkingSystemServiceImpl;

/**
 * @author subham.paul
 *
 * What if there are multiple entry panels, and trying to get the available spot?
 * We can lock parking spot status updating, and check current status before updating, it will ensure once a
 * thread enters the monitor and update the status, next thread will find that the status is already changed.
 */
public class Test {
    public static void main(String[] args) {
        /************* Add admin ***********************/
        AccountRepository accountRepository = new AccountRepository();
        AccountService accountService = new AccountServiceImpl();
        accountService.addAccount(createAccount("Subham", Role.ADMIN));

        /******** Add parking attendants ***************/
        AdminService adminService = new AdminServiceImpl();
        Account parkingAttendant1 = adminService.addParkingAttendant(1L, createAccount("amit", Role.PARKING_ATTENDANT));
        Account parkingAttendant2 = adminService.addParkingAttendant(1L, createAccount("dilip", Role.PARKING_ATTENDANT));

        //System.out.println(accountService.viewAll());

        /**************** Setting up parking lot ************************/

        //Add parking lot
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setAddress("India");
        adminService.addParkingLot(1L, parkingLot);

        //Add parking floor
        adminService.addParkingFloor(1L, ParkingFloor.builder().name("1").parkingLotId(1L).build());
        adminService.addParkingFloor(1L, ParkingFloor.builder().name("2").parkingLotId(1L).build());

        //Add panel
        adminService.addPanel(1L, 1L, com.subham.lld.parkinglot.model.parking.Panel.builder().parkingLotId(1L).type(PanelType.ENTRY).build());
        adminService.addPanel(1L, 1L, com.subham.lld.parkinglot.model.parking.Panel.builder().parkingLotId(1L).type(PanelType.EXIT).build());
        adminService.addPanel(1L, 1L, com.subham.lld.parkinglot.model.parking.Panel.builder().parkingLotId(1L).type(PanelType.EXIT).build());

        //Add parking spot
        adminService.addParkingSpot(1L, createParkingSpot("1", Size.SMALL, UtilityType.NORMAL, 1L));
        adminService.addParkingSpot(1L, createParkingSpot("2", Size.SMALL, UtilityType.NORMAL, 1L));
        adminService.addParkingSpot(1L, createParkingSpot("3", Size.SMALL, UtilityType.NORMAL, 1L));
        adminService.addParkingSpot(1L, createParkingSpot("4", Size.SMALL, UtilityType.NORMAL, 1L));
        adminService.addParkingSpot(1L,  createParkingSpot("5", Size.SMALL, UtilityType.NORMAL, 1L));
        adminService.addParkingSpot(1L,  createParkingSpot("6", Size.MEDIUM, UtilityType.NORMAL, 1L));
        adminService.addParkingSpot(1L, createParkingSpot("7", Size.MEDIUM, UtilityType.NORMAL, 1L));
        adminService.addParkingSpot(1L,createParkingSpot("8", Size.MEDIUM, UtilityType.NORMAL, 1L));
        ParkingSpot parkingSpot1 = createParkingSpot("9", Size.SMALL, UtilityType.ELECTRIC, 1L);
        parkingSpot1.setElectricPanel(com.subham.lld.parkinglot.model.parking.Panel.builder().type(PanelType.ELECTRIC).build());
        adminService.addParkingSpot(1L,parkingSpot1);

        adminService.addParkingSpot(1L, createParkingSpot("1", Size.SMALL, UtilityType.NORMAL, 2L));
        adminService.addParkingSpot(1L, createParkingSpot("2", Size.SMALL, UtilityType.NORMAL, 2L));
        adminService.addParkingSpot(1L,  createParkingSpot("3", Size.SMALL, UtilityType.NORMAL, 2L));
        adminService.addParkingSpot(1L, createParkingSpot("4", Size.MEDIUM, UtilityType.NORMAL, 2L));
        adminService.addParkingSpot(1L, createParkingSpot("5", Size.MEDIUM, UtilityType.NORMAL, 2L));
        adminService.addParkingSpot(1L, createParkingSpot("6", Size.MEDIUM, UtilityType.NORMAL, 2L));

        adminService.updateRate(Size.SMALL, UtilityType.NORMAL, 20.0);
        adminService.updateRate(Size.SMALL, UtilityType.ELECTRIC, 25.0);
        adminService.updateRate(Size.LARGE, UtilityType.NORMAL, 50.0);

        ParkingCatalogService parkingCatalogService = new ParkingCatalogServiceImpl();
        //parkingCatalogService.viewParkingLot(1L);
        parkingCatalogService.calculateParkingAvailability(1L);
        ParkingSystemService parkingSystemService = new ParkingSystemServiceImpl();
        parkingSystemService.displayDashboardByFloor(1L);

        /*************** entry *********************************/
        PanelPortal entryPanelPortal = new EntryPanelPortal(1L);

        Vehicle vehicle1 = buildVehicle("AXFE3456", Size.SMALL, UtilityType.NORMAL);
        ParkingTicket parkingTicket1 = entryPanelPortal.printTicket(1L, vehicle1);
        System.out.println(parkingTicket1);

        Vehicle vehicle2 = buildVehicle("ABCFE3456", Size.LARGE, UtilityType.NORMAL);
        ParkingTicket parkingTicket2 = entryPanelPortal.printTicket(1L, vehicle2);

        Vehicle vehicle3 = buildVehicle("AXFE3111", Size.SMALL, UtilityType.ELECTRIC);
        ParkingTicket parkingTicket3 = entryPanelPortal.printTicket(1L, vehicle3);
        System.out.println(parkingTicket3);

        parkingSystemService.displayDashboardByFloor(1L);
        //parkingCatalogService.viewParkingLot(1L);

        /****************** exit ******************************/
        //scan ticket
        PanelPortal exitPanelPortal = new ExitPanelPortal(2L);
        parkingTicket1 = exitPanelPortal.scanTicket(parkingTicket1.getId());

        //payment
        while(true) {
            CCPaymentModeDetails paymentModeDetails = new CCPaymentModeDetails();
            paymentModeDetails.setCardNo("1234567891234");
            paymentModeDetails.setCvv(234);
            if(exitPanelPortal.pay(parkingTicket1, parkingAttendant1.getId(), paymentModeDetails)) {
                break;
            }
        }
//        PaymentService paymentService = new PaymentService();
//        System.out.println(paymentService.viewTransactions());
//        System.out.println(parkingTicket1);
//        parkingCatalogService.viewParkingLot(1L);
        parkingSystemService.displayDashboardByFloor(1L);

    }

    private static Vehicle buildVehicle(String number, Size size, UtilityType utilityType) {
        Vehicle vehicle = new Vehicle();
        vehicle.setNumber(number);
        vehicle.setSize(size);
        vehicle.setUtilityType(utilityType);
        return vehicle;
    }

    private static ParkingSpot createParkingSpot(String number, Size size, UtilityType utilityType, long parkingFloorId) {
        return ParkingSpot.builder()
                .number(number)
                .size(size)
                .parkingFloorId(parkingFloorId)
                .utilityType(utilityType)
                .build();
    }

    private static Account createAccount(String userName, Role role) {
        return Account.builder()
                .userName(userName)
                .password("test")
                .role(role)
                .build();
    }
}
