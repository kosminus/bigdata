import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConsumerConfig {
    public static Properties loadProperties(String filename) {
        try {
            final Properties kafkaProp = new Properties();
            final FileInputStream input = new FileInputStream(filename);
            kafkaProp.load(input);

            return kafkaProp;
        } catch (Exception e) {
            e.printStackTrace();

        }

        return null;
    }
}

