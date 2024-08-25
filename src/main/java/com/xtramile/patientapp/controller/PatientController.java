package com.xtramile.patientapp.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xtramile.patientapp.dto.PatientDTO;
import com.xtramile.patientapp.entity.Patient;
import com.xtramile.patientapp.service.PatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {
	
    @Autowired
    private PatientService patientService;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @CrossOrigin(origins="*")
    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable long id) {
    	
		try {
	    	
	    	Patient patient = patientService.getPatient(id);
			
	        return new ResponseEntity<>(convertToDto(patient), HttpStatus.OK);
	        
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    
    @CrossOrigin(origins="*")
    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getPatients(
    		 @RequestParam(required = false) String pid,
             @RequestParam(required = false) String name,
    		@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
    	
		try {
			List<Patient> patients = new ArrayList<Patient>();
	    	Pageable paging = PageRequest.of(page, size);
	    	
	    	Page<Patient> pagePatient; 
	    	Map<String, Object> response = new HashMap<>();
	    	
	    	pagePatient = patientService.searchPatients(name, pid, paging);

	    	patients = pagePatient.getContent();
	    	
	        response.put("items", patients);
	        response.put("currentPage", pagePatient.getNumber());
	        response.put("totalItems", pagePatient.getTotalElements());
	        response.put("totalPages", pagePatient.getTotalPages());

	        return new ResponseEntity<>(response, HttpStatus.OK);
	        
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @CrossOrigin(origins="*")
    @PostMapping("")
    public ResponseEntity<PatientDTO> createPatient(@RequestBody PatientDTO patientDto) throws ParseException {
    	Patient patient = convertToEntity(patientDto);
    	
    	Patient savedPatient = patientService.savePatient(patient);
    	
    	return new ResponseEntity<>(convertToDto(savedPatient), HttpStatus.CREATED);
    }
    
    @CrossOrigin(origins="*")
    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable Long id, @RequestBody PatientDTO patientDto) throws ParseException {
        
    	if(!Objects.equals(id, patientDto.getId())){
            throw new IllegalArgumentException("IDs don't match");
        }
        
    	Patient patient = convertToEntity(patientDto);
    	
    	Patient updatedPatient = patientService.updatePatient(id, patient);
    	
        return new ResponseEntity<>(convertToDto(updatedPatient), HttpStatus.OK);
    }

    @CrossOrigin(origins="*")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
    	try {
	    	
    		patientService.deletePatient(id);
   
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    	
        return new ResponseEntity<String>("Patient is deleted successfully", HttpStatus.OK);
    }
    
    
    private PatientDTO convertToDto(Patient patient) {
    	PatientDTO patientDto = modelMapper.map(patient, PatientDTO.class);

        return patientDto;
    }
    
    private Patient convertToEntity(PatientDTO patientDto) throws ParseException {
        Patient patient = modelMapper.map(patientDto, Patient.class);

        return patient;
    }

}
