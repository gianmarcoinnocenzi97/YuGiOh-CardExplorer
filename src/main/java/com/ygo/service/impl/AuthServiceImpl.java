package com.ygo.service.impl;

import com.ygo.model.Auth;
import com.ygo.repository.AuthRepository;
import com.ygo.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return authenticate(username);
    }

    @Override
    public UserDetails authenticate(String userEmail) {
        Auth auth = authRepository.findByUserEmail(userEmail.toLowerCase())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Utente non trovato"));
        return new User(auth.getUser().getEmail(), auth.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(auth.getUser().getRole().getDescription()))
        );
    }
}
