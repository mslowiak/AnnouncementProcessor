package extractor.consumer;

import extractor.JMSConfig;
import extractor.dto.AnnouncementDto;
import extractor.service.MapperService;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.util.Optional;

@Service
@Slf4j
public class AnnouncementConsumerJms implements AnnouncementConsumer, ExceptionListener {

    private JMSConfig jmsConfig;
    private MapperService mapperService;

    public AnnouncementConsumerJms(JMSConfig jmsConfig, MapperService mapperService) {
        this.jmsConfig = jmsConfig;
        this.mapperService = mapperService;
    }

    @Override
    public Optional<AnnouncementDto> consumeAnnouncement() {

        log.info("Running consumeAnnouncement, brokerUrl: {}, queueName: {}",
                jmsConfig.getBrokerAddress(), jmsConfig.getQueueName());
        Optional<AnnouncementDto> optionalAnnouncementDto = Optional.empty();
        try {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(jmsConfig.getBrokerAddress());

            Connection connection = connectionFactory.createConnection();
            connection.start();

            connection.setExceptionListener(this);

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue(jmsConfig.getQueueName());

            MessageConsumer consumer = session.createConsumer(destination);

            Message message = consumer.receive(100);
            log.debug("Received message");

            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                log.info("Received text message");
                log.debug("Text message payload: {}", textMessage.getText());
                optionalAnnouncementDto = Optional.ofNullable(
                        mapperService.getAnnouncementDtoFromJsonString(textMessage.getText())
                );
            }

            consumer.close();
            session.close();
            connection.close();
            log.info("Finished consumeAnnouncement");
            return optionalAnnouncementDto;

        } catch (Exception e) {
            e.printStackTrace();
            return optionalAnnouncementDto;
        }
    }

    @Override
    public void onException(JMSException e) {
        log.error("JMS exception: {}" , e);
    }
}
