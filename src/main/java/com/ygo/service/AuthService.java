package com.ygo.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {

    UserDetails authenticate(String userEmail);
    UserDetails loadUserByUsername(String username);

}
