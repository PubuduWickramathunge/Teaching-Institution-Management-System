package com.isa.Backend.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    @ManyToMany(mappedBy = "courses",fetch = FetchType.LAZY)
    private List<Users> students;

    public Course(String name, String description, Users teacher) {
        this.name = name;
        this.description = description;
        this.teacher = teacher;
    }
}
