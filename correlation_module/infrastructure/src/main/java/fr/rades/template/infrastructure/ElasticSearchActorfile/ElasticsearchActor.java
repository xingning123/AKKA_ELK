package fr.rades.template.infrastructure.ElasticSearchActorfile;
import akka.actor.AbstractActor;
import akka.actor.Props;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.apache.http.HttpHost;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import fr.rades.template.infrastructure.CreateurLotRequetesfile.MessageDeCreateurLot;

public class ElasticsearchActor extends AbstractActor {
    private final RestClient client;
    private static final Logger log = LogManager.getLogger(ElasticsearchActor.class);

    public ElasticsearchActor(String url) {
        // Constructeur de la classe, il crée un client RestClient basé sur l'URL fournie
        this.client = createRestClient(url);

    }
    private RestClient createRestClient(String url) {
        // Méthode privée qui crée et configure le RestClient avec l'URL Elasticsearch fournie
        String[] hostAndPort = url.split(":");
        String hostname = hostAndPort[0];
        int port = Integer.parseInt(hostAndPort[1]);
        RestClientBuilder builder = RestClient.builder(new HttpHost(hostname, port));
        return builder.build();
    }

    public static Props props(String url) {
        return Props.create(ElasticsearchActor.class, url);
    }



    private void handleMessage(MessageDeCreateurLot message) {
        // Méthode pour traiter le message reçu par l'acteur
        String requestBody = message.getCorps();

        HttpEntity entity = new NStringEntity(requestBody, ContentType.APPLICATION_JSON);

        Request request = new Request("POST",message.getEndPoint());
        request.setEntity(entity);


        try {
            Response response = client.performRequest(request);
            log.info("Message envoyé à Elasticsearch: " + message.getCorps());
            log.info("Message reçu par Elasticsearch" + response);
        } catch (IOException e) {
            log.error("Une exception s'est produite lors de la requête Elasticsearch.",e);
        }
    }
    private Request createRequest(String method ,String endpoint) {
        return new Request(method,endpoint);
    }
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(MessageDeCreateurLot.class, this::handleMessage)
                .build();
    }
    @Override
    public void postStop() {
        // Méthode appelée lorsque l'acteur est arrêté, elle ferme le client RestClient
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
