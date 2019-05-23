package kafka;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.kafka.common.serialization.Serializer;


public class KafkaJsonSerializer implements Serializer {

    //private Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public void configure(Map map, boolean b) {

    }

    @Override
    public byte[] serialize(String s, Object o) {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            retVal = objectMapper.writeValueAsBytes(o);
        } catch (Exception e) {
            //logger.error(e.getMessage());
        }
        return retVal;
    }

    @Override
    public void close() {

    }
}