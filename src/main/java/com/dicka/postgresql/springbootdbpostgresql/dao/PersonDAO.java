package com.dicka.postgresql.springbootdbpostgresql.dao;

import com.dicka.postgresql.springbootdbpostgresql.model.Person;

import java.util.List;

public interface PersonDAO {

    Person insertPerson(Person person);
    Person updatePerson(Person person);
    void deletePerson(Person person);
    Person findOnePerson(int idperson);
    List<Person> findAllPerson();
    boolean ifExistPerson(Person person);
}
