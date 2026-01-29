package com.ramanasoft.college.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramanasoft.college.model.Admission;

public interface AdmissionRepository
        extends JpaRepository<Admission, Long> {

    List<Admission> findByCollegePanel_CollegeCode(String collegeCode);
}