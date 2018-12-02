package extractor.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import extractor.dto.AnnouncementDto;
import org.springframework.stereotype.Service;

import java.io.IOException;

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
            e.printStackTrace();
            return null;
        }
    }

}
