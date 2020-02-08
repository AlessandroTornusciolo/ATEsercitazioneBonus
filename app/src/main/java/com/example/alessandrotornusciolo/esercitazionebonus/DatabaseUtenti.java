// Progetto a cura di Alessandro Tornusciolo
// Matricola 65566

package com.example.alessandrotornusciolo.esercitazionebonus;

import java.util.ArrayList;
import java.util.List;

public class DatabaseUtenti extends ArrayList<Persona> {

    public List<Persona> getElenco() {
        return elenco;
    }

    public void setElenco(List<Persona> elenco) {
        this.elenco = elenco;
    }

    private List<Persona> elenco = Persona.getPersone();

    public DatabaseUtenti() {}

}
