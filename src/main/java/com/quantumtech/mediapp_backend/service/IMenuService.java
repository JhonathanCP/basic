package com.quantumtech.mediapp_backend.service;

import java.util.List;

import com.quantumtech.mediapp_backend.model.Menu;

public interface IMenuService extends ICRUD<Menu, Integer>{

    List<Menu> getMenusByUsername();

}