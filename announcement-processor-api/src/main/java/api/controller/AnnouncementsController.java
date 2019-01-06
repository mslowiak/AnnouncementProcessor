package api.controller;

import api.model.DetailedAnnouncementInfo;
import api.model.GeneralAnnouncementInfo;
import api.service.ExtractorDataService;
import api.service.FrontEndDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/announcements")
public class AnnouncementsController {
    private ExtractorDataService extractorDataService;
    private FrontEndDataService frontEndDataService;

    public AnnouncementsController(ExtractorDataService extractorDataService, FrontEndDataService frontEndDataService) {
        this.extractorDataService = extractorDataService;
        this.frontEndDataService = frontEndDataService;
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<GeneralAnnouncementInfo>> getAllAnnouncements(@RequestParam(required = false) String currency) {
        List<GeneralAnnouncementInfo> allAnnouncements = frontEndDataService.getAllAnnouncements(currency);
        if (allAnnouncements != null) {
            return new ResponseEntity<>(allAnnouncements, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("get/{id}/detailed-info")
    public ResponseEntity<DetailedAnnouncementInfo> getDetailedInfoAnnouncement(@PathVariable Integer id, @RequestParam(required = false) String currency) {
        DetailedAnnouncementInfo detailedInfoAnnouncement = frontEndDataService.getDetailedInfoAnnouncement(id, currency);
        if (detailedInfoAnnouncement != null) {
            return new ResponseEntity<>(detailedInfoAnnouncement, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("get/{id}/general-info")
    public ResponseEntity<GeneralAnnouncementInfo> getGeneralInfoAnnouncement(@PathVariable Integer id, @RequestParam(required = false) String currency) {
        GeneralAnnouncementInfo generalInfoAnnouncement = frontEndDataService.getGeneralInfoAnnouncement(id, currency);
        if (generalInfoAnnouncement != null) {
            return new ResponseEntity<>(generalInfoAnnouncement, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
