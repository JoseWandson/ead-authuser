package com.ead.authuser.filters;

import com.ead.authuser.enums.UserStatus;
import com.ead.authuser.enums.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFilter {

    private UserType userType;
    private String email;
    private UserStatus userStatus;
}
