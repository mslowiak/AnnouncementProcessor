package extractor;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@Log
@SpringBootApplication
@EnableScheduling
public class AnnouncementProcessorExtractorApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnnouncementProcessorExtractorApplication.class, args);
    }
}
