package extractor.consumer;

import extractor.dto.AnnouncementDto;
import extractor.entity.Announcement;
import extractor.service.AnnouncementExtractingService;
import extractor.service.AnnouncementSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@EnableScheduling
public class ReceivementScheduler {

    private AnnouncementConsumerJms consumer;
    private AnnouncementExtractingService extractor;
    private AnnouncementSenderService announcementSender;

    public ReceivementScheduler(AnnouncementConsumerJms consumer,
                                AnnouncementExtractingService extractor,
                                AnnouncementSenderService announcementSender) {
        this.consumer = consumer;
        this.extractor = extractor;
        this.announcementSender = announcementSender;
    }

    @Scheduled(cron = "*/10 * * * * *")
    private void scheduleTask() {

        log.info("Executing ReceivementScheduler scheduledTask");
        AnnouncementDto announcementDto = consumer.consumeAnnouncement();
        log.debug("Received announcementDto: {}", announcementDto);
        Announcement announcement = extractor.extractFromAnnouncementDto(announcementDto);
        log.debug("Converted announcementDto to announcement: {}", announcement);
        announcementSender.send(announcement);
        log.info("Sent announcement to API");
    }
}
