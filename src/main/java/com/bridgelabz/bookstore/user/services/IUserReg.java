package com.bridgelabz.bookstore.user.services;

import com.bridgelabz.bookstore.user.dto.UserLoginDto;
import com.bridgelabz.bookstore.user.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IUserReg {

    String userRegistration(UserEntity userEntity);
    String userLogin(UserLoginDto userLoginDto);

    List<UserEntity> getUserByJWT(String token);

//    List<UserLoginDto> getAllUserDetails();
//    List<UserLoginDto> getUserById(@PathVariable int id);
//
//    void postUser(@RequestBody UserEntity userEntity);
//    UserEntity putUserDetails(@PathVariable int id);
//    void deleteUser(@PathVariable int id);
}
