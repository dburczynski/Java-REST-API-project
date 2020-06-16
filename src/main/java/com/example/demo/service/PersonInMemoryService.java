package com.example.demo.service;

import com.example.demo.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PersonInMemoryService implements PersonManager {

    @Autowired
    @Qualifier("importCsv")
    private List<Person> persons;

    public void addPerson(Person person) {
       person.setId(getNewId());
       persons.add(person);
    }

    public int getNewId() {
        int maxId = 0;
        for(Person person : persons) {
            if(person.getId() > maxId)
                maxId = person.getId();
        }
        return maxId+1;
    }

    @Override
    public Person findById(int id) {
        for(Person person: persons) {
            if(person.getId() == id) {
                return person;
            }
        }
        return null;
    }

    @Override
    public void editPerson(Person person) {
        for (Person edited : persons) {
            if (edited.getId() == person.getId()) {
                edited.setFirstName(person.getFirstName());
                edited.setSurname(person.getSurname());
                edited.setEmail(person.getEmail());
                edited.setCompanyName(person.getCompanyName());
                edited.setDateOfHire(person.getDateOfHire());
            }
        }
    }

    public List<Person> getAllPersons() {return persons; }

    @Override
    public void remove(int id) {
        Person toRemove = null;
        for(Person person : persons) {
            if(person.getId() == id) {
                toRemove = person;
                break;
            }
        }
        if(toRemove != null) {
            persons.remove(toRemove);
        }
    }

    @Override
    public List<Person> findByCompanyName(String companyName) {
        List<Person> tempList = new ArrayList<Person>();
        for(Person person: persons) {
            if(person.getCompanyName().equals(companyName))
                tempList.add(person);
        }
        if(tempList.size() != 0)
            return tempList;
        else return null;
    }
}



