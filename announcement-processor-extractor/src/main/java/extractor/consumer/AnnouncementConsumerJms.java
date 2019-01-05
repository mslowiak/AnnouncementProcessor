package extractor.consumer;

import extractor.dto.AnnouncementDto;
import extractor.service.MapperService;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.jms.*;

@Service
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
        System.out.println(brokerUrl);
        System.out.println(queueName);
        try {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);

            Connection connection = connectionFactory.createConnection();
            connection.start();

            connection.setExceptionListener(this);

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue(queueName);

            MessageConsumer consumer = session.createConsumer(destination);

            Message message = consumer.receive(100);

            AnnouncementDto announcementDto = null;

            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                // TODO check if message can be a list of announcements
                announcementDto = mapperService.getAnnouncementDtoFromJsonString(textMessage.getText());
            }

            consumer.close();
            session.close();
            connection.close();
            return announcementDto;

        } catch (Exception e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void onException(JMSException e) {

        System.out.println("JMS exception: " + e);
    }
}
