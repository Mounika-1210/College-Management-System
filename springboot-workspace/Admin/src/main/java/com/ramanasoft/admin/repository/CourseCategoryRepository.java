package com.ramanasoft.admin.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ramanasoft.admin.model.CourseCategory;

@Repository
public interface CourseCategoryRepository extends JpaRepository<CourseCategory, Long> {

    // Existing methods
    CourseCategory findByName(String name);
    boolean existsByName(String name);

    // ✅ SEARCH QUERY
    @Query("""
        SELECT c FROM CourseCategory c WHERE
        (:name IS NULL OR c.name = :name) AND
        (:description IS NULL OR c.description = :description)
    """)
    List<CourseCategory> searchCategories(
            @Param("name") String name,
            @Param("description") String description
    );
}


