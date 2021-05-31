
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import org.apache.log4j.Logger;

import java.time.Duration;
import java.util.Collections;


public class Application {
    static Logger log = Logger.getLogger(Application.class.getName());


    public static void main(String[] args) {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(ConsumerConfig.loadProperties(args[0]));



     consumer.subscribe(Collections.singleton(ConsumerConfig.loadProperties(args[0]).getProperty("input.topic.name")));

     try{

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

            for (ConsumerRecord<String, String> record : records) {
                System.out.println("Message received: " + record.value());
            }

        }

    }
     finally {
         consumer.close();
     }
     }
}
