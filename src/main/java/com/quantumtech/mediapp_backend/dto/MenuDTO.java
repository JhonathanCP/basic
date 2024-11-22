package com.quantumtech.mediapp_backend.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO {

    private Integer idMenu;
    private String icon;
    private String name;
    private String url;
    private List<RoleDTO> roles;
}