package com.xtramile.patientapp.dto;

import jakarta.persistence.Embeddable;

@Embeddable
public class AustralianAddressDTO {
	
    private String address;
    
    private String suburb;
    
    private String state;
    
    private String postcode;
    
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSuburb() {
		return suburb;
	}
	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

}