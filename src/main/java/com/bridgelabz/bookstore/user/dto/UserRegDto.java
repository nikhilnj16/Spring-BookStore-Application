package com.bridgelabz.bookstore.user.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class UserRegDto {
    private String userFirstName;
    private String userLastName;
    private String userEmailId;
    private String userPassword;
}
