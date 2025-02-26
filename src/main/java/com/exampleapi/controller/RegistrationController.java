package com.exampleapi.controller;

import com.exampleapi.entity.Registration;
import com.exampleapi.payload.RegistrationDto;
import com.exampleapi.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
import java.util.List;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {

    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    //http://localhost:8080/api/v1/registration
    @PostMapping
    public ResponseEntity<RegistrationDto> addRegistrationController(
            @RequestBody RegistrationDto registrationDto
    ){
        RegistrationDto reg = registrationService.saveRegistration(registrationDto);
        return new ResponseEntity<>(reg, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/v1/registration?id=1
    /* @DeleteMapping
    public String deleteRegistration(
            @RequestParam Long id
    ){
        registrationService.deleteRegistration(id);
        return "Deleted";
    }*/

    /*//http://localhost:8080/api/v1/registration/record/1
    @DeleteMapping("/record/{id}")*/


    //http://localhost:8080/api/v1/registration/2
    @DeleteMapping("/{id}")
    public String deleteRegistration(
            @PathVariable Long id
    ){
        registrationService.deleteRegistration(id);
        return "Deleted";
    }

    @PutMapping("/{id}")
    public String updateRegistration(
            @PathVariable Long id,
            @RequestBody Registration registration
    ){
        registrationService.updateRegistration(id, registration);
        return "Done";
    }

    //http://localhost:8080/api/v1/registration?pageNo=0&pageSize=5&sortBy=name&sortDir=asc
    @GetMapping
    public List<RegistrationDto> getAllRegistrations(
            @RequestParam(defaultValue = "0", required = false) int pageNo,
            @RequestParam(defaultValue = "5", required = false) int pageSize,
            @RequestParam(defaultValue = "id", required = false) String sortBy,
            @RequestParam(defaultValue = "asc", required = false) String sortDir
    ){
        List<RegistrationDto> registrations = registrationService.getAllRegistrations(pageNo, pageSize, sortBy, sortDir);
        return registrations;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getRegistrationById(
            @PathVariable Long id
    ) //throws FileNotFoundException
    {
        //FileReader fr = new FileReader("C://Users/aksha/OneDrive/Documents/Thesis/txt.txt");
        Registration registration = registrationService.getRegistrationById(id);
        if (registration != null){
            return new ResponseEntity<>(registration, HttpStatus.OK);
        }
        return new ResponseEntity<>("No Record found for id:" + id, HttpStatus.OK);
    }
}
