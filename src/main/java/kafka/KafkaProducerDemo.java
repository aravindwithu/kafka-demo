package kafka;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class KafkaProducerDemo {
    public static final String TOPIC = "sampleTopic";
    
    public static void main(String[] args) throws InterruptedException {

        SampleProducer producer = new SampleProducer(TOPIC, false);
        MsgModel msg =null;

        ObjectMapper mapper = new ObjectMapper();
        
		try {
           // for(int i = 0; i<10; i++){
                msg = new MsgModel(1, "select * from user", "csv", "DEVLZB", "avenkituswamy@netspend.com", "productionsupport@netspend.com", "Kafka test mail", "TempTestFile", ",");
                //producer.sendMsg(msg);
                //Convert object to JSON string

                System.out.println("\n-------------------------------------------------\n");

                System.out.println(msg);

                System.out.println("\n-------------------------------------------------\n");
                
                String jsonInString = mapper.writeValueAsString(msg);
                System.out.println(jsonInString);

                System.out.println("\n-------------------------------------------------\n");

                MsgModel msgRtn = mapper.readValue(jsonInString, MsgModel.class);
                System.out.println(msgRtn);

                // // Convert object to JSON string and pretty print
                // jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
                // System.out.println(jsonInString);
           // }	
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}