import java.io.IOException;
import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;


public class KafkaS {

    public static void main(String[] args) throws InterruptedException, IOException {

        //Kafka Streams reads from Sales Topic
        String topicName = args[0].toString();   //Sales_topic

        //Kafka Streams reads from Purchases Topic
        String secondtopicName = args[0].toString(); //Purchases_topic

        String outtopicname = "results_topic";


        java.util.Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "exercises-application");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.Long().getClass());


        //Sales Topic
        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, Long> lines = builder.stream(topicName);


        KTable<String, Long> outlines = lines.groupByKey().count();
        outlines.mapValues(v -> "" + v).toStream().to(outtopicname, Produced.with(Serdes.String(), Serdes.String()));


        //Purchases Topic
        StreamsBuilder secondbuilder = new StreamsBuilder();
        KStream<String, Long> secondlines = builder.stream(secondtopicName);


        KTable<String, Long> secondoutlines = secondlines.groupByKey().count();
        secondoutlines.mapValues(v -> "" + v).toStream().to(outtopicname, Produced.with(Serdes.String(), Serdes.String()));


        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();

        System.out.println("Kafka Streans reading stream from topic: " + topicName);


        KafkaStreams secondstreams = new KafkaStreams(secondbuilder.build(), props);
        secondstreams.start();

        System.out.println("Kafka Stream reading stream from topic " + secondtopicName);

    }
}
