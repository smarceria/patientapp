package com.xtramile.patientapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.xtramile.patientapp.entity.Patient;

public interface PatientService {

	Patient savePatient(Patient patient);

	Patient getPatient(long patientId);

	Patient deletePatient(long patientId);
	
	Patient updatePatient(long patientId, Patient patient);
	
	Page<Patient> searchPatients(String name, String pid, Pageable pageable);
}
