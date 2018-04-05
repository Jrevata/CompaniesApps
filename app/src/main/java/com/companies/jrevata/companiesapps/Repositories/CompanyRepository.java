package com.companies.jrevata.companiesapps.Repositories;

import com.companies.jrevata.companiesapps.Models.Company;

import java.util.ArrayList;
import java.util.List;

public class CompanyRepository {
    private static List<Company> companies = new ArrayList<>();

    static {
        companies.add(new Company(100,"Restaurantes","Restaurante Revata","Mz ki lt 23 Ate", "7795501","restaurante.revata@gmail.com","http://www.google.com/","ic_company1","El mejor restaurante do mundo"));
        companies.add(new Company(200,"Restaurantes","Restaurante Axel","Mz L lt 24 Vitarte", "7795523","restaurante.axel@gmail.com","http://www.google.com/","ic_company2","El mejor restaurante do mundo"));
    }
    public static List<Company> getList(){
        return companies;
    }
}
