package com.kb.springsecurityjwt.Security;

import com.kb.springsecurityjwt.Controller.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> user = userRepository.findUserByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("User "+username+" not found!"));

        return  user.map(MyUserDetails::new).get();
    }
}
