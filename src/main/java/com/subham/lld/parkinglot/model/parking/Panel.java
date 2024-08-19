package com.subham.lld.parkinglot.model.parking;

import com.subham.lld.parkinglot.model.parking.type.PanelType;
import lombok.Builder;
import lombok.Data;

/**
 * @author subham.paul
 */

@Data
@Builder
public class Panel {
    private long id;

    private PanelType type;

    private long parkingLotId;
}
