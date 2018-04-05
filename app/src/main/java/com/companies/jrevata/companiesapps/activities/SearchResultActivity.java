package com.companies.jrevata.companiesapps.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.companies.jrevata.companiesapps.Adapters.CompanyAdapter;
import com.companies.jrevata.companiesapps.Models.Company;
import com.companies.jrevata.companiesapps.R;
import com.companies.jrevata.companiesapps.Repositories.CompanyRepository;

import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        CompanyAdapter companyAdapter = new CompanyAdapter();
        String busqueda = getIntent().getExtras().getString("busqueda");

        Toast.makeText(this,busqueda,Toast.LENGTH_LONG).show();
        companyAdapter.setCompanies(listCompany(busqueda));



        recyclerView.setAdapter(companyAdapter);
    }

    private List<Company> listCompany(String busqueda){
        List<Company> companies = new ArrayList<>();
        companies = CompanyRepository.getList();

        List<Company> companies1 = new ArrayList<>();

        for(int i = 0; i < companies.size(); i++) {
            String name = companies.get(i).getName();
            if (name.indexOf(busqueda)!= -1) {
                companies1.add(companies.get(i));
            }
        }

        return companies1;
    }
}
