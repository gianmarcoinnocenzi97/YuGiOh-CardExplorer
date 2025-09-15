package com.ygo.service;


import com.ygo.model.User;

public interface UserService {

    User findByEmail(String email);

}
