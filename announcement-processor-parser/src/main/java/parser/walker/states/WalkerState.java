package parser.walker.states;

import org.springframework.stereotype.Component;
import parser.walker.helpers.ProviderHelper;

@Component
public abstract class WalkerState {
    ProviderHelper providerHelper;

    public WalkerState(ProviderHelper providerHelper) {
        this.providerHelper = providerHelper;
    }

    public abstract WalkerState run();
}
