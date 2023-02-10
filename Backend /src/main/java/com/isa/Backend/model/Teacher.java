package com.isa.Backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor

@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "subject", nullable = false)
    private String subject;

    @Column(name = "date_of_birth", nullable = false)
    private Date dateOfBirth;

    @Column(name = "mobile", nullable = false)
    private String mobile;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    public Teacher(String subject, Date dateOfBirth, String mobile, Users user) {
        this.subject = subject;
        this.dateOfBirth = dateOfBirth;
        this.mobile = mobile;
        this.user = user;
    }
}