package com.kb.springsecurityjwt.Controller;

import com.kb.springsecurityjwt.Security.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public  interface UserRepository extends JpaRepository<MyUser, Integer> {
    Optional<MyUser> findUserByUsername(String username);
}
