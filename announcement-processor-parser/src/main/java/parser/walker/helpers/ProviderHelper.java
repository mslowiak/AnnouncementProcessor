package parser.walker.helpers;

import lombok.Getter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import parser.AnnouncementSender;
import parser.registry.ParsingInfo;
import parser.registry.ParsingInfoService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Getter
@Component
public abstract class ProviderHelper {
    ParsingInfoService parsingInfoService;
    ParsingInfo lastParsedAnnouncement;
    WalkerInfo walkerInfo;
    AnnouncementSender announcementSender;

    public ProviderHelper(ParsingInfoService parsingInfoService) {
        this.parsingInfoService = parsingInfoService;
    }

    Document getPageAsDocumentFromUrl(String url) throws IOException {
        return Jsoup.connect(url).get();
    }

    public abstract Optional<ParsingInfo> fetchLatestRecord();

    public abstract Optional<WalkerInfo> findPageWithAnnouncement(ParsingInfo parsingInfoToFind);

    public abstract List<String> getUrlsToParse(Document document, int divNumber);

    public abstract List<String> getAllUrlsOnPage(boolean refreshPage);

    public abstract void updateLastParsedAnnouncement();

    public abstract void goToNewerPageWithDocumentUpdate();

    abstract int findAnnouncementDivNumberOnPage(Document page, String url);

    abstract Elements getElementsWithDataFromPage(Document page);

    abstract String getPageUrlFromElement(Element element);

    abstract String getNextPageUrl(String previousUrl, int nextPageNumber);

    abstract String getPreviousPageUrl(String actualUrl);

    abstract int getNumberOfTotalPages(Document scannedPage);

    public void setAnnouncementSender(AnnouncementSender announcementSender) {
        this.announcementSender = announcementSender;
    }

    public void setLastParsedAnnouncement(ParsingInfo lastParsedAnnouncement) {
        this.lastParsedAnnouncement = lastParsedAnnouncement;
    }
}
