package com.springbootjwt.security.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChangeUserInfoRequest {
    private String firstName;
    private String lastName;
    private String email;
}
