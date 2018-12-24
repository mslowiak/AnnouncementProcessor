package parser.walker.states;

import lombok.extern.slf4j.Slf4j;
import parser.scrapper.AnnouncementParser;
import parser.walker.helpers.ProviderHelper;

import java.util.List;

@Slf4j
public class VisibleAfterReloadState extends WalkerState {

    public VisibleAfterReloadState(ProviderHelper providerHelper, AnnouncementParser announcementParser) {
        super(providerHelper, announcementParser);
    }

    @Override
    public WalkerState run() {
        int divIndex = getDivIndexIfInList();
        WalkerState state;

        if (divIndex != -1) {
            log.info("Url is visible on page. Go to TopElementState");
            providerHelper.getWalkerInfo().setRequestedAnnouncementDivNumber(divIndex);
            state = new TopElementState(providerHelper, announcementParser);
        } else {
            log.info("Url is not visible on page. Go to FetchRegistryState");
            state = new FetchRegistryState(providerHelper, announcementParser);
        }

        return state;
    }

    private int getDivIndexIfInList() {
        String desiredUrl = providerHelper.getLastParsedAnnouncement().getUrl();
        List<String> allUrlsOnPage = providerHelper.getAllUrlsOnPage();
        for (int i = 0; i < allUrlsOnPage.size(); i++) {
            if (allUrlsOnPage.get(i).equals(desiredUrl)) {
                return i;
            }
        }
        return -1;
    }
}
