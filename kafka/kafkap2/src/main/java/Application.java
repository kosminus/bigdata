import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.GenericRecordBuilder;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.avro.AvroMapper;
import com.fasterxml.jackson.dataformat.avro.AvroSchema;
import org.apache.log4j.Logger;


public class Application {

    static Logger log = Logger.getLogger(Application.class.getName());
    public static String TOPIC = "kosmin";

    public static void main(String[] args) throws JsonMappingException {
        Customer c1 = new Customer.Builder()
                .setCustomerID(1)
                .setCustomerName("Maria")
                .build();

        Customer c2 = new Customer.Builder()
                .setCustomerID(2)
                .setCustomerName("Ana")
                .build();


        AppConfig appConfig= new AppConfig();
        appConfig.loadProperties();


        final AvroMapper avroMapper = new AvroMapper();
        final AvroSchema schema = avroMapper.schemaFor(Customer.class);


        Producer<String,GenericRecord> producer = new KafkaProducer<>(appConfig.properties);

        GenericRecordBuilder recordBuilder = new GenericRecordBuilder(schema.getAvroSchema());
        recordBuilder.set("customerID", c1.getCustomerID());
        recordBuilder.set("customerName", c1.getCustomerName());
        final GenericRecord genericRecord = recordBuilder.build();

        ProducerRecord<String,GenericRecord> record = new ProducerRecord<>(Application.TOPIC, "customer", genericRecord );

        producer.send(record);

        producer.close();

    }
}
