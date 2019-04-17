package api.controller;

import api.dto.AnnouncementDto;
import api.model.DetailedAnnouncementInfo;
import api.model.GeneralAnnouncementInfo;
import api.service.ExtractorDataService;
import api.service.FrontEndDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        extractorDataService.convertAndSave(announcementDto);
        log.info("Extracted and saved to DB; announcement URL: {}", announcementDto.getUrl());
        return new ResponseEntity<>(announcementDto, HttpStatus.OK);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<GeneralAnnouncementInfo>> getAllAnnouncements(@RequestParam(required = false) String currency) {
        log.info("ENDPOINT /announcements/get/all was called");
        List<GeneralAnnouncementInfo> allAnnouncements = frontEndDataService.getAllAnnouncements();
        if (allAnnouncements != null) {
            log.info("ENDPOINT /announcements/get/all - STATUS OK");
            return new ResponseEntity<>(allAnnouncements, HttpStatus.OK);
        } else {
            log.info("ENDPOINT /announcements/get/all - STATUS NO CONTENT");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("get/{id}/detailed-info")
    public ResponseEntity<DetailedAnnouncementInfo> getDetailedInfoAnnouncement(@PathVariable Integer id, @RequestParam(required = false) String currency) {
        log.info("ENDPOINT /announcements/get/" + id + "/detailed-info was called");
        DetailedAnnouncementInfo detailedInfoAnnouncement = frontEndDataService.getDetailedInfoAnnouncement(id, currency);
        if (detailedInfoAnnouncement != null) {
            log.info("ENDPOINT /announcements/get/" + id + "/detailed-info - STATUS OK");
            return new ResponseEntity<>(detailedInfoAnnouncement, HttpStatus.OK);
        } else {
            log.info("ENDPOINT /announcements/get/" + id + "/detailed-info - STATUS NO CONTENT");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("get/{id}/general-info")
    public ResponseEntity<GeneralAnnouncementInfo> getGeneralInfoAnnouncement(@PathVariable Integer id, @RequestParam(required = false) String currency) {
        log.info("ENDPOINT /announcements/get/" + id + "/general-info was called");
        GeneralAnnouncementInfo generalInfoAnnouncement = frontEndDataService.getGeneralInfoAnnouncement(id, currency);
        if (generalInfoAnnouncement != null) {
            log.info("ENDPOINT /announcements/get/" + id + "/general-info - STATUS OK");
            return new ResponseEntity<>(generalInfoAnnouncement, HttpStatus.OK);
        } else {
            log.info("ENDPOINT /announcements/get/" + id + "/general-info - STATUS NO CONTENT");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
