package parser.walker;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import parser.scrapper.AnnouncementParser;
import parser.scrapper.GumtreeAnnouncementParser;
import parser.walker.states.StopState;
import parser.walker.states.WalkerState;

@Service
public class GumtreePageWalker extends PageWalker {
    private AnnouncementParser announcementParser;
    private WalkerState walkerState;

    public GumtreePageWalker(@Qualifier("fetchRegistryState") WalkerState walkerState,
                             GumtreeAnnouncementParser gumtreeAnnouncementParser) {
        this.walkerState = walkerState;
        this.announcementParser = gumtreeAnnouncementParser;
        walkerState.setAnnouncementParser(gumtreeAnnouncementParser);
    }

    @Override
    public void walk(String startPageUrl) {
        while(walkerState != null && !(walkerState instanceof StopState)){
            walkerState = walkerState.run();
        }
    }
}
