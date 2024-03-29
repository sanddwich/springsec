package com.example.springsec.security;

import com.example.springsec.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (userService.findByUsername(username).isEmpty())
            throw new UsernameNotFoundException("User doesn't exist");

        return SecurityUser.fromUser(
          userService.findByUsername(username).stream().findFirst().get()
        );
    }
}
