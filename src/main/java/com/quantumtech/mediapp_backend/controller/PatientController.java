package com.quantumtech.mediapp_backend.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.quantumtech.mediapp_backend.dto.PatientDTO;
import com.quantumtech.mediapp_backend.exception.ModelNotFoundException;
import com.quantumtech.mediapp_backend.model.Patient;
import com.quantumtech.mediapp_backend.service.IPatientService;

import jakarta.validation.Valid;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private IPatientService service;

    @Autowired
    private ModelMapper mapper;

    // @GetMapping
    // public ResponseEntity<List<Patient>> findAll() {        
    //     List<Patient> list = service.findAll();
    //     return new ResponseEntity<>(list, HttpStatus.OK);
    // }

    //DTO para no afectar al cliente
    @PreAuthorize("@authServiceImpl.hasAccess('ADMIN')")
    @GetMapping
    public ResponseEntity<List<PatientDTO>> findAll() {
        List<PatientDTO> list = service.findAll().stream().map(p -> mapper.map(p, PatientDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //Paginado  
    @GetMapping("/pageable")
    public ResponseEntity<Page<PatientDTO>> listPage(Pageable pageable) {
        Page<PatientDTO> list = service.listPage(pageable).map(p -> mapper.map(p, PatientDTO.class));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> findById(@PathVariable Integer id) {

        PatientDTO dtoResponse;
        Patient obj = service.findById(id);

        if(obj == null){
            throw new ModelNotFoundException("ID DOES NOT EXIST: " + id);
        }else{
            dtoResponse = mapper.map(obj, PatientDTO.class);
        }
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }    

    // @PostMapping
    // public ResponseEntity<Patient> save(@RequestBody Patient patient) {
    //     return new ResponseEntity<>(service.save(patient), HttpStatus.CREATED);
    // }

    //REST MATURITY LEVEL2
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody PatientDTO dto) {
        Patient p = service.save(mapper.map(dto, Patient.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(p.getIdPatient()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Patient> update(@Valid @RequestBody PatientDTO dto) {
        Patient obj = service.findById(dto.getIdPatient());
        if(obj == null){
            throw new ModelNotFoundException("ID DOES NOT EXIST: " + dto.getIdPatient());
        }
        return new ResponseEntity<>(service.update(mapper.map(dto, Patient.class)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {

        Patient obj = service.findById(id);

        if(obj == null){
            throw new ModelNotFoundException("ID DOES NOT EXIST: " + id);
        }else{
            service.delete(id);
        }        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);        
    }

    @GetMapping("hateoas/{id}")
    public EntityModel<PatientDTO> findByIdHateoas(@PathVariable Integer id) {
        PatientDTO dtoResponse;
        Patient obj = service.findById(id);

        if(obj == null){
            throw new ModelNotFoundException("ID DOES NOT EXIST: " + id);
        }else{
            dtoResponse = mapper.map(obj, PatientDTO.class);
        }

        EntityModel<PatientDTO> resource = EntityModel.of(dtoResponse);

        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findById(id));

        resource.add(link.withRel("patient-info"));

        return resource;
    }    

}
