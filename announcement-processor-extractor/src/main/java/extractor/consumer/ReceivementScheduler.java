package extractor.consumer;

import extractor.dto.AnnouncementDto;
import extractor.entity.Announcement;
import extractor.service.AnnouncementExtractingService;
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

    public ReceivementScheduler(AnnouncementConsumerJms consumer, AnnouncementExtractingService extractor) {
        this.consumer = consumer;
        this.extractor = extractor;
    }

    @Scheduled(cron = "*/10 * * * * *")
    private void scheduleTask() {

        log.info("Executing ReceivementScheduler scheduledTask");
        AnnouncementDto announcementDto = consumer.consumeAnnouncement();
        Announcement announcement = extractor.extractFromAnnouncementDto(announcementDto);
        // TODO save to the DB
    }
}
