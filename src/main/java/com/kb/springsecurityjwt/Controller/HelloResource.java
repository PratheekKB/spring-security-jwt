package com.kb.springsecurityjwt.Controller;


import com.kb.springsecurityjwt.Security.CustomUserDetailsService;
import com.kb.springsecurityjwt.Security.MyUser;
import com.kb.springsecurityjwt.Security.MyUserDetails;
import com.kb.springsecurityjwt.Util.JwtUtil;
import com.kb.springsecurityjwt.models.AuthenticationRequest;
import com.kb.springsecurityjwt.models.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/security")

public class HelloResource {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    @GetMapping("")
    public String hello() {
        return "Hello welcome to spring Security JWT";
    }

    @PostMapping(value = "/auth")
    public String authenticateAndGetToken(@RequestBody AuthenticationRequest authenticationRequest) {
        Authentication authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        final UserDetails userDetails = customUserDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        if (authentication.isAuthenticated()) {
            return jwtTokenUtil.generateToken(userDetails);
        } else {
            throw new UsernameNotFoundException("Invalid user request !");
        }
    }

//     public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
//        try{
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
//            );
//        }catch (BadCredentialsException e){
//            throw new Exception("Incorrect username or password", e);
//        }
//
////        User user = userRepository.findUserByUsername((authenticationRequest.getUsername()));
////
////        final String jwt = jwtUtil.generateToken(user.map(MyUserDetails::new).get());
////
////        return ResponseEntity.ok(new AuthenticationResponse(jwt));
//
//        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
//
//        final String jwt = jwtTokenUtil.generateToken(userDetails);
//
//        return ResponseEntity.ok(new AuthenticationResponse(jwt));
}





