package com.dicka.postgresql.springbootdbpostgresql.dao;

import com.dicka.postgresql.springbootdbpostgresql.exception.AlreadyException;
import com.dicka.postgresql.springbootdbpostgresql.model.Person;
import com.dicka.postgresql.springbootdbpostgresql.repository.RepositoryPerson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
@Validated
public class PersonDAOImpl implements PersonDAO{

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonDAOImpl.class);

    private RepositoryPerson repositoryPerson;

    @Autowired
    public PersonDAOImpl(RepositoryPerson repositoryPerson){
        this.repositoryPerson = repositoryPerson;
    }

    @Override
    public Person insertPerson(Person person) {
        LOGGER.debug("insert person");
        Person personExist = repositoryPerson.findOne(person.getIduser());
        if(personExist != null){
            new AlreadyException(String.format("kode id sudah ada", person.getIduser()));
        }
        return repositoryPerson.save(person);
    }

    @Override
    public Person updatePerson(Person person) {
        LOGGER.debug("update person");
        if(!entityManager.contains(person))
            person = entityManager.merge(person);
        return person;
    }

    @Override
    public void deletePerson(Person person) {
        LOGGER.debug("delete person");
        repositoryPerson.delete(person);
    }


    @Override
    public Person findOnePerson(int idperson) {
        LOGGER.debug("findone person");
        return repositoryPerson.findOne(idperson);
    }

    @Override
    public List<Person> findAllPerson() {
        LOGGER.debug("find all person");
        return repositoryPerson.findAll();
    }

    @Override
    public boolean ifExistPerson(Person person) {
        LOGGER.debug("exist person");
        return findOnePerson(person.getIduser()) != null;
    }
}
