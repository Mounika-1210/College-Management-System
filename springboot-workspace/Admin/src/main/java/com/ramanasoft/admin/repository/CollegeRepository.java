package com.ramanasoft.admin.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ramanasoft.admin.model.College;

@Repository
public interface CollegeRepository extends JpaRepository<College, Long> {

    // Find colleges by city
    List<College> findByCity(String city);

    // Find colleges by state
    List<College> findByState(String state);

    // Find college by name
    College findByName(String name);

    // Check if college exists by name
    boolean existsByName(String name);
    
    @Query("""
            SELECT c FROM College c WHERE
            (:name IS NULL OR c.name = :name) AND
            (:city IS NULL OR c.city = :city) AND
            (:state IS NULL OR c.state = :state) AND
            (:ranking IS NULL OR c.ranking = :ranking)
        """)
        List<College> searchColleges(
                @Param("name") String name,
                @Param("city") String city,
                @Param("state") String state,
                @Param("ranking") String ranking
        );
}
