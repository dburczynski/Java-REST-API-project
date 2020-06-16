package com.example.demo.service;

import com.example.demo.domain.Person;

import java.io.PrintWriter;
import java.util.List;

public class CsvUtil {
    public static void downloadCsv(PrintWriter writer, List<Person> persons) {
        writer.write("id, firstName, surname, email, companyName, dateOfHire\n");
        for (Person person: persons) {
            writer.write(person.getId() + "," + person.getFirstName() + "," + person.getSurname() + "," + person.getEmail() + "," + person.getCompanyName() + "," + person.getDateOfHire()+"\n");
        }
    }
}
