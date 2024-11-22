package com.quantumtech.mediapp_backend.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.quantumtech.mediapp_backend.service.IMedicService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.quantumtech.mediapp_backend.dto.MedicDTO;
import com.quantumtech.mediapp_backend.exception.ModelNotFoundException;
import com.quantumtech.mediapp_backend.model.Medic;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/medics")
public class MedicController {

    @Autowired
    private IMedicService service;

    @Autowired
    private ModelMapper mapper;

    // @GetMapping
    // public ResponseEntity<List<Medic>> findAll() {        
    //     List<Medic> list = service.findAll();
    //     return new ResponseEntity<>(list, HttpStatus.OK);
    // }

    //DTO para no afectar al cliente
    @GetMapping
    public ResponseEntity<List<MedicDTO>> findAll() {
        List<MedicDTO> list = service.findAll().stream().map(p -> mapper.map(p, MedicDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicDTO> findById(@PathVariable Integer id) {

        MedicDTO dtoResponse;
        Medic obj = service.findById(id);

        if(obj == null){
            throw new ModelNotFoundException("ID DOES NOT EXIST: " + id);
        }else{
            dtoResponse = mapper.map(obj, MedicDTO.class);
        }
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }    

    // @PostMapping
    // public ResponseEntity<Medic> save(@RequestBody Medic medic) {
    //     return new ResponseEntity<>(service.save(medic), HttpStatus.CREATED);
    // }

    //REST MATURITY LEVEL2
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody MedicDTO dto) {
        Medic p = service.save(mapper.map(dto, Medic.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(p.getIdMedic()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Medic> update(@Valid @RequestBody MedicDTO dto) {
        Medic obj = service.findById(dto.getIdMedic());
        if(obj == null){
            throw new ModelNotFoundException("ID DOES NOT EXIST: " + dto.getIdMedic());
        }
        return new ResponseEntity<>(service.update(mapper.map(dto, Medic.class)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {

        Medic obj = service.findById(id);

        if(obj == null){
            throw new ModelNotFoundException("ID DOES NOT EXIST: " + id);
        }else{
            service.delete(id);
        }        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);        
    }

    @GetMapping("hateoas/{id}")
    public EntityModel<MedicDTO> findByIdHateoas(@PathVariable Integer id) {
        MedicDTO dtoResponse;
        Medic obj = service.findById(id);

        if(obj == null){
            throw new ModelNotFoundException("ID DOES NOT EXIST: " + id);
        }else{
            dtoResponse = mapper.map(obj, MedicDTO.class);
        }

        EntityModel<MedicDTO> resource = EntityModel.of(dtoResponse);

        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findById(id));

        resource.add(link.withRel("medic-info"));

        return resource;
    }    

}
