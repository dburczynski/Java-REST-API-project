package com.example.demo.service;

import com.example.demo.domain.Company;

import java.util.List;

public interface CompanyManager {

    void addCompany(Company company);

    Company findByName(String companyName);

    List<Company> getAllCompany();

    void editCompany(Company company);

    void remove(String companyName);

}