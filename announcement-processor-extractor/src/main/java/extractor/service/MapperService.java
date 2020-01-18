package extractor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import extractor.dto.AnnouncementDto;
import extractor.entity.Announcement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class MapperService {

    private ObjectMapper mapper;

    public MapperService() {
        this.mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());
    }

    public AnnouncementDto getAnnouncementDtoFromJsonString(String json) {
        try {
            return mapper.readValue(json, AnnouncementDto.class);
        } catch (IOException e) {
            log.error("Failed to serialize json payload to AnnouncementDto object");
            return null;
        }
    }

    String getJsonFromAnnouncement(Announcement announcement) throws JsonProcessingException {
        return mapper.writeValueAsString(announcement);
    }
}
