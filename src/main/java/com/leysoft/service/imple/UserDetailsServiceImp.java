
package com.leysoft.service.imple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.leysoft.entity.CustomUser;
import com.leysoft.repository.CustomUserRepository;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    private static final String USERNAME_NOT_FOUND_MESSAGE = "Username not found";

    @Autowired
    private CustomUserRepository customUserDetailsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUser user = customUserDetailsRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(USERNAME_NOT_FOUND_MESSAGE));
        return new User(user.getUsername(), user.getPassword(), user.getAuthorities());

    }

}
