package com.quantumtech.mediapp_backend.service.impl;

import com.quantumtech.mediapp_backend.model.User;
import com.quantumtech.mediapp_backend.repo.ILoginRepo;
import com.quantumtech.mediapp_backend.service.ILoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements ILoginService {

    private final ILoginRepo repo;
    private final PasswordEncoder bcrypt;

    @Override
    public User checkUsername(String username) {
        return repo.checkUsername(username);
    }

    @Override
    public void changePassword(String password, String username) {
        repo.changePassword(bcrypt.encode(password), username);
    }
}
