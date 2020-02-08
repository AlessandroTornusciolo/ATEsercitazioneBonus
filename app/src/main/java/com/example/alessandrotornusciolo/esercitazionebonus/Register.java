// Progetto a cura di Alessandro Tornusciolo
// Matricola 65566

package com.example.alessandrotornusciolo.esercitazionebonus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Register extends AppCompatActivity {

    EditText username,password,conferma,citta,data;
    Button registrati;
    Persona person;
    DatePickerFragment datePickerFragment;

    public static final String PERSON_EXTRA = "package com.example.alessandrotornusciolo.esercitazionebonus";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        datePickerFragment = new DatePickerFragment();

        // creo una nuova persona
        person = new Persona();

        // recupero gli id dei campi
        username = findViewById(R.id.username_field);
        password = findViewById(R.id.password_field);
        conferma = findViewById(R.id.confirm_password_field);
        citta = findViewById(R.id.citta_field);
        data = findViewById(R.id.date_field);
        registrati = findViewById(R.id.button_registrati);

        registrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInput()) {
                    UpdatePerson();
                    Intent login = new Intent(Register.this, Login.class);
                    startActivity(login);
                }

            }
        });

        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerFragment.show(getSupportFragmentManager(), "date picker");
            }
        });

        data.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    datePickerFragment.show(getSupportFragmentManager(), "datePicker");
                }
            }
        });

        datePickerFragment.setOnDatePickerFragmentChanged(new DatePickerFragment.DatePickerFragmentListener() {
            @Override
            public void onDatePickerFragmentOkButton(DialogFragment dialog, Calendar date) {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                data.setText(format.format(date.getTime()));
            }

            @Override
            public void onDatePickerFragmentCancelButton(DialogFragment dialog) {

            }
        });
    }

    private void UpdatePerson() {

        // aggiorno persona con i dati inseriti dall'utente
        this.person.setUsername(this.username.getText().toString());
        this.person.setPassword(this.password.getText().toString());
        this.person.setCitta(this.citta.getText().toString());
        this.person.setData(this.datePickerFragment.getDate());
        Persona.elencoPersone.add(this.person);
    }

    // Funzione per il check dei dati inseriti
    private boolean checkInput() {

        boolean errors = true;

        if(username.getText() == null || username.getText().length() == 0) {
            username.setError("Inserire un username");
            errors = false;
        } else {
            username.setError(null);
        }

        if(password.getText() == null || password.getText().length() == 0) {
            password.setError("Scegliere una password");
            errors = false;
        } else {
            password.setError(null);
        }

        if(conferma.getText() == null || conferma.getText().length() == 0) {
            conferma.setError("Conferma la tua password");
            errors = false;
        } else {
            conferma.setError(null);
        }

        if(citta.getText() == null || citta.getText().length() == 0) {
            citta.setError("Inserisci una citta di provenienza");
            errors = false;
        } else {
            citta.setError(null);
        }

        if(data.getText() == null || data.getText().length() == 0) {
            data.setError("Inserire un username");
            errors = false;
        } else {
            data.setError(null);
        }

        // check perché password e conferma coincidano
        if(!(password.getText().toString().equals(conferma.getText().toString()))) {
            password.setError("Le due password non coincidono");
            conferma.setError("Le due password non coincidono");
            errors = false;
        } else {
            password.setError(null);
            conferma.setError(null);
        }

        return errors;
    }



}
