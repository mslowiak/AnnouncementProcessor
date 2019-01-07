package extractor.service;

import extractor.consumer.AnnouncementConsumer;
import extractor.consumer.AnnouncementConsumerFromString;
import extractor.dto.AnnouncementDto;
import extractor.entity.Announcement;
import org.junit.Test;

public class AnnouncementExtractingServiceTest {

    private final AnnouncementConsumer announcementConsumer = new AnnouncementConsumerFromString();
    private final AnnouncementExtractingService extractingService = new AnnouncementExtractingService();

    @Test
    public void extractFromAnnouncementDtoTest() {
        AnnouncementDto announcementDto = announcementConsumer.consumeAnnouncement();
        Announcement announcement = extractingService.extractFromAnnouncementDto(announcementDto);
        System.out.println(announcement);
    }
}
