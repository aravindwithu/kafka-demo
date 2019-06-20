package kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.io.IOException;
import java.util.Collections;
import java.util.Properties;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.netspend.raven.services.MailRequest;
import com.netspend.raven.utilities.MailOperations;
import com.netspend.raven.model.Payload;

/**
 * Consumer in Apache Kafka
 * 
 * @author aravind venkit
 */
public class Consumer {
    private final String URL = "localhost";
    private final int PORT = 9092;
    private final String TOPIC = "sampleTopic";
    private final String GROUP_ID = "ravenConsumerGroup";
    private final String SERVER_DETAIL = URL + ":" + PORT;
    private final int giveUp = 1000;
    private int noRecordsCount = 0;
    private KafkaConsumer<Integer, String> consumer = null;

    public Consumer() {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, SERVER_DETAIL);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumer = new KafkaConsumer<>(properties);
    }

    public void showMsg() throws InterruptedException {
        consumer.subscribe(Collections.singletonList(TOPIC));
        while (true) {
            final ConsumerRecords<Integer, String> consumerRecords = consumer.poll(5000);
            
            if (consumerRecords.count() == 0) {
                noRecordsCount++;
                if (noRecordsCount > giveUp)
                    break;
                else
                    continue;
            }

            consumerRecords.forEach(record -> {
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    Payload payload = mapper.readValue(record.value(), Payload.class);
                    String payloadStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(payload);
                    System.out.println("Key: "+record.key() + "; partition: " + record.partition() + "; offset: " + record.offset());
                    System.out.println(payloadStr);

                    System.out.println("---------------------Mail request-------------------");
                   
                    switch(payload.getOutputType()) {
                        case "Mail":
                            MailOperations mailOperations = new MailOperations();
                            MailRequest mailRequest = new MailRequest(null, mailOperations, null);
                            mailRequest.processRequest(payload);
                          break;
                        case "File":
                            // FileOperations fileOperations = new FileOperations();
                            // FileRequest fileRequest = new FileRequest(null, null, fileOperations);
                            // fileRequest.processRequest(payload);
                          break;
                        case "DB":
                            // DBOperations dbOperations = new DBOperations();
                            // DBRequest dbRequest = new DBRequest(null, dbOperations, null);
                            // dbRequest.processRequest(payload);
                          break;
                        default:
                            throw new IOException("Invalid Output/Request Type!");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            consumer.commitAsync();
        }
        consumer.close();
    }
}