package com.ygo.service;


import com.ygo.model.User;
import com.ygo.model.dto.request.RegisterRequest;

public interface UserService {

    User findByEmail(String email);
    void register(RegisterRequest request);

}
