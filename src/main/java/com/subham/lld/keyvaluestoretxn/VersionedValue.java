package com.subham.lld.keyvaluestoretxn;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Author: the_odd_human
 * Date: 14/03/25
 */

@Data
@AllArgsConstructor
public class VersionedValue<T> {
    private T value;
    private Long version;
}
