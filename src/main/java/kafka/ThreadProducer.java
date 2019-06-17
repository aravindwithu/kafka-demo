package kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

import kafka.model.MsgModel;

/**
 * Producer Example in Apache Kafka
 * 
 * @author aravind venkit
 */
public class ThreadProducer extends Thread {
    private final KafkaProducer producer;
    private final String topic;
    private final Boolean isAsync;

    public static final String KAFKA_SERVER_URL = "localhost";
    public static final int KAFKA_SERVER_PORT = 9092;
    public static final String CLIENT_ID = "SampleProducer";
    private List<MsgModel> msgList = null;
    private int messageNo = 0;
    private Boolean exit;

    public ThreadProducer(String topic, Boolean isAsync) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", KAFKA_SERVER_URL + ":" + KAFKA_SERVER_PORT);
        properties.put("client.id", CLIENT_ID);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer(properties);
        this.topic = topic;
        this.isAsync = isAsync;
        this.exit = false;
        // initMsg();
    }

    // public synchronized void initMsg() {
    //     msgList = new ArrayList<>();
    //     MsgModel msg = null;
    //     while (messageNo <= 10) {
    //         msg = new MsgModel(messageNo, "Message_" + messageNo);
    //         this.msgList.add(msg);
    //         messageNo++;
    //     }
    // }

    public synchronized void addMsg(MsgModel msg) {
        this.msgList.add(msg);
        messageNo++;
    }

    public synchronized void exit(){
        this.exit = true;
    }

    public synchronized List<MsgModel> cloneMsgList() throws CloneNotSupportedException {
        List<MsgModel> tempList = new ArrayList<>();
        Iterator<MsgModel> iterator = this.msgList.iterator();
        while (iterator.hasNext()) {
            //tempList.add((MsgModel) iterator.next().clone());
        }
        this.msgList.removeAll(this.msgList);
        return tempList;
    }

    @Override
    public void run() {
        while (!exit) {
            if(this.msgList.size()>0){
                List<MsgModel> tempList = null;
                try {
                    tempList = cloneMsgList();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < tempList.size(); i++) {
                    sendMsg(tempList.get(i));
                }
                tempList.removeAll(tempList);
            }
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendMsg(MsgModel msg) {
        long startTime = System.currentTimeMillis();
        if (isAsync) { // Send asynchronously
            producer.send(new ProducerRecord(topic, msg.msgNo, msg.msgStr), new DemoCallBack(startTime, msg.msgNo, msg.msgStr));
        } else { // Send synchronously
            try {
                producer.send(new ProducerRecord(topic, msg.msgNo, msg.msgStr)).get();
                System.out.println("Sent message: (" + msg.msgNo + ", " + msg.msgStr + ")");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                // handle the exception
            }
        }
    }
}