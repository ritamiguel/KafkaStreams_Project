import Rest.Utilities;

import java.util.Properties;

public class PurchasesOrders {

    private String produce_topic = "purchases_topic";
    private String consume_topic = "DBInfo_topic";

    public static void main(String[] args) throws Exception {
        Properties props = Utilities.consumerAndProducer();




    }
}
