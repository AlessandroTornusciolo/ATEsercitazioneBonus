// Progetto a cura di Alessandro Tornusciolo
// Matricola 65566

package com.example.alessandrotornusciolo.esercitazionebonus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;

public class ModificaPassword extends AppCompatActivity {

    Persona utente;
    Button aggiorna;
    Button home;
    EditText nuova_pass;
    EditText conferma_pass;
    TextView username;
    TextView password_base;
    TextView messaggio_conferma;
    public static final String PERSON_EXTRA = "com.example.alessandrotornusciolo.esercitazionebonus";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modifica_password);

        aggiorna = findViewById(R.id.aggiorna_button);
        home = findViewById(R.id.home_button);
        nuova_pass = findViewById(R.id.nuova_pass);
        conferma_pass = findViewById(R.id.conferma_nuova_pass);
        username = findViewById(R.id.campo_user_modifica);
        password_base = findViewById(R.id.campo_pass_modifica);
        messaggio_conferma = findViewById(R.id.confirmText);

        messaggio_conferma.setVisibility(View.GONE);

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Login.PERSON_EXTRA);

        if(obj instanceof Persona) {
            utente = (Persona) obj;
        }

        username.setText(utente.getUsername());
        password_base.setText(utente.getPassword());

        aggiorna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(nuova_pass.getText() == null || nuova_pass.getText().length() == 0) {

                    nuova_pass.setError("Inserire una nuova password");

                } else if(conferma_pass.getText() == null || conferma_pass.getText().length() == 0) {

                    conferma_pass.setError("Inserire nuovamente la password scelta");

                } else if(nuova_pass.getText().toString().equals(conferma_pass.getText().toString())) {

                    if(!(nuova_pass.getText().toString().equals(utente.getPassword()))) {

                        changePass(nuova_pass.getText().toString());
                        utente.setPassword(nuova_pass.getText().toString());
                        password_base.setText(nuova_pass.getText().toString());
                        nuova_pass.setText("");
                        conferma_pass.setText("");
                        messaggio_conferma.setVisibility(View.VISIBLE);

                    } else {
                        nuova_pass.setError("La nuova password dev'essere differente dalla precedente");
                    }

                } else {

                    nuova_pass.setError("La nuova password e la conferma devono coincidere");
                    conferma_pass.setError("La nuova password e la conferma devono coincidere");

                }

            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModificaPassword.this, MainUtente.class);
                intent.putExtra(PERSON_EXTRA, utente);
                startActivity(intent);
                finish();
            }
        });

    }

    public void changePass(String password) {

        for(Persona p: Persona.elencoPersone) {
            if(p.getUsername().equals(utente.getUsername())) {
                //if(!(p.getPassword().equals(password))) {
                    p.setPassword(password);
                //} else {
                //    nuova_pass.setError("La nuova password dev'essere differente dalla precedente");
                //}
            }
        }

    }

}
