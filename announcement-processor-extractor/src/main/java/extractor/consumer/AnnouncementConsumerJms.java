package extractor.consumer;

import extractor.dto.AnnouncementDto;
import extractor.service.MapperService;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.jms.*;

@Service
@Slf4j
public class AnnouncementConsumerJms implements AnnouncementConsumer, ExceptionListener {

    @Value("${brokerURL}")
    private String brokerUrl;
    @Value("${queue}")
    private String queueName;
    private MapperService mapperService;

    AnnouncementConsumerJms() {
        this.mapperService = new MapperService();
    }

    @Override
    public AnnouncementDto consumeAnnouncement() {

        log.info("Running consumeAnnouncement, brokerUrl: {}, queueName: {}", brokerUrl, queueName);
        try {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);

            Connection connection = connectionFactory.createConnection();
            connection.start();

            connection.setExceptionListener(this);

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue(queueName);

            MessageConsumer consumer = session.createConsumer(destination);

            Message message = consumer.receive(100);
            log.debug("Received message");
            AnnouncementDto announcementDto = null;

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

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void onException(JMSException e) {
        log.error("JMS exception: {}" , e);
    }
}
