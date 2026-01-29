package com.ramanasoft.college.service;

import com.ramanasoft.college.dto.GalleryDto;
import com.ramanasoft.college.model.CollegePanel;
import com.ramanasoft.college.model.Gallery;
import com.ramanasoft.college.repository.CollegePanelRepository;
import com.ramanasoft.college.repository.GalleryRepository;
import com.ramanasoft.college.service.GalleryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class GalleryServiceImpl implements GalleryService {

    private final GalleryRepository galleryRepository;
    private final CollegePanelRepository collegePanelRepository;

    @Override
    public GalleryDto createGallery(GalleryDto dto) {
        CollegePanel college = collegePanelRepository.findById(dto.getCollegePanelId())
                .orElseThrow(() -> new RuntimeException("College not found"));

        Gallery gallery = new Gallery();
        gallery.setMediaType(dto.getMediaType());
        gallery.setMediaUrl(dto.getMediaUrl());
        gallery.setCollegePanel(college);

        Gallery saved = galleryRepository.save(gallery);
        dto.setId(saved.getId());
        return dto;
    }

    @Override
    public GalleryDto updateGallery(Long id, GalleryDto dto) {
        Gallery gallery = galleryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gallery not found"));

        CollegePanel college = collegePanelRepository.findById(dto.getCollegePanelId())
                .orElseThrow(() -> new RuntimeException("College not found"));

        gallery.setMediaType(dto.getMediaType());
        gallery.setMediaUrl(dto.getMediaUrl());
        gallery.setCollegePanel(college);

        galleryRepository.save(gallery);
        dto.setId(gallery.getId());
        return dto;
    }

    @Override
    public void deleteGallery(Long id) {
        galleryRepository.deleteById(id);
    }

    @Override
    public GalleryDto getGalleryById(Long id) {
        Gallery gallery = galleryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gallery not found"));
        return mapToDto(gallery);
    }

    @Override
    public List<GalleryDto> getAllGallery() {
        return galleryRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<GalleryDto> getGalleryByCollegeId(Long collegeId) {
        CollegePanel college = collegePanelRepository.findById(collegeId)
                .orElseThrow(() -> new RuntimeException("College not found"));
        return galleryRepository.findByCollegePanel(college).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<GalleryDto> getGalleryByCollegeAndType(Long collegeId, String mediaType) {
        CollegePanel college = collegePanelRepository.findById(collegeId)
                .orElseThrow(() -> new RuntimeException("College not found"));
        return galleryRepository.findByCollegePanelAndMediaType(college, mediaType.toUpperCase()).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private GalleryDto mapToDto(Gallery gallery) {
        GalleryDto dto = new GalleryDto();
        dto.setId(gallery.getId());
        dto.setMediaType(gallery.getMediaType());
        dto.setMediaUrl(gallery.getMediaUrl());
        dto.setCollegePanelId(gallery.getCollegePanel().getId());
        return dto;
    }
}

