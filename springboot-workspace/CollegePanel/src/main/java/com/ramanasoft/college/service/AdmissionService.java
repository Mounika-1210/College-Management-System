package com.ramanasoft.college.service;

import com.ramanasoft.college.dto.AdmissionDto;
import com.ramanasoft.college.model.Admission;
import java.util.List;

public interface AdmissionService {

    Admission applyAdmission(AdmissionDto dto);

    Admission getByAdmissionId(Long admissionId);

    List<Admission> getAllAdmissions();

    List<Admission> getByCollegeId(String collegeCode);

    Admission updateAdmission(Long admissionId, AdmissionDto dto);

    void deleteAdmission(Long admissionId);
}
