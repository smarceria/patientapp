package com.xtramile.patientapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xtramile.patientapp.entity.Patient;
import com.xtramile.patientapp.repository.PatientRepository;
import com.xtramile.patientapp.repository.PatientSpecifications;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {

	@Autowired
	PatientRepository patientRepo;
	
	@Override
	public Patient savePatient(Patient patient) {
		return patientRepo.save(patient);
	}
	
	@Override
	public Patient getPatient(long patientId) {
		return patientRepo.findById(patientId).orElseThrow(() -> new RuntimeException("Not Found"));
	}

	@Override
	public Patient updatePatient(long patientId, Patient patient) {
		Patient existingPatient = patientRepo.findById(patientId).orElseThrow(() -> new RuntimeException("Not Found"));
		existingPatient.setFirstName(patient.getFirstName());
		existingPatient.setLastName(patient.getLastName());
		existingPatient.setDateOfBirth(patient.getDateOfBirth());
		existingPatient.setGender(patient.getGender());
		existingPatient.setAddress(patient.getAddress());
		existingPatient.setPhoneNo(patient.getPhoneNo());
		existingPatient.setAddress(patient.getAddress());

		patientRepo.save(existingPatient);
		return existingPatient;
       
   }

   @Override
   public Patient deletePatient(long patientId) {
       Patient existingPatient = patientRepo.findById(patientId).orElseThrow(() -> new RuntimeException("Not Found"));
       patientRepo.deleteById(patientId);
       return existingPatient;
   }

   @Override
   public Page<Patient> searchPatients(String name, String pid, Pageable pageable) {
       Specification<Patient> spec = Specification.where(null);

       if (name != null && !name.isEmpty()) {
           spec = spec.and(PatientSpecifications.hasFirstName(name));
           spec = spec.or(PatientSpecifications.hasLastName(name));
       }

       if (pid != null && !pid.isEmpty()) {
           spec = spec.or(PatientSpecifications.hasPid(pid));
       }

       return patientRepo.findAll(spec, pageable);
   }

}
