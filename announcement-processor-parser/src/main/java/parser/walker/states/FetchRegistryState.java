package parser.walker.states;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import parser.registry.ParsingInfo;
import parser.walker.helpers.ProviderHelper;

import java.util.Optional;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FetchRegistryState extends WalkerState {

    public FetchRegistryState(ProviderHelper providerHelper) {
        super(providerHelper);
    }

    @Override
    public WalkerState run() {
        Optional<ParsingInfo> parsingInfoToFind = providerHelper.fetchLatestRecord();
        return parsingInfoToFind
                .map(parsingInfo -> new FindUrlState(providerHelper, parsingInfo))
                .orElseGet(() -> new FindUrlState(providerHelper, null));
    }
}