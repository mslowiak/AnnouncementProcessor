package extractor.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import extractor.entity.Announcement;
import io.specto.hoverfly.junit.rule.HoverflyRule;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;
import static io.specto.hoverfly.junit.core.SimulationSource.dsl;
import static io.specto.hoverfly.junit.dsl.HoverflyDsl.service;

public class AnnouncementSenderServiceTest {

//    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
//    private final MapperService mapperService = new MapperService(objectMapper);
//    private final AnnouncementSenderService announcementSenderService = new AnnouncementSenderService(mapperService);
//
//    @ClassRule
//    public static HoverflyRule hoverflyRule = HoverflyRule.inSimulationMode(dsl(
//            service("http://localhost:8080")
//                    .post("/announcements/send")
//                    .willReturn(success("{response: \"response\"}", "application/json"))
//    ));
//
//    @Test
//    public void shouldBeAbleToGetABookingUsingHoverfly() {
//        Announcement announcement = Announcement.builder()
//                .title("Test title")
//                .build();
//
//        boolean finishedMethod = announcementSenderService.send(announcement);
//
//        Assert.assertTrue(finishedMethod);
//    }
}
