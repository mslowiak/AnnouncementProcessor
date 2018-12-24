package parser.walker.states;

import parser.scrapper.AnnouncementParser;
import parser.walker.helpers.ProviderHelper;

public class NewerPageState extends WalkerState {

    public NewerPageState(ProviderHelper providerHelper, AnnouncementParser announcementParser) {
        super(providerHelper, announcementParser);
    }

    @Override
    public WalkerState run() {
        return null;
    }
}
