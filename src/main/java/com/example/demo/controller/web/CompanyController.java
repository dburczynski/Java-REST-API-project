package com.example.demo.controller.web;

import com.example.demo.domain.Company;
import com.example.demo.service.CompanyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller("companywebcontroller")
public class CompanyController {

    private CompanyManager cm;

    @Autowired
    public CompanyController(CompanyManager cm) {
        this.cm = cm;
    }

    @GetMapping("/company")
    public String home(Model model) {
        model.addAttribute("companies", cm.getAllCompany());
        return "company-all";
    }

    @GetMapping("/company_add")
    public String newCompany(Model model) {
        model.addAttribute("company",new Company());
        return "company-add";
    }
    @PostMapping("/company_add")
    public String processOrderAdd(@Valid Company company, Errors errors) {
        if (errors.hasErrors()) {
            return "company-add";
        }
        cm.addCompany(company);
        return "redirect:/";
    }

    @GetMapping("/company_delete/{companyName}")
    public String deletePerson(@PathVariable("companyName") String companyName, Model model) {
        cm.remove(companyName);
        model.addAttribute("companies", cm.getAllCompany());
        return "company-all";
    }



}
