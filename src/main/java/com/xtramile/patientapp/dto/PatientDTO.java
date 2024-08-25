package com.xtramile.patientapp.dto;

import java.time.LocalDate;

import jakarta.persistence.Embedded;

public class PatientDTO {
	
	private Long id;
	
    private String pid;
    
    private String firstName;
    
    private String lastName;
    
    private LocalDate dateOfBirth;
    
    private String gender;
    
    @Embedded
    private AustralianAddressDTO address;
    
    private String phoneNo;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public AustralianAddressDTO getAddress() {
		return address;
	}
	public void setAddress(AustralianAddressDTO address) {
		this.address = address;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	    
	
}
