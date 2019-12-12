import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;
import java.util.Random;

public class Customer {

    private static String produce_topic = "sales_topic";
    private static String consume_topic = "DBInfo";
    private static Properties props_cons;
    private static Properties props_prod;
    private static ArrayList<String> values = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        props_cons = Utilities.consumerProps();
        props_prod = Utilities.producerProps();
        consume_info(props_cons);
        produce_info(props_prod);
    }

    private static void produce_info(Properties props){
        Producer<String, String> producer = new KafkaProducer<>(props);

        String random_value = values.get(new Random().nextInt(values.size()));
        producer.send(new ProducerRecord<String, String>(produce_topic, random_value));
    }

    private static void consume_info(Properties props) {

        while (true) {
            try (final KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {
                consumer.subscribe(Collections.singletonList(consume_topic));

                final ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(30));

                // get replies
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("\n[NOTIFICATION] " + record.value());
                    values.add(record.value());
                }
            }
        }
    }


}
