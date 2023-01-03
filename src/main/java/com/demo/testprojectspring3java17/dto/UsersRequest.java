package com.demo.testprojectspring3java17.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsersRequest {
	private String username;
	private String password;
    private String roles;
}
