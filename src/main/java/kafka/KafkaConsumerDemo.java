package kafka;

public class KafkaConsumerDemo {
    public static void main(String[] args) throws InterruptedException {
        final String TOPIC = "replicatedTopic2";
        SampleConsumer consumer = new SampleConsumer(TOPIC);
        consumer.showMsg();
    }
}