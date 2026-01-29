package com.ramanasoft.college.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.ramanasoft.college.dto.AdmissionDto;
import com.ramanasoft.college.model.Admission;
import com.ramanasoft.college.model.CollegePanel;
import com.ramanasoft.college.repository.AdmissionRepository;
import com.ramanasoft.college.repository.CollegePanelRepository;

@Service
public class AdmissionServiceImpl implements AdmissionService {

    private final AdmissionRepository admissionRepository;
    private final CollegePanelRepository collegeRepository;

    public AdmissionServiceImpl(
            AdmissionRepository admissionRepository,
            CollegePanelRepository collegeRepository) {
        this.admissionRepository = admissionRepository;
        this.collegeRepository = collegeRepository;
    }

    // Apply Admission
    @Override
    public Admission applyAdmission(AdmissionDto dto) {
        CollegePanel college = collegeRepository
                .findByCollegeCode(dto.getCollegeId())
                .orElseThrow(() ->
                        new RuntimeException("College not found with ID: " + dto.getCollegeId()));

        Admission admission = new Admission();
        admission.setApplicantName(dto.getApplicantName());
        admission.setRole(dto.getRole());
        admission.setQualification(dto.getQualification());
        admission.setEmail(dto.getEmail());
        admission.setCollegePanel(college);

        return admissionRepository.save(admission);
    }

    // Get by Admission ID
    @Override
    public Admission getByAdmissionId(Long id) {
        return admissionRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Admission not found with ID: " + id));
    }

    // Get all Admissions
    @Override
    public List<Admission> getAllAdmissions() {
        return admissionRepository.findAll();
    }

    // Get by College Code
    @Override
    public List<Admission> getByCollegeId(String collegeCode) {
        return admissionRepository.findByCollegePanel_CollegeCode(collegeCode);
    }

    // Update Admission
    @Override
    public Admission updateAdmission(Long id, AdmissionDto dto) {
        Admission admission = getByAdmissionId(id);
        admission.setApplicantName(dto.getApplicantName());
        admission.setRole(dto.getRole());
        admission.setQualification(dto.getQualification());
        admission.setEmail(dto.getEmail());
        return admissionRepository.save(admission);
    }

    // Delete Admission
    @Override
    public void deleteAdmission(Long id) {
        Admission admission = getByAdmissionId(id);
        admissionRepository.delete(admission);
    }
}
