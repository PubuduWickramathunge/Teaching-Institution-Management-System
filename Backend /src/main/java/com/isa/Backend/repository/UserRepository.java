package com.isa.Backend.repository;

import com.isa.Backend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByEmail(String email);

    //    Optional<Users> findById(Long id);
    @Query(value = "SELECT * FROM users WHERE role = :role", nativeQuery = true)
    List<Users> findByRole(@Param("role") String role);

    @Query(value = "SELECT * FROM users", nativeQuery = true)
    List<Users> findAll();


}
