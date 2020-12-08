package com.example.snowtam.data;

public class LigneOaci {

    private String lettre;
    private String ligne;

    public LigneOaci(String lettre, String ligne){
        this.lettre = lettre;
        this.ligne = ligne;
    }

    public String getLettre() {
        return lettre;
    }

    public void setLettre(String lettre) {
        this.lettre = lettre;
    }

    public String getLigne() {
        return ligne;
    }

    public void setLigne(String ligne) {
        this.ligne = ligne;
    }
}
