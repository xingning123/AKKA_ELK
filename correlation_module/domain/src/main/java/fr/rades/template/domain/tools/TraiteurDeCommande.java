package fr.rades.template.domain.tools;

import fr.rades.template.domain.CommandeActorfile.Commande;
import fr.rades.template.infrastructure.interfaces.Etat;
import fr.rades.template.domain.TraiteurMessagefile.MessageTraiteePourCommande;

import java.util.*;

public class TraiteurDeCommande {

    public static Commande handleMessage(MessageTraiteePourCommande updatedMessage, Commande commande ) {

        ArrayList<Etat> listeEtat = commande.getEtatList();

        boolean notExist = true;
        for (int i = 0; i < listeEtat.size(); i++) {
            Etat etatExistant = listeEtat.get(i);

            if (etatExistant.getNomEtat().equals(updatedMessage.getNomEtat())) {
                notExist = false;
                etatExistant.setDateDebut(updatedMessage.getDateEntree());
                etatExistant.setDateFin(updatedMessage.getDateSortie());
                System.out.println("etat existant : " + updatedMessage + "\n" );


                break;
            }
            commande.setId_cmd(updatedMessage.getIdCommande());
        }

        if (notExist) {
            Etat newEtat = new Etat(updatedMessage.getNomEtat(), updatedMessage.getDateEntree(), updatedMessage.getDateSortie());
            listeEtat.add(newEtat);

            System.out.println("not exist : " + updatedMessage + "\n");
            commande.setId_cmd(updatedMessage.getIdCommande());

        }

        String commandeId = updatedMessage.getIdCommande();
        return commande;
//        createurLotRequetes.tell(updatedMessage, self);
//        System.out.println("Tell UpdatedMessage to CreateurLotRequetes: " + updatedMessage);
//
    }

}


