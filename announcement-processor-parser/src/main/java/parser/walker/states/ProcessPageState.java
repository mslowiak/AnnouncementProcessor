package parser.walker.states;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import parser.Announcement;
import parser.scrapper.AnnouncementParser;
import parser.walker.helpers.ProviderHelper;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ProcessPageState extends WalkerState {
    private HashMap<String, Object> pageWithAnnouncement;
    private final int THREAD_POOL_SIZE = 10;

    public ProcessPageState(ProviderHelper providerHelper, AnnouncementParser announcementParser, HashMap<String, Object> pageWithAnnouncement) {
        super(providerHelper, announcementParser);
        this.pageWithAnnouncement = pageWithAnnouncement;
    }

    @Override
    public WalkerState run() {
        Document pageDocument = (Document) pageWithAnnouncement.get("pageDocument");
        int divNumber = (int) pageWithAnnouncement.get("divNumber");
        List<String> urlsToParse = providerHelper.getUrlsToParse(pageDocument, divNumber);
        Integer counterPerDate = providerHelper.getLastParsedAnnouncement().getCounterPerDate();

        log.info("Number of pages to parse: " + urlsToParse.size());

        try {
            ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
            for (int i = 0; i < urlsToParse.size(); ++i) {
                String url = urlsToParse.get(i);
                int finalI = i;
                executorService.submit(() -> {
                    int counterPerPageForParsed = counterPerDate + urlsToParse.size() - finalI;
                    Announcement announcement = announcementParser.parsePage(url);
                    if (announcement != null) {
                        providerHelper.getParsingInfoService().saveAnnouncementToRegistry("GUMTREE", announcement, counterPerPageForParsed);
                        // TODO: Send announcement via JMS
                    }
                });
            }
            executorService.shutdown();
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            log.error("Interrupted exception");
        }
        log.info("Parsing urls from page " + providerHelper.getActualPageURLNumber() + " done. Go to VisibleAfterReloadState");

        return new VisibleAfterReloadState(providerHelper);
    }
}
