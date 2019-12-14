package Rest;


import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Properties;

@Path("/products")
public class ProductKeeper {

    @GET
    @Path("/orders")
    @Produces(MediaType.APPLICATION_JSON)
    public int createReordersView() throws InterruptedException {
        java.util.Properties props = new Properties();

        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "reorders_id");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        StreamsBuilder builder = new StreamsBuilder();
        /*KStream<String, String> lines = builder.stream(statistics_quantity);

        KTable<String, Long> outlines = lines.groupByKey().count(Materialized.as(tablename));
        outlines.mapValues(v -> "" + v).toStream();

        stream_quantity = new KafkaStreams(builder.build(), props);
        stream_quantity.start();
        System.out.println(stream_quantity.state());

        Thread.sleep(10000);
        int count = 0;

        try {
            ReadOnlyKeyValueStore<String, Long> keyValueStore = stream_quantity.store(tablename, QueryableStoreTypes.keyValueStore());
            System.out.println();
            // Get the values for a range of keys available in this application instance
            KeyValueIterator<String, Long> range = keyValueStore.all();


            while (range.hasNext()) {
                KeyValue<String, Long> next = range.next();
                System.out.println("count for " + next.key + ": " + next.value);
                count++;
            }
            range.close();

        } catch (Exception e){
            e.printStackTrace();
        }

        return count;*/
        return 0;
    }
}
