package extractor.service;

import extractor.dto.AnnouncementDto;
import extractor.entity.Announcement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@AllArgsConstructor
@Service
public class MapperService {

    private ObjectMapper mapper;

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
