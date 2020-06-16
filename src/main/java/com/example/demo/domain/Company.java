package com.example.demo.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class Company {

    @NotNull(message = "Company name is required")
    @Size(min = 2, message = "Company name should have at least 2 characters")
    String companyName;

    @NotNull(message = "Company should have a owner")
    @Size(min = 2, message = "Company name should have at least 2 characters")
    String ownerName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getOwner() {
        return ownerName;
    }

    public void setOwner(String ownerName) {
        this.ownerName = ownerName;
    }
}
