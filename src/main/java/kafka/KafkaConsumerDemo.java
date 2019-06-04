package kafka;

public class KafkaConsumerDemo {
    public static void main(String[] args) throws InterruptedException {
        final String TOPIC = "sampleTopic";
        SampleConsumer consumer = new SampleConsumer(TOPIC);
        consumer.showMsg();
    }
}