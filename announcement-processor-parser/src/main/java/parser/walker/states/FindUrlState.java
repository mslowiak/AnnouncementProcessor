package parser.walker.states;

import lombok.extern.slf4j.Slf4j;
import parser.registry.ParsingInfo;
import parser.scrapper.AnnouncementParser;
import parser.walker.helpers.ProviderHelper;

import java.util.HashMap;
import java.util.Optional;

@Slf4j
public class FindUrlState extends WalkerState {

    private ParsingInfo parsingInfoToFind;

    FindUrlState(ProviderHelper providerHelper, AnnouncementParser announcementParser, ParsingInfo parsingInfoToFind) {
        super(providerHelper, announcementParser);
        this.parsingInfoToFind = parsingInfoToFind;
    }

    @Override
    public WalkerState run() {
        Optional<HashMap<String, Object>> pageWithAnnouncement = providerHelper.findPageWithAnnouncement(parsingInfoToFind);

        if (pageWithAnnouncement.isPresent()) {
            log.info("Page has been found on page number: " + providerHelper.getActualPageURLNumber() + ". Go to ProcessPageState");
        } else {
            log.info("Page not found. Go to FetchRegistryState");
        }

        return pageWithAnnouncement
                .<WalkerState>map(stringObjectHashMap -> new ProcessPageState(providerHelper, announcementParser, stringObjectHashMap))
                .orElseGet(() -> new FetchRegistryState(providerHelper, announcementParser));
    }
}
