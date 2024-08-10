package com.subham.lld.librarymanagement.model.account;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * @author subham.paul
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class Member extends Account {
    private LocalDate dateOfMembership;
}
