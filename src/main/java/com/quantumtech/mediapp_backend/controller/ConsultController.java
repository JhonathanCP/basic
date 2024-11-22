package com.quantumtech.mediapp_backend.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.quantumtech.mediapp_backend.dto.ConsultDTO;
import com.quantumtech.mediapp_backend.dto.ConsultListExamDTO;
import com.quantumtech.mediapp_backend.dto.ConsultProcDTO;
import com.quantumtech.mediapp_backend.dto.FilterConsultDTO;
import com.quantumtech.mediapp_backend.exception.ModelNotFoundException;
import com.quantumtech.mediapp_backend.model.Consult;
import com.quantumtech.mediapp_backend.model.Exam;
import com.quantumtech.mediapp_backend.model.MediaFile;
import com.quantumtech.mediapp_backend.service.IConsultService;
import com.quantumtech.mediapp_backend.service.IMediaFileService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/consults")
public class ConsultController {

    @Autowired
    private IConsultService service;

    @Autowired
    private IMediaFileService mediaFileService;

    @Autowired
    private ModelMapper mapper;

    // @GetMapping
    // public ResponseEntity<List<Consult>> findAll() {        
    //     List<Consult> list = service.findAll();
    //     return new ResponseEntity<>(list, HttpStatus.OK);
    // }

    //DTO para no afectar al cliente
    @GetMapping
    public ResponseEntity<List<ConsultDTO>> findAll() {
        List<ConsultDTO> list = service.findAll().stream().map(p -> mapper.map(p, ConsultDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultDTO> findById(@PathVariable Integer id) {

        ConsultDTO dtoResponse;
        Consult obj = service.findById(id);

        if(obj == null){
            throw new ModelNotFoundException("ID DOES NOT EXIST: " + id);
        }else{
            dtoResponse = mapper.map(obj, ConsultDTO.class);
        }
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }    

    // @PostMapping
    // public ResponseEntity<Consult> save(@RequestBody Consult consult) {
    //     return new ResponseEntity<>(service.save(consult), HttpStatus.CREATED);
    // }

    //REST MATURITY LEVEL2
    // @PostMapping
    // public ResponseEntity<Void> save(@Valid @RequestBody ConsultDTO dto) {
    //     Consult p = service.save(mapper.map(dto, Consult.class));
    //     URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(p.getIdConsult()).toUri();
    //     return ResponseEntity.created(location).build();
    // }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ConsultListExamDTO dto) {
        //Consult p = service.save(mapper.map(dto.getConsult(), Consult.class));
        Consult c = mapper.map(dto.getConsult(), Consult.class);
        List<Exam> exams = mapper.map(dto.getLstExam(), new TypeToken<List<Exam>>() {}.getType());

        service.saveTransactional(c, exams);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(c.getIdConsult()).toUri();
        return ResponseEntity.created(location).build();
    }   

    @PutMapping
    public ResponseEntity<Consult> update(@Valid @RequestBody ConsultDTO dto) {
        Consult obj = service.findById(dto.getIdConsult());
        if(obj == null){
            throw new ModelNotFoundException("ID DOES NOT EXIST: " + dto.getIdConsult());
        }
        return new ResponseEntity<>(service.update(mapper.map(dto, Consult.class)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {

        Consult obj = service.findById(id);

        if(obj == null){
            throw new ModelNotFoundException("ID DOES NOT EXIST: " + id);
        }else{
            service.delete(id);
        }        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);        
    }

    @GetMapping("hateoas/{id}")
    public EntityModel<ConsultDTO> findByIdHateoas(@PathVariable Integer id) {
        ConsultDTO dtoResponse;
        Consult obj = service.findById(id);

        if(obj == null){
            throw new ModelNotFoundException("ID DOES NOT EXIST: " + id);
        }else{
            dtoResponse = mapper.map(obj, ConsultDTO.class);
        }

        EntityModel<ConsultDTO> resource = EntityModel.of(dtoResponse);

        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findById(id));

        resource.add(link.withRel("consult-info"));

        return resource;
    }

    @GetMapping("/search/others")
    public ResponseEntity<List<ConsultDTO>> searchByOthers(@RequestBody FilterConsultDTO filterDTO){

        List<Consult> consults = service.search(filterDTO.getDni(), filterDTO.getFullname());

        List<ConsultDTO> consultsDTOs = mapper.map(consults, new TypeToken<List<ConsultDTO>>() {}.getType());

        return new ResponseEntity<>(consultsDTOs, HttpStatus.OK);
        
    }

    @GetMapping("/search/date")
    public ResponseEntity<List<ConsultDTO>> searchByDates(@RequestParam(value = "date1") String date1, @RequestParam(value = "date2") String date2){

        List<Consult> consults = service.searchByDates(LocalDateTime.parse(date1), LocalDateTime.parse(date2));

        List<ConsultDTO> consultsDTOs = mapper.map(consults, new TypeToken<List<ConsultDTO>>() {}.getType());

        return new ResponseEntity<>(consultsDTOs, HttpStatus.OK);
        
    }

    @GetMapping("/callProcedure")
    public ResponseEntity<List<ConsultProcDTO>> callProcOrFunction(@RequestParam(value = "date1") String date1, @RequestParam(value = "date2") String date2){

        List<ConsultProcDTO> consults = service.callProcedureOrFunction();

        return new ResponseEntity<>(consults, HttpStatus.OK);
        
    }

    @GetMapping(value = "/generateReport", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generateReport() throws Exception{

        byte[] data;

        data = service.generateReport();

        return new ResponseEntity<>(data, HttpStatus.OK);
        
    }

    @GetMapping(value = "/generateReportByte", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> generateReportByte() throws Exception{

        byte[] data;

        data = service.generateReport();

        return new ResponseEntity<>(data, HttpStatus.OK);
        
    }

    @PostMapping(value = "/saveFile", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Void> saveFile(@RequestParam("file") MultipartFile file) throws Exception{

        MediaFile fl = new MediaFile();

        fl.setFileType(file.getContentType());
        fl.setFilename(file.getName());
        fl.setValue(file.getBytes());
        
        mediaFileService.save(fl);

        return new ResponseEntity<>(HttpStatus.OK);
        
    }

    @GetMapping(value = "/readFile/{idFile}", consumes =  MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> readFile(@PathVariable("idFile") Integer idFile) throws Exception{

        byte[] arr = mediaFileService.findById(idFile).getValue();

        return new ResponseEntity<>(arr, HttpStatus.OK);
        
    }

}
