package com.example.demo.service;

import com.example.demo.domain.Company;
import com.example.demo.domain.Person;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class CompanyInMemoryServiceConfig {
    @Bean
    public ArrayList<Company> importCsv2() throws IOException {
        List<Person> persons;
        List<Company> companies = new ArrayList<Company>();
        String fileName = "src/main/resources/Data2.csv";
        Path filePath= Paths.get(fileName);

        try (BufferedReader br = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)) {

            HeaderColumnNameMappingStrategy<Person> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(Person.class);

            CsvToBean csvToBean = new CsvToBeanBuilder(br).withType(Person.class).withMappingStrategy(strategy).withIgnoreLeadingWhiteSpace(true).build();
            persons = csvToBean.parse();
        }
        for(Person p: persons) {
            boolean exists = false;
            Company tempCompany = new Company();
            tempCompany.setCompanyName(p.getCompanyName());
            tempCompany.setOwner(p.getFirstName()+" "+p.getSurname());
            for(Company c: companies) {
                if(tempCompany.getCompanyName().equals(c.getCompanyName())){
                    exists = true;
                }
            }
            if(!exists)
                companies.add(tempCompany);
        }

        return new ArrayList<Company>(companies);
    }
}
