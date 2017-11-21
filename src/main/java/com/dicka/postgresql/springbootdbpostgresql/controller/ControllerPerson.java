package com.dicka.postgresql.springbootdbpostgresql.controller;

import com.dicka.postgresql.springbootdbpostgresql.dao.PersonDAO;
import com.dicka.postgresql.springbootdbpostgresql.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
@Service
public class ControllerPerson {

    private PersonDAO personDAO;

    private Logger LOGGER =
            LoggerFactory.getLogger(ControllerPerson.class);

    @Autowired
    public ControllerPerson(PersonDAO personDAO){
        this.personDAO = personDAO;
    }

    @PostMapping(value = "/insertPerson")
    public ResponseEntity<Person>insertPerson(@RequestBody Person person, UriComponentsBuilder builder){
        LOGGER.debug("insert person");
        if(personDAO.ifExistPerson(person)){
            LOGGER.debug("data conflict");
            return new ResponseEntity<Person>(HttpStatus.CONFLICT);
        }

        personDAO.insertPerson(person);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/insertPerson/{idperson}")
                .buildAndExpand(person.getIduser()).toUri());
        return new ResponseEntity<Person>(headers, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deletePerson/{idperson}")
    public ResponseEntity<Void>deletePerson(@PathVariable String idperson){
        LOGGER.debug("delete person");
        Person person = personDAO.findOnePerson(Integer.parseInt(idperson));
        if(person == null){
            LOGGER.debug("data kosong");
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
        personDAO.deletePerson(person);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/updatePerson/{idperson}")
    public ResponseEntity<Person>updatePerson(@PathVariable String idperson,
                                              @RequestBody Person person,
                                              UriComponentsBuilder builder){
        LOGGER.debug("update person");
        Person currentPerson = personDAO.findOnePerson(Integer.parseInt(idperson));
        if(currentPerson == null){
            LOGGER.debug("data kosong");
            return new ResponseEntity<Person>(HttpStatus.BAD_REQUEST);
        }
        currentPerson.setEmail(person.getEmail());
        currentPerson.setKelamin(person.getKelamin());
        currentPerson.setNama(person.getNama());
        currentPerson.setTelepon(person.getTelepon());
        currentPerson.setUsia(person.getUsia());

        personDAO.updatePerson(currentPerson);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/updatePerson/{idperson}")
                .buildAndExpand(person.getIduser()).toUri());
        return new ResponseEntity<Person>(currentPerson, headers, HttpStatus.CREATED);
    }

    @GetMapping(value = "/persons")
    public ResponseEntity<List<Person>>findAll(){
        LOGGER.info("list person");
        List<Person> listperson = personDAO.findAllPerson();
        if(listperson.isEmpty()){
            LOGGER.info("data kosong");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<Person>>(listperson, HttpStatus.OK);
    }

    @GetMapping(value = "/persons/{idperson}")
    public ResponseEntity<Person>findOnePerson(@PathVariable String idperson,
                                               UriComponentsBuilder builder){
        LOGGER.info("findone person");
        Person person = personDAO.findOnePerson(Integer.parseInt(idperson));
        if(person == null){
            LOGGER.debug("data kosong");
            return new ResponseEntity<Person>(HttpStatus.BAD_REQUEST);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/persons/{idperson}")
                .buildAndExpand(person.getIduser()).toUri());
        return new ResponseEntity<Person>(person, HttpStatus.OK);
    }

}
