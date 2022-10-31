package controller;

import model.PatientModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import repository.PatientRepository;

import java.util.List;

@RestController
public class PatientController {

    @Autowired
    private PatientRepository patientRepo;

    @GetMapping("/patient/getall")
    public ResponseEntity<?> getAllPatients(){
        List<PatientModel> patients = patientRepo.findAll();

        if(patients.size()>0){
            return new ResponseEntity<List<PatientModel>>(patients, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("No patients found!",HttpStatus.NOT_FOUND);
        }
    }
}
