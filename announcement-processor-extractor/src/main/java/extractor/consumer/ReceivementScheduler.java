package extractor.consumer;

import extractor.dto.AnnouncementDto;
import extractor.entity.Announcement;
import extractor.service.AnnouncementExtractingService;
import extractor.service.AnnouncementSenderService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
@EnableScheduling
public class ReceivementScheduler { // todo to implement MessageListener; not scheduling it would be nice

    private AnnouncementConsumer consumer; // todo interface here that is injected
    private AnnouncementExtractingService extractor;
    private AnnouncementSenderService announcementSender;

    @Scheduled(cron = "*/5 * * * * *") // todo config this
    private void scheduleTask() {
        log.info("Executing ReceivementScheduler scheduledTask");
        Optional<AnnouncementDto> announcementDto = Optional.ofNullable(consumer.consumeAnnouncement());
        announcementDto.ifPresent(annDto -> {
            log.debug("Received announcementDto: {}", announcementDto);
            Announcement announcement = extractor.extractFromAnnouncementDto(annDto);
            log.debug("Converted announcementDto to announcement: {}", announcement);
            announcementSender.send(announcement);
        });
    }
}
