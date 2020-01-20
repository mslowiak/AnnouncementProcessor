package extractor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import extractor.dto.AnnouncementDto;
import extractor.entity.Announcement;
import org.junit.Assert;
import org.junit.Test;

public class MapperServiceTest {

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    private final MapperService mapperService = new MapperService(objectMapper);
    private final AnnouncementExtractingService extractingService = new AnnouncementExtractingService();

    @Test
    public void getAnnouncementDtoFromJsonStringTest() {
        AnnouncementDto announcementDtoFromJsonString = mapperService
                .getAnnouncementDtoFromJsonString(AnnouncementValue.ANNOUNCEMENTDTO_JSON);

        Assert.assertEquals(AnnouncementValue.ANNOUNCEMENT_DTO, announcementDtoFromJsonString);
    }

    @Test
    public void getJsonFromAnnouncementTest() throws JsonProcessingException {
        Announcement announcement = extractingService.extractFromAnnouncementDto(AnnouncementValue.ANNOUNCEMENT_DTO);

        String jsonFromAnnouncement = mapperService.getJsonFromAnnouncement(announcement);

        Assert.assertEquals(AnnouncementValue.ANNOUNCEMENT_JSON, jsonFromAnnouncement);
    }

}
