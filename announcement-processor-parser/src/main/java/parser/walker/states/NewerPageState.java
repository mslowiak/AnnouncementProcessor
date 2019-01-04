package parser.walker.states;

import lombok.extern.slf4j.Slf4j;
import parser.scrapper.AnnouncementParser;
import parser.walker.helpers.ProviderHelper;

@Slf4j
public class NewerPageState extends WalkerState {

    NewerPageState(ProviderHelper providerHelper, AnnouncementParser announcementParser) {
        super(providerHelper, announcementParser);
    }

    @Override
    public WalkerState run() {
        int walkPageUrlNumber = providerHelper.getWalkerInfo().getWalkPageUrlNumber();

        if (walkPageUrlNumber == 1) {
            log.info("It is newest page, go to StopState");
            return new StopState(providerHelper);
        }

        providerHelper.goToNewerPageWithDocumentUpdate();
        log.info("It is not a newest page, go to ProcessPageState");
        return new ProcessPageState(providerHelper, announcementParser);
    }
}
