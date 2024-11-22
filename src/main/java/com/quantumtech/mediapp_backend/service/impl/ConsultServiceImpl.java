package com.quantumtech.mediapp_backend.service.impl;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.quantumtech.mediapp_backend.dto.ConsultProcDTO;
import com.quantumtech.mediapp_backend.model.Consult;
import com.quantumtech.mediapp_backend.model.Exam;
import com.quantumtech.mediapp_backend.repo.IConsultExamRepo;
import com.quantumtech.mediapp_backend.repo.IConsultRepo;
import com.quantumtech.mediapp_backend.repo.IGenericRepo;
import com.quantumtech.mediapp_backend.service.IConsultService;

import jakarta.transaction.Transactional;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ConsultServiceImpl extends CRUDImpl<Consult, Integer> implements IConsultService {

    @Autowired
    private IConsultRepo repo;

    @Autowired
    private IConsultExamRepo ceRepo;

    @Override
    protected IGenericRepo<Consult, Integer> getRepo() {
        return repo;
    }

    @Transactional
    @Override
    public Consult saveTransactional(Consult consult, List<Exam> exams) {
        //con @JsonBackReference ya no requiere referencia
        //consult.getDetails().forEach(det -> det.setConsult(consult));
        repo.save(consult);
        exams.forEach(exam -> ceRepo.saveExam(consult.getIdConsult(), exam.getIdExam()));
        return consult;
    }

    @Override
    public List<Consult> search(String dni, String fullname) {
        return repo.search(dni, fullname);
    }

    @Override
    public List<Consult> searchByDates(LocalDateTime date1, LocalDateTime date2) {
        return repo.searchByDates(date1, date2);
    }

    @Override
    public List<ConsultProcDTO> callProcedureOrFunction() {

        List<ConsultProcDTO> consults = new ArrayList<>();

        repo.callProcedureOrFunction().forEach(res -> {
            ConsultProcDTO dto = new ConsultProcDTO();
            dto.setQuantity((Integer) res[0]);
            dto.setConsultdate((String) res[1]);
            consults.add(dto);
        });

        return consults;
    }

    @Override
    public byte[] generateReport() throws Exception{
        
        byte[] data;

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("txt_title", "Report title");

        File file;

        file = new ClassPathResource("/reports/consults.jasper").getFile();

        JasperPrint print = JasperFillManager.fillReport(file.getPath(), parameters, new JRBeanCollectionDataSource(callProcedureOrFunction()));

        data = JasperExportManager.exportReportToPdf(print);

        return data;
    }

}
