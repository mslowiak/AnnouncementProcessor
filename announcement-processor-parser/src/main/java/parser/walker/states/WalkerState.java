package parser.walker.states;

import org.springframework.stereotype.Component;
import parser.scrapper.AnnouncementParser;
import parser.walker.helpers.ProviderHelper;

@Component
public abstract class WalkerState {
    ProviderHelper providerHelper;
    AnnouncementParser announcementParser;

    public WalkerState(ProviderHelper providerHelper) {
        this.providerHelper = providerHelper;
    }

    public WalkerState(ProviderHelper providerHelper, AnnouncementParser announcementParser) {
        this.providerHelper = providerHelper;
        this.announcementParser = announcementParser;
    }

    public abstract WalkerState run();
}
