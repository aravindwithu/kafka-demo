package kafka;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.common.serialization.IntegerSerializer;

import kafka.model.MsgModel;
import org.apache.kafka.clients.producer.ProducerConfig;

/**
 * Producer in Apache Kafka
 * 
 * @author aravind venkit
 */
public class Producer {
    private final KafkaProducer<Integer,String> producer;
    private final String URL = "localhost";
    private final int PORT = 9092;
    private final String CLIENT_ID = "sampleTopic";
    private final String TOPIC = "sampleTopic";
    private final Boolean isAsync = false;
    private final String SERVER_DETAIL = URL + ":" + PORT;

    public Producer() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, SERVER_DETAIL);
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, CLIENT_ID);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producer = new KafkaProducer<>(properties);
     }

    public void sendMsg(MsgModel msg) {
        long startTime = System.currentTimeMillis();
        ProducerRecord<Integer,String> record= new ProducerRecord<>(TOPIC, msg.getMsgNo(), msg.getMsgStr());
        if (isAsync) { // Send asynchronously
            producer.send(record, new DemoCallBack(startTime, msg.getMsgNo(), msg.msgStr));
        } else { 
            try {// Send synchronously
                producer.send(record).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Sent message: (" + msg.getMsgNo() + ", " + msg.msgStr + ")");
    }
}