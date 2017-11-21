/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicka.postgresql.springbootdbpostgresql.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author java-spring
 */

@Data
@Entity
@Table(name = "person", 
        catalog = "public.java")
public class Person implements Serializable{

    @Id
    @Column(name = "iduser", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int iduser;

    @Column(name = "nama", nullable = false)
    String nama;

    @Column(name = "kelamin", nullable = false)
    String kelamin;

    @Column(name = "telepon", nullable = false)
    String telepon;

    @Column(name = "email", nullable = false)
    String email;

    @Column(name = "usia", nullable = false)
    String usia;
    
    private Person(){}
}
