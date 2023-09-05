package fr.rades.template.domain.CommandeActorfile;

import java.util.Date;

public class Etats {
    private String nom_etat;
    private Date date_entree;
    private Date date_sortie;

    public Etats(String nom_etat, Date date_entree, Date date_sortie) {
        this.nom_etat = nom_etat;
        this.date_entree = date_entree;
        this.date_sortie = date_sortie;
    }

    public String getNom_etat() {
        return nom_etat;
    }

    public void setNom_etat(String nom_etat){
        this.nom_etat = nom_etat;
    }

    public Date getDate_entree() {
        return date_entree;
    }

    public void setDate_entree(Date date_entree) {
        this.date_entree = date_entree;
    }

    public void setDate_sortie(Date date_sortie) {
        this.date_sortie = date_sortie;
    }

    public Date getDate_sortie() {
        return date_sortie;
    }

    @Override
    public String toString() {
        return "Etats [nom_etat=" + nom_etat + ", date_entree=" + date_entree + ", date_sortie=" + date_sortie + "]";
    }

}

