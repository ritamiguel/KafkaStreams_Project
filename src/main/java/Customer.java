import java.util.Properties;

public class Customer {

    private String produce_topic = "sales_topic";
    private String consume_topic = "DBInfo_topic";

    public static void main(String[] args) throws Exception {
        Properties props = Utilities.consumerAndProducer();

    }
}
