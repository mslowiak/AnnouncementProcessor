package parser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import parser.walker.PageWalker;

@EnableJpaRepositories
@SpringBootApplication
public class AnnouncementProcessorParserApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(AnnouncementProcessorParserApplication.class, args);
        PageWalker pageWalker = (PageWalker) run.getBean("gumtreePageWalker");
        pageWalker.walk("www.example.com");
    }
}
