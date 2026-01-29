package com.ramanasoft.college.service;

import com.ramanasoft.college.dto.GalleryDto;

import java.util.List;

public interface GalleryService {

    GalleryDto createGallery(GalleryDto dto);

    GalleryDto updateGallery(Long id, GalleryDto dto);

    void deleteGallery(Long id);

    GalleryDto getGalleryById(Long id);

    List<GalleryDto> getAllGallery();

    List<GalleryDto> getGalleryByCollegeId(Long collegeId);

    List<GalleryDto> getGalleryByCollegeAndType(Long collegeId, String mediaType);
}

