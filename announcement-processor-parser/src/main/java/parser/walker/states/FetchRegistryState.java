package parser.walker.states;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import parser.walker.helpers.ProviderHelper;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FetchRegistryState extends WalkerState {

    public FetchRegistryState(ProviderHelper providerHelper) {
        super(providerHelper);
    }

    @Override
    public WalkerState run() {
        return null;
    }
}
