package com.xtramile.patientapp.repository;

import org.springframework.data.jpa.domain.Specification;

import com.xtramile.patientapp.entity.Patient;

public class PatientSpecifications {

	public static Specification<Patient> hasFirstName(String name) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.like(root.get("firstName"), "%" + name + "%");
    }

	public static Specification<Patient> hasLastName(String name) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.like(root.get("lastName"), "%" + name + "%");
    }
	
    public static Specification<Patient> hasPid(String pid) {
        return (root, query, criteriaBuilder) ->
        	criteriaBuilder.equal(root.get("pid"), pid);
    }

}
