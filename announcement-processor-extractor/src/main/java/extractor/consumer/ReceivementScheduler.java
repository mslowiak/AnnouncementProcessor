package extractor.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@EnableScheduling
public class ReceivementScheduler {

    @Scheduled(cron= "0 */2 * * * *")
    private void scheduleTask() {
        log.info("Executing ReceivementScheduler scheduledTask");
        
    }
}
