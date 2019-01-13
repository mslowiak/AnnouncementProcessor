package extractor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JMSConfig {

    @Value("${broker.os}")
    private String brokerOs;
    @Value("${broker.ip}")
    private String brokerIp;
    @Value("${broker.port}")
    private String brokerPort;
    @Value("${broker.queue}")
    private String brokerQueueName;

    public String getBrokerAddress() {
        if (isWindows()) {
            return "tcp://" + brokerIp + ":" + brokerPort;
        }
        return "tcp://localhost:" + brokerPort;
    }

    public String getQueueName() {
        return brokerQueueName;
    }

    private boolean isWindows() {
        return brokerOs.equals("windows");
    }
}