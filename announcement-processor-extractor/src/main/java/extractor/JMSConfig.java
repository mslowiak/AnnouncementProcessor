package extractor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JMSConfig {
    @Value("${broker.ip}")
    private String brokerIp;
    @Value("${broker.port}")
    private String brokerPort;
    @Value("${queue}")
    private String queueName;

    public String getBrokerAddress() {
        String property = System.getProperty("os.name").toLowerCase();
        if (isWindows(property)) {
            return "tcp://" + brokerIp + ":" + brokerPort;
        }
        return "tcp://localhost:" + brokerPort;
    }

    public String getQueueName() {
        return queueName;
    }

    private boolean isWindows(String sys) {
        return sys.contains("win");
    }
}
