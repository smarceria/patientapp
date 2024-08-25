package com.xtramile.patient.controller;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.xtramile.patientapp.controller.PatientController;
import com.xtramile.patientapp.entity.Patient;
import com.xtramile.patientapp.service.PatientService;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PatientControllerTest {

    @Mock
    private PatientService patientService;

    @Spy
    private ModelMapper modelMapper;
    
	@InjectMocks
	PatientController patientController;
	
    @Autowired
    private MockMvc mockMvc;
    
    Patient patient;
    
    /*
	@Test
	public void testAddPatient() throws ParseException 
	{
		
		PatientDTO patientDto = new PatientDTO();
		patientDto.setFirstName("John");
		patientDto.setLastName("Doe");
		
		Patient patient = new Patient();
		patient.setFirstName("John");
		patient.setLastName("Doe");

		when(patientService.savePatient(any(Patient.class))).thenReturn(patient);
		
		ResponseEntity<PatientDTO> responseEntity = patientController.createPatient(patientDto);
		
		assertThat(responseEntity.getStatusCode().value()).isEqualTo(201);

	}
	
	@Test
	public void testUpdatePatient() throws ParseException 
	{
		
		PatientDTO patientDto = new PatientDTO();
		patientDto.setFirstName("John");
		patientDto.setLastName("Doe");
		
		Patient patient = new Patient();
		patient.setFirstName("John");
		patient.setLastName("Doe");

		when(patientService.savePatient(any(Patient.class))).thenReturn(patient);
		
		ResponseEntity<PatientDTO> responseEntity = patientController.createPatient(patientDto);
		
		assertThat(responseEntity.getStatusCode().value()).isEqualTo(201);

	}
	
	
	@Test
	public void testDeletePatient() throws ParseException 
	{
		
		PatientDTO patientDto = new PatientDTO();
		patientDto.setFirstName("John");
		patientDto.setLastName("Doe");
		
		Patient patient = new Patient();
		patient.setFirstName("John");
		patient.setLastName("Doe");

		when(patientService.savePatient(any(Patient.class))).thenReturn(patient);
		
		ResponseEntity<PatientDTO> responseEntity = patientController.createPatient(patientDto);
		
		assertThat(responseEntity.getStatusCode().value()).isEqualTo(201);

	}
	
	@Test
	public void testGetPatient() throws ParseException 
	{
		
		Page<Patient> patientMock = Mockito.mock(Page.class);
		
        when(patientService.searchPatients(anyString(), anyString(), any(Pageable.class))).thenReturn(patientMock);
		
		ResponseEntity<Map<String, Object>> responseEntity = patientController.getPatients(anyString(), anyString(), anyInt(), anyInt());
		
        assertNotNull(responseEntity);
        assertEquals(1, patients.size());
        assertEquals("John", patients.get(0).getFirstName());

	}
   
	*/
    
	@Test
	public void testGetPatients() throws Exception {
	    ResponseEntity<Map<String, Object>> responseEntity = patientController.getPatients("John", null, 0, 10);

	    mockMvc.perform(get("/patient"))
	            .andExpect(status().isOk())
	            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	            .andExpect(jsonPath("$").isArray())
	            .andExpect(jsonPath("$[0].firstName").value("John"));
	}
}
