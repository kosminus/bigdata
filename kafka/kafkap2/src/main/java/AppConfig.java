import java.util.Properties;

public class AppConfig {


    private static final String KAFKA_SERVER_ADDRESS = "namenode.enisei:6667";
    private static final String AVRO_SERIALIZER_CLASS = "io.confluent.kafka.serializers.KafkaAvroSerializer";
    private static final String SCHEMA_REGISTRY_SERVER_URL = "http://namenode.enisei:8081";

    public Properties properties = new Properties();

    public void  loadProperties() {
        properties.put("bootstrap.servers", KAFKA_SERVER_ADDRESS);
        properties.put("key.serializer", AVRO_SERIALIZER_CLASS);
        properties.put("value.serializer", AVRO_SERIALIZER_CLASS);
        properties.put("schema.registry.url", SCHEMA_REGISTRY_SERVER_URL);
    }

    public Properties getProperties() {
        return properties;
    }
}
