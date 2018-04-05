package com.companies.jrevata.companiesapps;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.companies.jrevata.companiesapps.Models.Company;
import com.companies.jrevata.companiesapps.Repositories.CompanyRepository;

import java.util.List;

public class DetailActivity extends AppCompatActivity {


    TextView nameEditText, phoneEditText, addressEditText, infoEditText;
    ImageView imageView;
    Company company;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        nameEditText = findViewById(R.id.txtname1);
        phoneEditText = findViewById(R.id.txtphone1);
        addressEditText = findViewById(R.id.txtaddress1);
        infoEditText = findViewById(R.id.info);
        imageView = findViewById(R.id.picture_image1);
        Integer id = getIntent().getExtras().getInt("id");
        company = busqueda(id);
        Toast.makeText(this, company.getName(), Toast.LENGTH_LONG).show();
        //Toast.makeText(this,id.toString(),Toast.LENGTH_LONG).show();
        nameEditText.setText(company.getName());
        phoneEditText.setText(company.getPhone());
        addressEditText.setText(company.getAddress());
        infoEditText.setText(company.getInfo());
        int restId = getResources().getIdentifier(company.getLogo(), "drawable", getPackageName());
        imageView.setImageResource(restId);

    }

    public Company busqueda(Integer id) {
        Company company = new Company();
        List<Company> companies = CompanyRepository.getList();

        for (int i = 0; i < companies.size(); i++)
            if (companies.get(i).getId().equals(id))
                company = companies.get(i);


        return company;
    }

    private static final int PERMISSIONS_REQUEST = 100;

    public void call(View view) {

        // Check permission (Api 22 check in Manifest, Api 23 check by requestPermissions)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // Dont have permission => request one or many permissions (String[])
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, PERMISSIONS_REQUEST);
        } else {
            // Have permission
            openCallApplication();
        }
    }

    // On request permissions result
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST: {
                if (permissions[0].equals(Manifest.permission.CALL_PHONE)) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        openCallApplication();
                    } else {
                        Toast.makeText(this, "CALL_PHONE permissions declined!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    public void openCallApplication() {
        String phoneNumber = company.getPhone();
        if (phoneNumber.isEmpty()) {
            Toast.makeText(this, "Phone number required!", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNumber));

        // Is necesary to check permission again before startActivity
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED)
            startActivity(intent);
    }

    public void call1(View view) {
        Uri uri = Uri.parse(company.getUrl());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    private void enviarEmail() {
        //Instanciamos un Intent del tipo ACTION_SEND
        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        //Definimos la tipologia de datos del contenido dle Email en este caso text/html
        emailIntent.setType("text/html");
        // Indicamos con un Array de tipo String las direcciones de correo a las cuales
        //queremos enviar el texto
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{company.getEmail()});
        // Definimos un titulo para el Email
        emailIntent.putExtra(android.content.Intent.EXTRA_TITLE, "El Titulo");
        // Definimos un Asunto para el Email
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "El Asunto");
        // Obtenemos la referencia al texto y lo pasamos al Email Intent
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "q");
        try {
            //Enviamos el Correo iniciando una nueva Activity con el emailIntent.
            startActivity(Intent.createChooser(emailIntent, "Enviar E-mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(DetailActivity.this, "No hay ningun cliente de correo instalado.", Toast.LENGTH_SHORT).show();
        }
    }

    public void call3(View view){
        enviarEmail();
    }

}
