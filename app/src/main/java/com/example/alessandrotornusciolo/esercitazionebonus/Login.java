// Progetto a cura di Alessandro Tornusciolo
// Matricola 65566

package com.example.alessandrotornusciolo.esercitazionebonus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    EditText username,password;
    Button accedi;
    Persona person;
    public static final String PERSON_EXTRA = "com.example.alessandrotornusciolo.esercitazionebonus";
    boolean user;
    boolean pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username = findViewById(R.id.login_username_field);
        password = findViewById(R.id.login_password_field);
        accedi = findViewById(R.id.button_accedi);

        accedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkCredentials()) {

                    if(pass) {

                        Intent intent = new Intent(Login.this, MainUtente.class);
                        intent.putExtra(PERSON_EXTRA, person);
                        startActivity(intent);
                        finish();
                    } else {
                        password.setError("Password errata");
                    }

                }

                if(username.getText() == null || username.getText().length() == 0) {
                    username.setError("Inserisci il tuo username");
                } else if(!user) {
                    username.setError("Username e/o password errati");
                    password.setError("Username e/o password errati");
                }

                if(password.getText() == null || password.getText().length() == 0) {
                    password.setError("Inserisci la tua password");
                }

            }
        });

    }

    // Funzione per il clickable della textview per la registrazione
    public void onClick(View v) {
        Intent register = new Intent(Login.this, Register.class);
        startActivity(register);
    }

    // Funzione che controlla se nella lista dei registrati esiste una corrispondenza username-password
    private boolean checkCredentials() {

        for(Persona p : Persona.elencoPersone) {
            if(p.getUsername().equals(username.getText().toString()) && p.getPassword().equals(password.getText().toString())) {
                person = p;
                this.user = true;
                this.pass = true;
                return true;
            }

            if(p.getUsername().equals(username.getText().toString()) && !(p.getPassword().equals(password.getText().toString()))) {
                this.user = true;
                this.pass = false;
                return true;
            } else if (!(p.getUsername().equals(username.getText().toString()))) {
                this.user = false;
                this.pass = false;
            }
        }

        return false;
    }
}
