package parser.walker;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import parser.walker.states.StopState;
import parser.walker.states.WalkerState;

@Service
public class GumtreePageWalker extends PageWalker {

    private WalkerState walkerState;

    public GumtreePageWalker(@Qualifier("fetchRegistryState") WalkerState walkerState) {
        this.walkerState = walkerState;
    }

    @Override
    public void walk(String startPageUrl) {
        while(walkerState != null && !(walkerState instanceof StopState)){
            walkerState = walkerState.run();
        }
    }
}
