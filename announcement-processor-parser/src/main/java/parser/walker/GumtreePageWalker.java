package parser.walker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import parser.scrapper.GumtreeAnnouncementParser;
import parser.walker.helpers.GumtreeHelper;
import parser.walker.states.FetchRegistryState;
import parser.walker.states.StopState;
import parser.walker.states.WalkerState;

@Slf4j
@Service
public class GumtreePageWalker extends PageWalker {
    private WalkerState walkerState;

    public GumtreePageWalker(@Qualifier("gumtreeHelper") GumtreeHelper gumtreeHelper,
                             GumtreeAnnouncementParser gumtreeAnnouncementParser) {
        this.walkerState = new FetchRegistryState(gumtreeHelper, gumtreeAnnouncementParser);
    }

    @Override
    public void walk(String startPageUrl) {
        while(walkerState != null && !(walkerState instanceof StopState)){
            walkerState = walkerState.run();
        }
        log.info("GumtreePageWalker has parsed all pages");
    }
}
