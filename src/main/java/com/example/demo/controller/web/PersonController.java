package com.example.demo.controller.web;

import com.example.demo.domain.Person;
import com.example.demo.service.CsvUtil;
import com.example.demo.service.PersonManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;


@Controller("personwebcontroller")
public class PersonController {

    private PersonManager pm;

    @Autowired
    public PersonController(PersonManager pm) {
        this.pm = pm;
    }

    @GetMapping("/person")
    public String home(Model model) {
        model.addAttribute("persons", pm.getAllPersons());
        return "person-all";
    }

    @GetMapping("/person_add")
    public String newPerson(Model model) {
        model.addAttribute("person",new Person());
        return "person-add";
    }
    @PostMapping("/person_add")
    public String processOrderAdd(@Valid Person person, Errors errors){
        if(errors.hasErrors()){
            return "person-add";
        }

        pm.addPerson(person);
        return "redirect:/";
    }


    @GetMapping("/person_delete/{id}")
    public String deletePerson(@PathVariable("id") int id, Model model) {
        pm.remove(id);
        model.addAttribute("persons", pm.getAllPersons());
        return "person-all";
    }

    @GetMapping("/person_edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", pm.findById(id));
        return "person-edit";
    }
    @PostMapping("/person_edit")
    public String processOrderEdit(@Valid Person person, Errors errors, Model model) {
        if(errors.hasErrors()){
            return "person-edit";
        }
        pm.editPerson(person);
        model.addAttribute("persons",pm.getAllPersons());
        return "redirect:/person";
    }

    @GetMapping("/person_find")
    public String findPerson(Model model) {
        model.addAttribute("person", new Person());
        return "person-find";
    }

    @PostMapping("/person_find/answer")
    public String findPersonAnswer(Person person, Model model) {
        model.addAttribute("person", pm.findById(person.getId()));
        return "person-answer";
    }

    @GetMapping("/employee_find")
    public String findEmployee(Model model) {
        model.addAttribute("person", new Person());
        return "employee-find";
    }
    @PostMapping("/employee_find/answer")
    public String findEmployeeAnswer(Person person, Model model) {
        model.addAttribute("persons", pm.findByCompanyName(person.getCompanyName()));
        return "employee-answer";
    }

    @GetMapping("/download/persons.csv")
    public void downloadCsv(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; file=employee.csv");
        CsvUtil.downloadCsv(response.getWriter(), pm.getAllPersons()) ;
    }

}
