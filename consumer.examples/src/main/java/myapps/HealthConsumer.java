package myapps;

import myapps.HealthDeserializer;
import myapps.HealthMetadata;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class HealthConsumer {
    private final static String TOPIC = "test-dump";
    private final static String BOOTSTRAP_SERVERS = "localhost:9092";

    private static Consumer<String, HealthMetadata> createConsumer() {
        final Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                    BOOTSTRAP_SERVERS);
        props.put(ConsumerConfig.GROUP_ID_CONFIG,
                "KafkaExampleConsumer");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class.getName());
        //Custom Deserializer
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                HealthDeserializer.class.getName());
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 500);
        // Create the consumer using props.
        final Consumer<String, HealthMetadata> consumer =
                new KafkaConsumer<>(props);
        //Subscribe to the topic.
        consumer.subscribe(Collections.singletonList(
                            TOPIC));
        return consumer;
    }
    static void runConsumer() throws InterruptedException {
        final Consumer<String, HealthMetadata> consumer = createConsumer();
        final Map<String, HealthMetadata> map = new HashMap<>();
        try {
            final int giveUp = 1000; int noRecordsCount = 0;
            int readCount = 0;
            while (true) {
                final ConsumerRecords<String, HealthMetadata> consumerRecords =
                        consumer.poll(1000);
                if (consumerRecords.count() == 0) {
                    noRecordsCount++;
                    if (noRecordsCount > giveUp) break;
                    else continue;
                }
                readCount++;
                consumerRecords.forEach(record -> {
                    map.put(record.key(), record.value());
                });
                if (readCount % 100 == 0) {
                    displayRecordStatsAndHealthData(map, consumerRecords);
                }
                consumer.commitAsync();
            }
        }
        finally {
            consumer.close();
        }
        System.out.println("DONE");
    }
    private static void displayRecordStatsAndHealthData(
            final Map<String, HealthMetadata> HealthMetadataMap,
            final ConsumerRecords<String, HealthMetadata> consumerRecords) {
        System.out.printf("New ConsumerRecords par count %d count %d\n",
                consumerRecords.partitions().size(),
                consumerRecords.count());
        HealthMetadataMap.forEach((s, HealthMetadata) ->
                System.out.printf("Name: %s Age: %d Weight: %d \n",
                        HealthMetadata.getName(),
                        HealthMetadata.getAge(),
                        HealthMetadata.getWeight()));
        System.out.println();
    }
    public static void main(String... args) throws Exception {
        runConsumer();
    }
}