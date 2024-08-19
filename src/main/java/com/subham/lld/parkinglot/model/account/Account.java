package com.subham.lld.parkinglot.model.account;

import com.subham.lld.parkinglot.model.account.type.AccountStatus;
import com.subham.lld.parkinglot.model.account.type.LoginStatus;
import com.subham.lld.parkinglot.model.account.type.Role;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author subham.paul
 */

@Data
@Builder
public class Account {
    private Long id;

    private String userName;

    private String password;

    private Role role;

    private AccountStatus status;

    private LocalDate creationTime;

    private LocalDate lastLoginTime;

    private LoginStatus loginStatus;
}
