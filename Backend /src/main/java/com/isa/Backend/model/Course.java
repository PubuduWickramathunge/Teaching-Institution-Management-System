package com.isa.Backend.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Users teacher;

    @ManyToMany(mappedBy = "courses",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Users> students = new HashSet<>();

    public Course(String name, String description, Users teacher) {
        this.name = name;
        this.description = description;
        this.teacher = teacher;
    }
}
