package parser.walker.states;

import lombok.extern.slf4j.Slf4j;
import parser.scrapper.AnnouncementParser;
import parser.walker.helpers.ProviderHelper;

@Slf4j
public class TopElementState extends WalkerState {

    public TopElementState(ProviderHelper providerHelper, AnnouncementParser announcementParser) {
        super(providerHelper, announcementParser);
    }

    @Override
    public WalkerState run() {
        int divNumber = providerHelper.getWalkerInfo().getRequestedAnnouncementDivNumber();

        if (divNumber == 0) {
            log.info("Element is at the top of the page. Go to NewerPageState");
            return new NewerPageState(providerHelper, announcementParser);
        } else {
            log.info("Element is not at the top of the page. Go to ProcessPageState");
            return new ProcessPageState(providerHelper, announcementParser);
        }
    }
}
