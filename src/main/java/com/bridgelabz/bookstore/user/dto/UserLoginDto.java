package com.bridgelabz.bookstore.user.dto;




import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserLoginDto {
        private String userEmailId;
        private String userPassword;

}


