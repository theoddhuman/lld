package com.subham.lld.parkinglot.model.parking;

import com.subham.lld.parkinglot.model.parking.type.Size;
import com.subham.lld.parkinglot.model.parking.type.UtilityType;
import lombok.Data;

/**
 * @author subham.paul
 */

@Data
public class Vehicle {
    private String number;

    private Size size;

    private UtilityType utilityType;
}
