package com.example.demo.controller.api;

import com.example.demo.domain.Company;
import com.example.demo.domain.Person;
import com.example.demo.service.CompanyManager;
import com.example.demo.service.PersonManager;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class JsonController {

    private PersonManager pm;
    private CompanyManager cm;

    @Autowired
    public JsonController(PersonManager pm, CompanyManager cm) {
        this.pm = pm;
        this.cm = cm;
    }

    @GetMapping("/person")
    public ResponseEntity<Person> getPersonById(@RequestParam("id") int id){
        for (Person person : pm.getAllPersons()) {
            if(person.getId() == id ){
                return new ResponseEntity<Person>(person, HttpStatus.OK);
            }
        }
        return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/company")
    public ResponseEntity<HashMap<String,Object>> getCompany(@RequestParam("companyName") String companyName){
        Company returnCompany = null;
        for (Company company : cm.getAllCompany()) {
            if(company.getCompanyName().equals(companyName)){
                returnCompany = company;
                break;
            }
        }
        if(returnCompany != null) {
            int counter = 0;
            for(Person person: pm.getAllPersons()) {
                if(person.getCompanyName().equals(returnCompany.getCompanyName()))
                    counter++;
            }
            HashMap<String, Object> map = new HashMap<>();
            map.put("companyName", returnCompany.getCompanyName());
            map.put("owner", returnCompany.getOwner());
            map.put("number of employees", counter);
            return new ResponseEntity<HashMap<String, Object>>(map,HttpStatus.OK);
        }
        return new ResponseEntity<HashMap<String, Object>>(HttpStatus.NOT_FOUND);
    }
}
