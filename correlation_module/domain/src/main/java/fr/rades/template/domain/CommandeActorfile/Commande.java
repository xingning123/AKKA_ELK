package fr.rades.template.domain.CommandeActorfile;

import fr.rades.template.infrastructure.interfaces.Etat;

import java.util.ArrayList;

public class Commande {
    private String id_cmd;
    private ArrayList<Etat> etatList;

    public void setId_cmd(String id_cmd) {
        this.id_cmd = id_cmd;
    }

    public void setEtatList(ArrayList<Etat> etatList) {
        this.etatList = etatList;
    }

    public String getId_cmd() {
        return id_cmd;
    }

    public ArrayList<Etat> getEtatList() {
        return etatList;
    }

    public Commande(String id_cmd, ArrayList<Etat> etatList) {
        this.id_cmd = id_cmd;
        this.etatList = etatList;
        if (this.etatList == null) {
            this.etatList = new ArrayList<>();
        }
    }


    @Override
    public String toString() {
        return "id " + id_cmd + "\n" + "Etat List: " + etatList;
    }

}


