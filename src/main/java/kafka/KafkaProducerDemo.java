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
        RavenModel ravenReq =null;
        ObjectMapper mapper = new ObjectMapper();
        
		try {
            for(int i = 0; i<10; i++){
                ravenReq = new RavenModel("select * from user", "csv", "DEVLZB", "avenkituswamy@netspend.com", "productionsupport@netspend.com", "Kafka test mail", "TempTestFile", ",");
                System.out.println("\n--------------------ravenReq-----------------------------\n");

                System.out.println(ravenReq);

                System.out.println("\n--------------------ravenReqStr---------------------------\n");
                //Convert object to JSON string
                String ravenReqStr = mapper.writeValueAsString(ravenReq);
                System.out.println(ravenReqStr);

                System.out.println("\n-----------------------ProdeucerStart------------------------\n");
                msg = new MsgModel(i,ravenReqStr);
                producer.sendMsg(msg);

                System.out.println("\n-----------------------ProdeucerEnd--------------------------\n");

                // MsgModel msgRtn = mapper.readValue(ravenReqStr, MsgModel.class);
                // System.out.println(msgRtn);

                // // Convert object to JSON string and pretty print
                // jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
                // System.out.println(jsonInString);
            }	
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}