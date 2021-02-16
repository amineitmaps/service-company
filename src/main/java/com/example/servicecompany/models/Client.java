package com.example.servicecompany.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity  // This tells Hibernate to make a table out of this class
@Table(name="clients")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fname;
    private String lname;
    @Column(nullable = false , unique = true)
    private String email;

    //@Temporal(TemporalType.DATE)
    @Column(name="createat")
    private String createat;
}
