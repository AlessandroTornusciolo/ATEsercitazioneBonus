// Progetto a cura di Alessandro Tornusciolo
// Matricola 65566

package com.example.alessandrotornusciolo.esercitazionebonus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Persona implements Serializable {

    private String username,password,citta;
    private Calendar data;
    public static List<Persona> elencoPersone = new ArrayList<>();

    public Persona() {
        this.setUsername("");
        this.setPassword("");
        this.setCitta("");
        //this.setData("");
    }

    public Persona(String username, String password, String citta, String data) {
        this.setUsername(username);
        this.setPassword(password);
        this.setCitta(citta);
        //this.setData(data);
        elencoPersone.add(this);

    }

    public static List<Persona> getPersone() {
        return elencoPersone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public static List<Persona> getElencoPersone() {
        return elencoPersone;
    }

    public static void setElencoPersone(List<Persona> elencoPersone) {
        Persona.elencoPersone = elencoPersone;
    }


}
