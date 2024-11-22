package com.quantumtech.mediapp_backend.service;

import com.quantumtech.mediapp_backend.model.User;

public interface ILoginService {

    User checkUsername(String username);
    void changePassword(String password, String username);
}
