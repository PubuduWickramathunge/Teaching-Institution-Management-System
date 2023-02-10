package com.isa.Backend.repository;


import com.isa.Backend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
//    Optional<Student> findById(Long id);

}
