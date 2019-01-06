package api.controller;

import api.dto.AnnouncementDto;
import api.service.ExtractorDataService;
import api.service.FrontEndDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/announcements")
public class AnnouncementsController {
    private ExtractorDataService extractorDataService;
    private FrontEndDataService frontEndDataService;

    public AnnouncementsController(ExtractorDataService extractorDataService, FrontEndDataService frontEndDataService) {
        this.extractorDataService = extractorDataService;
        this.frontEndDataService = frontEndDataService;
    }

    @PostMapping("/send")
    public ResponseEntity<AnnouncementDto> receiveAnnouncement(@RequestBody AnnouncementDto announcementDto) {
        log.debug("Received receiveAnnouncement POST request with body: {}", announcementDto);
        log.info("Received receiveAnnouncement POST request with announcement URL: {}", announcementDto.getUrl());

        return new ResponseEntity<>(announcementDto, HttpStatus.OK);
    }
}
