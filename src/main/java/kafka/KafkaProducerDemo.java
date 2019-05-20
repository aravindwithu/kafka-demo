package kafka;

public class KafkaProducerDemo {
    public static final String TOPIC = "sampleTopic";
    
    public static void main(String[] args) {
        boolean isAsync = false;
        SampleProducer producerThread = new SampleProducer(TOPIC, isAsync);
        // start the producer
        producerThread.start();

        //producerThread.stop();
 
    }
}