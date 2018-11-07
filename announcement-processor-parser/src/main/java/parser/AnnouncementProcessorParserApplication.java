package parser;

import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Log
@SpringBootApplication
public class AnnouncementProcessorParserApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AnnouncementProcessorParserApplication.class);
        app.run(args);
    }

    @Override
    public void run(String... args) {
    }
}
