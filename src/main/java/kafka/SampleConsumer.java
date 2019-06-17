package kafka;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.Properties;

/**
* Kafka Consumer with Example Java Application
*/
public class SampleConsumer {
    private String TOPIC = null;

    public static final String KAFKA_SERVER_URL = "localhost";
    public static final int KAFKA_SERVER_PORT = 9095;

    public SampleConsumer(String topic){
        this.TOPIC = topic;
    }

    private Consumer<Integer, String> createConsumer() {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_SERVER_URL + ":" + KAFKA_SERVER_PORT);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,
        "KafkaExampleConsumer");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
        IntegerDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
        StringDeserializer.class.getName());
        // Create the consumer using props.
        final Consumer<Integer, String> consumer =
        new KafkaConsumer<>(properties);
    
        // Subscribe to the topic.
        consumer.subscribe(Collections.singletonList(TOPIC));
        return consumer;
    }

    public void showMsg() throws InterruptedException {
        final Consumer<Integer, String> consumer = createConsumer();

        final int giveUp = 1000;   int noRecordsCount = 0;

        while (true) {
            final ConsumerRecords<Integer, String> consumerRecords =
                    consumer.poll(5000);

            if (consumerRecords.count()==0) {
                noRecordsCount++;
                if (noRecordsCount > giveUp) break;
                else continue;
            }

            consumerRecords.forEach(record -> {
                System.out.printf("Consumer Record:(%d, %s, %d, %d)\n",
                        record.key(), record.value(),
                        record.partition(), record.offset());


            });

            consumer.commitAsync();
        }
        consumer.close();
        System.out.println("DONE");
    }

}