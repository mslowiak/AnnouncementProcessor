package parser.walker.states;

import lombok.extern.slf4j.Slf4j;
import parser.registry.ParsingInfo;
import parser.scrapper.AnnouncementParser;
import parser.walker.helpers.ProviderHelper;
import parser.walker.helpers.WalkerInfo;

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
        Optional<WalkerInfo> walkerInfo = providerHelper.findPageWithAnnouncement(parsingInfoToFind);

        if (walkerInfo.isPresent()) {
            log.info("Page has been found on page number: " + providerHelper.getWalkerInfo().getWalkPageUrlNumber() + ". Go to ProcessPageState");
        } else {
            log.info("Page not found. Go to FetchRegistryState");
        }

        return walkerInfo
                .<WalkerState>map(walker -> new ProcessPageState(providerHelper, announcementParser))
                .orElseGet(() -> new FetchRegistryState(providerHelper, announcementParser));
    }
}
