package kafka;

public class KafkaConsumerDemo {
    public static void main(String[] args) {
        SampleConsumer consumer = new SampleConsumer("testTopic");
        consumer.showMsg();
    }
}