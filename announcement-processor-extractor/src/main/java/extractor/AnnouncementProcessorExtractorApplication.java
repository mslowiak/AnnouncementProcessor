package extractor;


import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@Log
@SpringBootApplication
@EnableScheduling
public class AnnouncementProcessorExtractorApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AnnouncementProcessorExtractorApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
