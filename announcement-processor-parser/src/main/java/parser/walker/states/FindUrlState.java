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

        if (pageWithAnnouncement.isPresent()) {
            return new ProcessPageState(providerHelper);
        } else {
            return new FetchRegistryState(providerHelper);
        }
    }
}
