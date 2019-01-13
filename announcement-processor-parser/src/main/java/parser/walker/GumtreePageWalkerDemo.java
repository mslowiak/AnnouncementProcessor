package parser.walker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import parser.AnnouncementSender;
import parser.scrapper.GumtreeAnnouncementParser;
import parser.walker.helpers.GumtreeHelper;
import parser.walker.states.DemoDataState;
import parser.walker.states.StopState;
import parser.walker.states.WalkerState;

@Slf4j
@Service
@Profile({"demo"})
public class GumtreePageWalkerDemo extends PageWalker {
    private WalkerState startState;

    public GumtreePageWalkerDemo(@Qualifier("gumtreeHelper") GumtreeHelper gumtreeHelper,
                                 GumtreeAnnouncementParser gumtreeAnnouncementParser,
                                 AnnouncementSender announcementSender) {
        gumtreeHelper.setAnnouncementSender(announcementSender);
        this.startState = new DemoDataState(gumtreeHelper, gumtreeAnnouncementParser);
    }

    @Override
    @Scheduled(fixedDelay = 300000)
    public void walk() {
        log.info("");
        log.info("==============> Scheduled task started walker <==================================");
        WalkerState walkerState = startState;
        walkerState.getProviderHelper().setLastParsedAnnouncement(null);
        while (walkerState != null && !(walkerState instanceof StopState)) {
            walkerState = walkerState.run();
        }
        log.info("==============> GumtreePageWalker has parsed all pages <========================");
    }
}
