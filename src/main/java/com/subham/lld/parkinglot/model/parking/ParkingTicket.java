package com.subham.lld.parkinglot.model.parking;

import com.subham.lld.parkinglot.model.parking.type.ParkingTicketStatus;
import lombok.Builder;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

/**
 * @author subham.paul
 */

@Data
@Builder
public class ParkingTicket {
    private String id;

    private long entryPanelId;

    private long exitPanelId;

    private long parkingSpotId;

    private Vehicle vehicle;

    private LocalDateTime issueTime;

    private LocalDateTime exitTime;

    private double payableAmount;

    private ParkingTicketStatus status;
}
