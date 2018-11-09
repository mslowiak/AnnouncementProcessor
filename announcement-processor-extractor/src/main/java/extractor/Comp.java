package extractor;

import lombok.extern.java.Log;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
@Log
public class Comp {
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.info("The time is now: " + LocalDateTime.now().toString());
    }
}