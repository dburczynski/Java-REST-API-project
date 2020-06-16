package com.example.demo.service;

import com.example.demo.domain.Person;

import java.util.List;

public interface PersonManager {

    void addPerson(Person person);

    Person findById(int id);

    List<Person> getAllPersons();

    void editPerson(Person person);

    void remove(int id);

    List<Person> findByCompanyName(String companyName);

}
