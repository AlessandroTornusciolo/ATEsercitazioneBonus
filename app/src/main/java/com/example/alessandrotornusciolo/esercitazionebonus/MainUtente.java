// Progetto a cura di Alessandro Tornusciolo
// Matricola 65566

package com.example.alessandrotornusciolo.esercitazionebonus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.text.SimpleDateFormat;

import java.io.Serializable;

public class MainUtente extends AppCompatActivity {

    Persona utente;
    Button logout;
    public static final String PERSON_EXTRA = "com.example.alessandrotornusciolo.esercitazionebonus";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_utente);

        TextView benvenuto_utente = findViewById(R.id.home_benvenuto);
        TextView username = findViewById(R.id.campo_user);
        TextView password = findViewById(R.id.campo_pass);
        TextView citta = findViewById(R.id.campo_citta);
        TextView data = findViewById(R.id.campo_data);

        logout = findViewById(R.id.button_logout);

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Login.PERSON_EXTRA);

        if(obj instanceof Persona) {
            utente = (Persona) obj;
        }

        benvenuto_utente.setText(benvenuto_utente.getText().toString() + " " + utente.getUsername() + "!");
        username.setText(utente.getUsername());
        password.setText(utente.getPassword());
        citta.setText(utente.getCitta());
        data.setText(format.format(utente.getData().getTime()));

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainUtente.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

    }

    // Funzione per il clickable della textview per la modifica password
    public void onClick(View v) {
        Intent modifica = new Intent(MainUtente.this, ModificaPassword.class);
        modifica.putExtra(PERSON_EXTRA, utente);
        startActivity(modifica);
    }
}
