package fr.rades.template.infrastructure.ElasticSearchActorfile;

public class MessageDeCreateurLot {
    private String corps;
    private String endPoint;
    private String method;

    public MessageDeCreateurLot(String endPoint, String corps, String method) {
        this.corps = corps;
        this.endPoint = endPoint;
        this.method = method;
    }

    public String getEndPoint() {
        return endPoint ;
    }

    public String getCorps() {
        return corps ;
    }
    public String getMethod (){
        return method;
    }


    public void setMessage(String endPoint, String corps,String method) {
        this.corps = corps;
        this.endPoint = endPoint;
        this.method = method;
    }
}

