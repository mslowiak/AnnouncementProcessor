package parser.walker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import parser.AnnouncementSender;
import parser.scrapper.GumtreeAnnouncementParser;
import parser.walker.helpers.GumtreeHelper;
import parser.walker.states.FetchRegistryState;
import parser.walker.states.StopState;
import parser.walker.states.WalkerState;

@Slf4j
@Service
public class GumtreePageWalker extends PageWalker {
    private WalkerState startState;

    public GumtreePageWalker(@Qualifier("gumtreeHelper") GumtreeHelper gumtreeHelper,
                             GumtreeAnnouncementParser gumtreeAnnouncementParser,
                             AnnouncementSender announcementSender) {
        gumtreeHelper.setAnnouncementSender(announcementSender);
        this.startState = new FetchRegistryState(gumtreeHelper, gumtreeAnnouncementParser);
    }

    @Override
    @Scheduled(fixedDelay = 300000)
    public void walk() {
        log.info("");
        log.info("==============> Scheduled task started walker <==================================");
        WalkerState walkerState = startState;
        walkerState.getProviderHelper().setLastParsedAnnouncement(null);
        while(walkerState != null && !(walkerState instanceof StopState)){
            walkerState = walkerState.run();
        }
        log.info("==============> GumtreePageWalker has parsed all pages <========================");
    }
}
