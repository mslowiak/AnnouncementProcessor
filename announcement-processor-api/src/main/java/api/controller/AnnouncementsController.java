package api.controller;

import api.service.ExtractorDataService;
import api.service.FrontEndDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public void receiveAnnouncement() {

    }

    @GetMapping("/lala")
    public void qubec() {
        System.out.println("lala");
    }
}
