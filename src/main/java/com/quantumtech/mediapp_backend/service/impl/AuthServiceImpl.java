package com.quantumtech.mediapp_backend.service.impl;

import java.util.Arrays;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Slf4j
@Service
public class AuthServiceImpl { //Para uso de acceso usando @PreAuthorize

    public boolean hasAccess(String path){
        var rpta = false;
        val methodRole = switch (path) {
            case "ADMIN" -> "ADMIN";
            case "USER" -> "USER,DBA";
            default -> "";
        };

        val methodRoles = methodRole.split(",");
        val auth = SecurityContextHolder.getContext().getAuthentication();
        for(GrantedAuthority gra : auth.getAuthorities()){
            val rolUser = gra.getAuthority();
            log.info(rolUser);
            log.info(auth.getName());
            if (Arrays.stream(methodRoles).anyMatch(rolUser::equalsIgnoreCase))
                rpta = true;
        }
        return rpta;
    }
}