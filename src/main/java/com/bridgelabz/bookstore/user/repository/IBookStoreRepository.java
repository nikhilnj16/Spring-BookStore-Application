package com.bridgelabz.bookstore.user.repository;

import com.bridgelabz.bookstore.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IBookStoreRepository extends JpaRepository<UserEntity, Integer> {
    @Query("SELECT u FROM UserEntity u WHERE u.userEmailId = :userEmailId AND u.userPassword = :userPassword")
    UserEntity findByUsernameAndPassword(@Param("userEmailId") String userEmailId, @Param("userPassword") String userPassword);

    @Query("SELECT u from UserEntity u where u.userFirstName = :userFirstName")
    UserEntity findByFirstName(@Param("userFirstName") String userFirstName);
}
