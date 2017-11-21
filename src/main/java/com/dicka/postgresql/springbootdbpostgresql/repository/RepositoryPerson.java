package com.dicka.postgresql.springbootdbpostgresql.repository;

import com.dicka.postgresql.springbootdbpostgresql.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RepositoryPerson extends JpaRepository<Person, Integer>{
}
