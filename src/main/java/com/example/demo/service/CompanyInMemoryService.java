package com.example.demo.service;

import com.example.demo.domain.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyInMemoryService implements CompanyManager{
    @Autowired
    @Qualifier("importCsv2")
    private List<Company> companies;

    @Override
    public void addCompany(Company company) {
        companies.add(company);
    }

    @Override
    public Company findByName(String companyName) {
        for(Company company: companies) {
            if(company.getCompanyName().equals(companyName))
                return company;
        }
        return null;
    }

    @Override
    public List<Company> getAllCompany() {
        return companies;
    }

    @Override
    public void editCompany(Company company) {
        for(Company edited: companies) {
            if(edited.getCompanyName().equals(company.getCompanyName())) {
                edited.setOwner(company.getOwner());
            }
        }
    }

    @Override
    public void remove(String companyName) {
        Company toRemove = null;
        for(Company company: companies) {
            if(company.getCompanyName().equals(companyName))
                toRemove = company;
        }
        companies.remove(toRemove);
    }
}
