package extractor.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import extractor.consumer.AnnouncementConsumer;
import extractor.consumer.AnnouncementConsumerFromString;
import extractor.dto.AnnouncementDto;
import extractor.entity.Announcement;
import org.junit.Assert;
import org.junit.Test;

public class AnnouncementExtractingServiceTest {

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    private final MapperService mapperService = new MapperService(objectMapper);
    private final AnnouncementConsumer announcementConsumer = new AnnouncementConsumerFromString(mapperService);
    private final AnnouncementExtractingService extractingService = new AnnouncementExtractingService();

    @Test
    public void extractFromAnnouncementDtoTest() {
        AnnouncementDto announcementDto = AnnouncementValue.ANNOUNCEMENT_DTO;

        Announcement announcement = extractingService.extractFromAnnouncementDto(announcementDto);

        Assert.assertEquals(announcementDto.getTitle(), announcement.getTitle());
        Assert.assertEquals(announcementDto.getDescription(), announcement.getDescription());
        Assert.assertEquals(announcementDto.getImages(), announcement.getImages());
        Assert.assertEquals(announcementDto.getDescription(), announcement.getDescription());
        Assert.assertEquals(announcementDto.getLessor(), announcement.getLessor().getLessorType());
        Assert.assertEquals(announcementDto.getCreationDate(), announcement.getCreationDate());
        Assert.assertEquals(announcementDto.getUrl(), announcement.getUrl());
        Assert.assertEquals(announcementDto.getProvider(), announcement.getProvider());
    }
}
