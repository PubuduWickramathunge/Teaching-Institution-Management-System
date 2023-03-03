package com.isa.Backend.repository;

import com.isa.Backend.model.Enrollment;
import com.isa.Backend.model.EnrollmentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentId> {
}
