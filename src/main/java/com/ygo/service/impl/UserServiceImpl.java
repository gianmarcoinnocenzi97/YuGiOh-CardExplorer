package com.ygo.service.impl;


import com.ygo.integration.enums.ERole;
import com.ygo.integration.mapper.UserMapper;
import com.ygo.model.Role;
import com.ygo.model.User;
import com.ygo.model.dto.request.RegisterRequest;
import com.ygo.repository.RoleRepository;
import com.ygo.repository.UserRepository;
import com.ygo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(RegisterRequest request) {
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use");
        }

        Role role = roleRepository.findByCode(ERole.ROLE_USER.getCode())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Default role not found"
                ));

        User user = userMapper.toUser(request);
        user.getAuth().setPassword(passwordEncoder.encode(request.password()));
        user.getAuth().setUser(user);
        user.setRole(role);
        user.setIsActive(Boolean.TRUE);

        userRepository.save(user);
    }


    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "USER_NOT_FOUND"));
    }

}



