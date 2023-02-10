package com.isa.Backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor

@Entity
@Table(name = "managers")
public class Management {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "position", nullable = false)
    private String position;


    @Column(name = "mobile", nullable = false)
    private String mobile;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    public Management(String position, String mobile, Users user) {
        this.position = position;
        this.mobile = mobile;
        this.user = user;
    }
}