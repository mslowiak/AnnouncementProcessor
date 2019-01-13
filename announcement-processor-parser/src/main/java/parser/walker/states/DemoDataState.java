package parser.walker.states;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import parser.Announcement;
import parser.scrapper.AnnouncementParser;
import parser.walker.helpers.ProviderHelper;

import java.io.IOException;
import java.util.List;

@Slf4j
public class DemoDataState extends WalkerState {

    public DemoDataState(ProviderHelper providerHelper, AnnouncementParser announcementParser) {
        super(providerHelper, announcementParser);
    }

    @Override
    public WalkerState run() {
        String url = providerHelper.getBaseAnnouncementsUrl();
        try {
            Document pageDocument = providerHelper.getPageAsDocumentFromUrl(url);
            List<String> urlsToParse = providerHelper.getUrlsToParse(pageDocument, 10);
            Announcement announcement = announcementParser.parsePage(urlsToParse.get(urlsToParse.size() - 1));
            providerHelper.getParsingInfoService().saveAnnouncementToRegistry("GUMTREE", announcement, 1);
            return new FetchRegistryState(providerHelper, announcementParser);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Unexpected error in DemoState");
            return new StopState(providerHelper);
        }
    }
}
