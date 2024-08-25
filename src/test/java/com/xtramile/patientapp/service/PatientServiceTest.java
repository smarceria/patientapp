package com.xtramile.patientapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.xtramile.patientapp.entity.AustralianAddress;
import com.xtramile.patientapp.entity.Patient;
import com.xtramile.patientapp.repository.PatientRepository;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientServiceImpl patientService;

    private Patient patient1;
    
    private AustralianAddress address1;
    
    private Patient patient2;
    
    private AustralianAddress address2;
    
    
    @BeforeEach
    public void setup(){

    	address1 = new AustralianAddress();
    	address1.setAddress("123 Main Street");
    	address1.setState(null);
    	address1.setState("NSW");
    	address1.setPostcode("2000");
    	
    	patient1 = new Patient();
        patient1.setId(1L);
        patient1.setPid("P88888");
        patient1.setFirstName("John");
        patient1.setLastName("Doe");
        patient1.setGender("Male");
        patient1.setDateOfBirth(LocalDate.of(1985, 10, 1));
        patient1.setPhoneNo("4178676890");
        patient1.setAddress(address1);
        
        
    	address2 = new AustralianAddress();
    	address2.setAddress("456 Main Street");
    	address2.setState(null);
    	address2.setState("NSW");
    	address2.setPostcode("2000");
    	
    	patient2 = new Patient();
        patient2.setId(2L);
        patient2.setPid("P99999");
        patient2.setFirstName("Jane");
        patient2.setLastName("Doe");
        patient2.setGender("Female");
        patient2.setDateOfBirth(LocalDate.of(1988, 7, 11));
        patient2.setPhoneNo("4178676891");
        patient2.setAddress(address2);

    }
    
    @Test
    public void testSavePatient() {

        when(patientRepository.save(patient1)).thenReturn(patient1);

        Patient savedPatient = patientService.savePatient(patient1);

        assertNotNull(savedPatient);
        
        assertPatient(patient1, savedPatient);
        
    }

    @Test
    public void testGetPatient() {
        long patientId = 1L;

        when(patientRepository.findById(patientId)).thenReturn(java.util.Optional.of(patient1));

        Patient retrievedPatient = patientService.getPatient(patientId);

        assertNotNull(retrievedPatient);
        
        assertPatient(patient1, retrievedPatient);
    }

    /*
    @Test
    public void testDeletePatient() {
        long patientId = 1L;
        when(patientRepository.existsById(patientId)).thenReturn(true);

        patientService.deletePatient(patientId);

        verify(patientRepository).deleteById(patientId);
    }
	*/
    
    /*
    @Test
    public void testUpdatePatient() {
        long patientId = 1L;
        Patient patient = new Patient();
        when(patientRepository.existsById(patientId)).thenReturn(true);
        when(patientRepository.save(patient)).thenReturn(patient);

        Patient updatedPatient = patientService.updatePatient(patientId, patient);

        assertNotNull(updatedPatient);
        assertEquals(patient, updatedPatient);
    }
	*/
    
    
    
    @Test
    public void testSearchPatientsByName() {
        String name = "Doe";
        Pageable pageable = PageRequest.of(0, 10);

        List<Patient> patients = List.of(patient1, patient2);
        Page<Patient> page = new PageImpl<>(patients, pageable, patients.size());

        when(patientRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(page);

        Page<Patient> resultPage = patientService.searchPatients(name, null, pageable);

        assertNotNull(resultPage);
        assertEquals(patients.size(), resultPage.getContent().size());
    }

    @Test
    public void testSearchPatientsByPid() {
        String pid = "P123";
        Pageable pageable = PageRequest.of(0, 10);

        List<Patient> patients = List.of(new Patient(), new Patient());
        Page<Patient> page = new PageImpl<>(patients, pageable, patients.size());

        when(patientRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(page);

        Page<Patient> resultPage = patientService.searchPatients(null, pid, pageable);

        assertNotNull(resultPage);
        assertEquals(patients.size(), resultPage.getContent().size());
    }

    @Test
    public void testSearchPatientsByNameAndPid() {
        String name = "John";
        String pid = "P123";
        Pageable pageable = PageRequest.of(0, 10);

        List<Patient> patients = List.of(patient1, patient2);
        Page<Patient> page = new PageImpl<>(patients, pageable, patients.size());

        when(patientRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(page);

        Page<Patient> resultPage = patientService.searchPatients(name, pid, pageable);

        assertNotNull(resultPage);
        assertEquals(patients.size(), resultPage.getContent().size());
    }

    @Test
    public void testSearchPatientsEmptySearch() {
        Pageable pageable = PageRequest.of(0, 10);

        when(patientRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(Page.empty());

        Page<Patient> resultPage = patientService.searchPatients(null, null, pageable);

        assertNotNull(resultPage);
        assertEquals(0, resultPage.getContent().size());
    }
    
    
    private void assertPatient(Patient expected, Patient actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), expected.getLastName());
    }
}