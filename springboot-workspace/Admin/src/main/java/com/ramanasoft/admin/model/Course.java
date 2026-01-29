package com.ramanasoft.admin.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "course")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String duration;
    private Double fees;
    private String eligibility;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CourseCategory category;

    @ManyToOne
    @JoinColumn(name = "college_id")
    private College college;
}