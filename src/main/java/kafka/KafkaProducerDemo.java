package kafka;

public class KafkaProducerDemo {
    public static final String TOPIC = "sampleTopic";
    
    public static void main(String[] args) throws InterruptedException {
        // SampleProducer producerThread = new SampleProducer(TOPIC, false);
        // producerThread.start();
        // Boolean exit = false;
        // while(!exit){
        //     producerThread.addMsg();
        //     producerThread.addMsg();
        //     producerThread.addMsg();
        //     producerThread.addMsg();
        //     producerThread.addMsg();
        //     producerThread.exit();
        //     exit = true;
        //     Thread.sleep(10000);
        // }

        SampleProducer producer = new SampleProducer(TOPIC, false);
        MsgModel msg =null;

        for(int i = 0; i<10; i++){
            msg = new MsgModel(i, "select * from user", "csv", "DEVLZB", "avenkituswamy@netspend.com", "productionsupport@netspend.com", "Kafka test mail", "TempTestFile", ",");
            producer.sendMsg(msg);
        }
    }
}