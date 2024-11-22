package com.quantumtech.mediapp_backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.quantumtech.mediapp_backend.model.Menu;
import com.quantumtech.mediapp_backend.repo.IGenericRepo;
import com.quantumtech.mediapp_backend.repo.IMenuRepo;
import com.quantumtech.mediapp_backend.service.IMenuService;


@Service
public class MenuServiceImpl extends CRUDImpl<Menu, Integer> implements IMenuService{

    @Autowired
    private IMenuRepo repo;

    @Override
    protected IGenericRepo<Menu, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<Menu> getMenusByUsername() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return repo.getMenusByUsername(username);
    }

}
