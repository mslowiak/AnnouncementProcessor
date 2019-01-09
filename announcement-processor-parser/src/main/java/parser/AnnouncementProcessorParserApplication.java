package parser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJpaRepositories
@SpringBootApplication
@EnableScheduling
public class AnnouncementProcessorParserApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(AnnouncementProcessorParserApplication.class, args);
    }
}
