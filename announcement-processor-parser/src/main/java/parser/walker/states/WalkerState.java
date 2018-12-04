package parser.walker.states;

import org.springframework.stereotype.Component;
import parser.scrapper.AnnouncementParser;
import parser.scrapper.GumtreeAnnouncementParser;
import parser.walker.helpers.ProviderHelper;

@Component
public abstract class WalkerState {
    ProviderHelper providerHelper;
    AnnouncementParser announcementParser;

    public WalkerState(ProviderHelper providerHelper) {
        this.providerHelper = providerHelper;
    }

    public abstract WalkerState run();

    public void setAnnouncementParser(GumtreeAnnouncementParser gumtreeAnnouncementParser){
        this.announcementParser = gumtreeAnnouncementParser;
    };
}
