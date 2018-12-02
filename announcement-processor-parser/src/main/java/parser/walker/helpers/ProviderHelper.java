package parser.walker.helpers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;
import parser.registry.ParsingInfo;
import parser.registry.ParsingInfoService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

@Component
public abstract class ProviderHelper {
    ParsingInfoService parsingInfoService;
    ParsingInfo lastParsedAnnouncement;

    public ProviderHelper(ParsingInfoService parsingInfoService) {
        this.parsingInfoService = parsingInfoService;
    }

    Document getPageAsDocumentFromUrl(String url) throws IOException {
        return Jsoup.connect(url).get();
    }

    public abstract Optional<ParsingInfo> fetchLatestRecord();

    public abstract Optional<HashMap<String, Object>> findPageWithAnnouncement(ParsingInfo parsingInfoToFind);

    abstract HashMap<String, Object> findAnnouncementUrlOnPage(Document page, String url);

    abstract String getPageUrlFromElement(Element element);

    abstract String getNextPageUrl(String previousUrl, int nextPageNumber);

    abstract int getNumberOfTotalPages(Document scannedPage);
}
