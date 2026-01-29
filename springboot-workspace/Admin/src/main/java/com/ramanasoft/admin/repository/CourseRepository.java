package com.ramanasoft.admin.repository;

import com.ramanasoft.admin.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    // existing methods
    List<Course> findByCollegeId(Long collegeId);
    List<Course> findByCategoryId(Long categoryId);
    List<Course> findByNameContainingIgnoreCase(String name);

    // ✅ SEARCH QUERY
    @Query("""
        SELECT c FROM Course c WHERE
        (:name IS NULL OR LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND
        (:collegeId IS NULL OR c.college.id = :collegeId) AND
        (:categoryId IS NULL OR c.category.id = :categoryId) AND
        (:minFees IS NULL OR c.fees >= :minFees) AND
        (:maxFees IS NULL OR c.fees <= :maxFees)
    """)
    List<Course> searchCourses(
            @Param("name") String name,
            @Param("collegeId") Long collegeId,
            @Param("categoryId") Long categoryId,
            @Param("minFees") Double minFees,
            @Param("maxFees") Double maxFees
    );
}
