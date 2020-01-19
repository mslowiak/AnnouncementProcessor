package extractor;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@Log
@SpringBootApplication
@EnableScheduling
public class AnnouncementProcessorExtractorApplication {

    public static void main(String[] args) { // TODO add JaCoCo to get tests coverage: it may be already in intelliJ
        SpringApplication.run(AnnouncementProcessorExtractorApplication.class, args);
    }
}
