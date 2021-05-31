import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import kafka.javaapi.producer.Producer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

public class Application {
    static Logger log = Logger.getLogger(Application.class.getName());

    public static Properties loadProperties(String fileName) throws IOException {
        final Properties envProps = new Properties();
        final FileInputStream input = new FileInputStream(fileName);
        envProps.load(input);
        input.close();

        return envProps;
    }
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        String configPath= "src/main/resources/config.properties";
        final Properties props = Application.loadProperties(configPath);
        final String topic = props.getProperty("topic");




        ObjectMapper objectMapper = new ObjectMapper();
        File jsonFile = new File("src/main/resources/cars.json");
        Car car = objectMapper.readValue(jsonFile, Car.class);

        KafkaProducer<String, Car> producer = new KafkaProducer<>(props);

        ProducerRecord<String, Car> record
                = new ProducerRecord<String, Car>(topic, null, car);
        producer.send(record).get();
        producer.close();
        System.out.println(car);

    }
}
