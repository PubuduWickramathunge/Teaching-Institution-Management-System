package com.isa.Backend.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class EnrollmentId implements Serializable{




        @Column(name = "student_id")
        private Long studentId;

        @Column(name = "course_id")
        private Long courseId;

        // ... getters and setters


}
