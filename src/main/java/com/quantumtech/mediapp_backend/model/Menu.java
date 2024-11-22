package com.quantumtech.mediapp_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {

    @Id
    private Integer idMenu;

    @Column(length = 20)
    private String icon;
    
    @Column(length = 20)
    private String name;

    @Column(length = 50)
    private String url;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "menu_role", 
        joinColumns = @JoinColumn(name = "id_menu", referencedColumnName = "idMenu"),
        inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "idRole"))
    private List<Role> roles;

}
