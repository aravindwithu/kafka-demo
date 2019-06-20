package kafka;

public class KafkaConsumerApplication {
    public static void main(String[] args) throws InterruptedException {
        Consumer consumer = new Consumer();
        System.out.println("-------------------Consumer starts------------------");
        consumer.showMsg();
        System.out.println("--------------------Consumer ends-------------------");
    }
}