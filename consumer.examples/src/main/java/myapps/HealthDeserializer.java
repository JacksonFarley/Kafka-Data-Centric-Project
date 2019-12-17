package myapps; 

import myapps.HealthMetadata;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HealthDeserializer implements Deserializer<HealthMetadata> {

    @Override
    public HealthMetadata deserialize(final String topic, final byte[] data) {
        return new HealthMetadata(new String(data, StandardCharsets.UTF_8));
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public void close() {
    }
}