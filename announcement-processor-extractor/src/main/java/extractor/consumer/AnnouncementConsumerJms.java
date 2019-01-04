package extractor.consumer;

import extractor.dto.AnnouncementDto;
import extractor.service.MapperService;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class AnnouncementConsumerJms implements AnnouncementConsumer, ExceptionListener {

    private MapperService mapperService;

    public AnnouncementConsumerJms() {
        this.mapperService = new MapperService();
    }

    @Override
    public AnnouncementDto consumeAnnouncement() {

        try {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");

            Connection connection = connectionFactory.createConnection();
            connection.start();

            connection.setExceptionListener(this);

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue("ANNOUNCEMENTS");

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
