package parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
@Getter
public class AnnouncementSender {
    private JMSConfig jmsConfig;

    public AnnouncementSender(JMSConfig jmsConfig) {
        this.jmsConfig = jmsConfig;
    }

    public void sendAnnouncement(Announcement announcement) {
        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(jmsConfig.getBrokerAddress());
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue(jmsConfig.getQueueName());

            MessageProducer messageProducer = session.createProducer(queue);

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            Message message = session.createTextMessage(objectMapper.writeValueAsString(announcement));
            messageProducer.send(message);

            connection.close();
            session.close();
        } catch (JMSException | JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
