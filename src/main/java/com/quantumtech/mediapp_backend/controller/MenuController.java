package com.quantumtech.mediapp_backend.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quantumtech.mediapp_backend.dto.MenuDTO;
import com.quantumtech.mediapp_backend.model.Menu;
import com.quantumtech.mediapp_backend.service.IMenuService;

@RestController
@RequestMapping("/menus")
public class MenuController {

    @Autowired
    private IMenuService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<MenuDTO>> getAllMenus() throws Exception{
        List<Menu> menus = service.findAll();
        List<MenuDTO> menusDTO = mapper.map(menus, new TypeToken<List<MenuDTO>>() {}.getType());
        return new ResponseEntity<>(menusDTO, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<MenuDTO>> getMenusByUser() throws Exception{
        List<Menu> menus = service.getMenusByUsername();
        List<MenuDTO> menusDTO = mapper.map(menus, new TypeToken<List<MenuDTO>>() {}.getType());
        return new ResponseEntity<>(menusDTO, HttpStatus.OK);
    }

}
