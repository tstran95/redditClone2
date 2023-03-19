package com.vn.redditclone.repository;

import com.vn.redditclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User , Long> {
    @Query(value = "SELECT * FROM user u WHERE u.username = :username", nativeQuery = true)
    User findByUserName(@Param(value = "username") String username);

    @Query(value = "SELECT * FROM user u WHERE u.email = :email", nativeQuery = true)
    User findByEmail(@Param(value = "email") String email);
}
