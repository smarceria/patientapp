package com.xtramile.patientapp.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.xtramile.patientapp.entity.AustralianAddress;
import com.xtramile.patientapp.entity.Patient;
import com.xtramile.patientapp.service.PatientService;

//@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@WebMvcTest(PatientController.class)
public class PatientControllerTest {

    @MockBean
    private PatientService patientService;

    @MockBean
    private ModelMapper modelMapper;
    
    //@Spy
    //private ModelMapper modelMapper;
    
    
    @Autowired
	private MockMvc mockMvc;
    
	@InjectMocks
	PatientController patientController;
    
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
	void shouldReturnDefaultMessage() throws Exception {
		this.mockMvc.perform(get("/patient")).andDo(print()).andExpect(status().isOk());
	}
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
   
	
    
	@Test
	public void testGetPatients() throws Exception {
		
		List<Patient> patients = Arrays.asList(patient1, patient2);
		Pageable pageable = PageRequest.of(0, 10);
		Page<Patient> patientMock = new PageImpl<>(patients, pageable, patients.size());
        
		when(patientService.searchPatients(anyString(), anyString(), any(Pageable.class))).thenReturn(patientMock);
			
	    ResponseEntity<Map<String, Object>> responseEntity = patientController.getPatients("John", null, 0, 10);

	    assertNotNull(responseEntity);
        assertEquals(2, patients.size());
        assertEquals("John", patients.get(0).getFirstName());
	}
	
	*/
    
	@Test
	public void testGetPatients() throws Exception {
		
		List<Patient> patients = Arrays.asList(patient1, patient2);
		Pageable pageable = PageRequest.of(0, 10);
		Page<Patient> patientMock = new PageImpl<>(patients, pageable, patients.size());
        
		when(patientService.searchPatients(anyString(), anyString(), any(Pageable.class))).thenReturn(patientMock);
			/*
		mockMvc.perform(get("/patient"))
	    .andExpect(status().isOk())
	    .andExpect(jsonPath("$.size()").value(patients.size()))
	    .andDo(print());
	    */
		

		// Send course as body to /students/Student1/courses
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/patient")
				.accept(MediaType.APPLICATION_JSON).content("test")
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	

}
