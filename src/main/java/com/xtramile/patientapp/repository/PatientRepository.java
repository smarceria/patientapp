package com.xtramile.patientapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.xtramile.patientapp.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>, JpaSpecificationExecutor<Patient> {

	/*
	@Query("select p from Patient p where (:firstName is null or u.firstName = :firstName)"
		      +" and (:lastName is null or u.lastName = :lastName)")
	Page<Patient> findPatientByFirstName(String name, Pageable pageable);
	
	Page<Patient> findPatientByPid(String pid, Pageable pageable);
	
	
	Page<Patient> findAll(Specification<Patient> spec, Pageable pageable);
*/
}