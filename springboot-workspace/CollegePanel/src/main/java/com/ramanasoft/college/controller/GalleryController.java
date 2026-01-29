package com.ramanasoft.college.controller;

import com.ramanasoft.college.dto.GalleryDto;
import com.ramanasoft.college.service.GalleryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gallery")
public class GalleryController {

    @Autowired
    private GalleryService galleryService;

    // Create a new gallery item
    @PostMapping
    public ResponseEntity<GalleryDto> createGallery(@Valid @RequestBody GalleryDto dto) {
        return ResponseEntity.ok(galleryService.createGallery(dto));
    }

    // Update gallery item by ID
    @PutMapping("/{id}")
    public ResponseEntity<GalleryDto> updateGallery(@PathVariable Long id,
                                                    @Valid @RequestBody GalleryDto dto) {
        return ResponseEntity.ok(galleryService.updateGallery(id, dto));
    }

    // Delete gallery item by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGallery(@PathVariable Long id) {
        galleryService.deleteGallery(id);
        return ResponseEntity.noContent().build();
    }

    // Get gallery item by ID
    @GetMapping("/{id}")
    public ResponseEntity<GalleryDto> getGallery(@PathVariable Long id) {
        return ResponseEntity.ok(galleryService.getGalleryById(id));
    }

    // Get all gallery items
    @GetMapping
    public ResponseEntity<List<GalleryDto>> getAllGallery() {
        return ResponseEntity.ok(galleryService.getAllGallery());
    }

    // Get all gallery items for a specific college
    @GetMapping("/college/{collegeId}")
    public ResponseEntity<List<GalleryDto>> getGalleryByCollege(@PathVariable Long collegeId) {
        return ResponseEntity.ok(galleryService.getGalleryByCollegeId(collegeId));
    }

    // Get gallery items by college ID and media type (IMAGE / VIDEO)
    @GetMapping("/college/{collegeId}/type")
    public ResponseEntity<List<GalleryDto>> getGalleryByCollegeAndType(@PathVariable Long collegeId,
                                                                       @RequestParam String mediaType) {
        return ResponseEntity.ok(galleryService.getGalleryByCollegeAndType(collegeId, mediaType));
    }
}

