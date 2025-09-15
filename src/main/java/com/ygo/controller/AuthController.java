package com.ygo.controller;


import com.ygo.common.AppConstants;
import com.ygo.config.JwtUtils;
import com.ygo.model.User;
import com.ygo.model.dto.JwtRequest;
import com.ygo.service.AuthService;
import com.ygo.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping(AppConstants.AUTH_URL)
@RestController
public class AuthController {

    private final AuthService authService;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody JwtRequest request, HttpServletResponse response) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        UserDetails userDetails = authService.authenticate(request.getEmail());
        User user = userService.findByEmail(request.getEmail());
        String jwt = jwtUtils.generateClaims(userDetails, user);
        response.setHeader("Authorization", "Bearer " + jwt);
        return ResponseEntity.ok().build();
    }


}
