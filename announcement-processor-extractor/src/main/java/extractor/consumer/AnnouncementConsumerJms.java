package extractor.consumer;

import extractor.JMSConfig;
import extractor.dto.AnnouncementDto;
import extractor.service.MapperService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

@Slf4j
@AllArgsConstructor
@Primary
@Service
public class AnnouncementConsumerJms implements AnnouncementConsumer, ExceptionListener {

    private JMSConfig jmsConfig;
    private MapperService mapperService;

    @Override
    public AnnouncementDto consumeAnnouncement() { // todo refactor - that long method doesn't look good
        log.info("Running consumeAnnouncement, brokerUrl: {}, queueName: {}", jmsConfig.getBrokerAddress(), jmsConfig.getQueueName());
        try { // todo config creation of factory as singleton
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(jmsConfig.getBrokerAddress());

            Connection connection = connectionFactory.createConnection();
            connection.start();
            connection.setExceptionListener(this);

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(jmsConfig.getQueueName());
            MessageConsumer consumer = session.createConsumer(destination);

            Message message = consumer.receive(100);
            log.debug("Received message");
            AnnouncementDto announcementDto = null;// todo remove null to ;

            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                log.info("Received text message");
                log.debug("Text message payload: {}", textMessage.getText());
                announcementDto = mapperService.getAnnouncementDtoFromJsonString(textMessage.getText());
            }

            consumer.close();
            session.close();
            connection.close();
            log.info("Finished consumeAnnouncement");
            return announcementDto;
        } catch (JMSException e) {
            log.error("Error during jms connection, {}", e.getMessage());
            return null;
        }
    }

    // todo check if can be removed
    @Override
    public void onException(JMSException e) {
        log.error("JMS exception: {}", e.getMessage());
    }
}
