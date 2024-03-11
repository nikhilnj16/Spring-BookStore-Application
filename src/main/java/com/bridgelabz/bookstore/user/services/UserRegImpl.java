package com.bridgelabz.bookstore.user.services;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bridgelabz.bookstore.user.config.EmailSender;
import com.bridgelabz.bookstore.user.config.UserJwt;
import com.bridgelabz.bookstore.user.dto.UserLoginDto;
import com.bridgelabz.bookstore.user.entity.UserEntity;
import com.bridgelabz.bookstore.user.repository.IBookStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.time.LocalDate.parse;

@Service
public class UserRegImpl implements IUserReg {
    @Autowired
    private IBookStoreRepository repo;

    @Autowired
    UserJwt userJwt;

    @Autowired
    EmailSender emailSender;

    public String userRegistration(UserEntity userEntity){
        repo.save(userEntity);
        String body = "Thanks for registering in Book store app."+ " click here to verify you account   " + "http://localhost:8085/login" ;
        String subject = "registered successfully and get your account verified";
        System.out.println(userEntity.getUserEmailId());
        emailSender.sendEmail(userEntity.getUserEmailId(), subject ,body);
        return  "User register successfully";
    }

    public String userLogin(UserLoginDto userLoginDto){
        UserEntity userEntity = repo.findByUsernameAndPassword(userLoginDto.getUserEmailId(), userLoginDto.getUserPassword());

        if(userEntity != null){
            String token = userJwt.createToken(userEntity.getUserFirstName());
            String body = "Successfully registed";
            String subject = "account verified";
            emailSender.sendEmail(userLoginDto.getUserEmailId(),subject,body);
            return "Login successful. JWT Token: " + token;
        } else {
            return "Invalid credentials. login failed";
        }
    }

    public List<UserEntity> getUserByJWT(String token){
        try {
            String userFirstName = userJwt.decodeToken(token);
            UserEntity userEntity = repo.findByFirstName(userFirstName);
            System.out.println(userEntity);
            return Collections.singletonList(userEntity);
        } catch (JWTVerificationException ex){
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }



//    @Autowired
//    private IBookStoreRepository repo;
//    public List<UserLoginDto> getAllUserDetails(){
//        return repo.findAll()
//                .stream()
//                .map(userEntity -> new UserLoginDto(
//                        userEntity.getUserId(),
//                        userEntity.getUserFirstName(),
//                        userEntity.getUserLastName()
//
//                )).collect(Collectors.toList());
//    }
//    public List<UserLoginDto> getUserById(@PathVariable int id){
//       return repo.findById(id).stream()
//               .map(userEntity -> new UserLoginDto(
//                       userEntity.getUserId(),
//                       userEntity.getUserFirstName(),
//                       userEntity.getUserLastName()
//
//               )).collect(Collectors.toList());
//       // return repo.fin>dById(id).get();
//    }
//
//    public void postUser(@RequestBody UserEntity userEntity){
//        repo.save(userEntity);
//    }
//
//    public UserEntity putUserDetails(@PathVariable int id){
//        UserEntity userEntity = repo.findById(id).get();
//        userEntity.setUserFirstName("Nikhil");
//        userEntity.setUserLastName("Jana");
//        repo.save(userEntity);
//        return userEntity;
//    }
//    public void deleteUser(@PathVariable int id){
//        UserEntity userEntity = repo.findById(id).get();
//        repo.delete(userEntity);
//    }

}
