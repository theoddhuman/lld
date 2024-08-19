package com.subham.lld.parkinglot.model.parking.type;

import lombok.Getter;

/**
 * @author subham.paul
 */
@Getter
public enum Size {
    LARGE(4),
    SMALL(1),
    MEDIUM(2);

    private final int count;

    Size(int count) {
        this.count = count;
    }
}
