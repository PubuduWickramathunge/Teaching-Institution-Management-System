package com.isa.Backend.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "enrollment")
public class Enrollment {

    @EmbeddedId
    private EnrollmentId id;

    @Column
    private String grade;


}
