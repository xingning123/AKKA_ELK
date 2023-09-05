package fr.rades.template.domain.CommandeActorfile;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import fr.rades.template.domain.TraiteurMessagefile.MessageTraiteePourCommande;
import fr.rades.template.domain.tools.TraiteurDeCommande;
import fr.rades.template.domain.TraiteurMessagefile.TraiteurDeMessage;
import fr.rades.template.infrastructure.CreateurLotRequetesfile.MessagePourCreateurLot;

import java.util.ArrayList;
import java.util.List;


public class CommandeActor extends AbstractActor {

    private ActorRef createurLotRequetes;
    private Commande commande;

    public CommandeActor(ActorRef createurLotRequetes) {
        this.createurLotRequetes = createurLotRequetes;
        this.commande = new Commande("",new ArrayList<>());
    }

//    public CommandeActor() {}

    public static Props props(ActorRef createurLotRequetes) {
        return Props.create(CommandeActor.class, () -> new CommandeActor(createurLotRequetes));
    }


    @Override

    public Receive createReceive() {

        return receiveBuilder()

                .match(TraiteurDeMessage.UpdateMessagePourCommandeActor.class, message -> {
                    // Récupérer les messages mis à jour du TraiteurDeMessage.UpdateMessagePourCommandeActor
                    System.out.println("in commande Actor"+message);
                    List<MessageTraiteePourCommande> updatedMessages = message.getMessagesPourCommandeActor();

                    String actorName = self().path().name();
                    // Voit l'output sur le terminal avec le nom de l'acteur
                    for (MessageTraiteePourCommande updatedMessage : updatedMessages) {
                        System.out.println("L'acteur est : " + actorName + "\n" +
                                "Received Updated Message in CommandeActor: " + updatedMessage + "\n");
                        this.commande = TraiteurDeCommande.handleMessage(updatedMessage, this.commande);
                        MessagePourCreateurLot msg = new MessagePourCreateurLot(commande.getId_cmd(), commande.getEtatList());
                        this.createurLotRequetes.tell(msg, getSelf());
                    }

                })

                .build();
    }



}




