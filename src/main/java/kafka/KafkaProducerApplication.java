package kafka;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kafka.model.MsgModel;
import com.netspend.raven.model.Payload;

public class KafkaProducerApplication {
    
    public static void main(String[] args) throws InterruptedException {

        Producer producer = new Producer();
        MsgModel msg =null;
        Payload ravenReq =null;
        ObjectMapper mapper = new ObjectMapper();
        
		try {
            for(int i = 0; i<1; i++){
                ravenReq = new Payload();
                ravenReq.setQuery("select * from BI_EMAIL_REPORTS");
                ravenReq.setOutputType("Mail");
                ravenReq.setMailTo("avenkituswamy@netspend.com");
                ravenReq.setMailFrom("bi.reporting@netspend.com");
                ravenReq.setMailSubject("Generated report for email reports");
                ravenReq.setFileName("BI_EMAIL_REPORTS.csv");
                ravenReq.setFileDelimiter("|");

                String ravenReqStr = mapper.writeValueAsString(ravenReq);

                System.out.println("\n-----------------------ProdeucerStart------------------------\n");
                msg = new MsgModel(i,ravenReqStr);
                producer.sendMsg(msg);
                System.out.println("\n-----------------------ProdeucerEnd--------------------------\n");
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