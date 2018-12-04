package parser.walker.states;

import parser.registry.ParsingInfo;
import parser.walker.helpers.ProviderHelper;

import java.util.HashMap;
import java.util.Optional;

public class FindUrlState extends WalkerState {

    private ParsingInfo parsingInfoToFind;

    FindUrlState(ProviderHelper providerHelper, ParsingInfo parsingInfoToFind) {
        super(providerHelper);
        this.parsingInfoToFind = parsingInfoToFind;
    }

    @Override
    public WalkerState run() {
        Optional<HashMap<String, Object>> pageWithAnnouncement = providerHelper.findPageWithAnnouncement(parsingInfoToFind);

        return pageWithAnnouncement
                .<WalkerState>map(stringObjectHashMap -> new ProcessPageState(providerHelper, stringObjectHashMap))
                .orElseGet(() -> new FetchRegistryState(providerHelper));
    }
}
