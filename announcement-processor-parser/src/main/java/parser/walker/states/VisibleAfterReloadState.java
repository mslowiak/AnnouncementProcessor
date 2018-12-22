package parser.walker.states;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import parser.walker.helpers.ProviderHelper;

import java.util.List;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class VisibleAfterReloadState extends WalkerState {

    public VisibleAfterReloadState(ProviderHelper providerHelper) {
        super(providerHelper);
    }

    @Override
    public WalkerState run() {
        List<String> allUrls = providerHelper.getAllUrlsOnPage();
        boolean isStillOnSamePage = allUrls.contains(providerHelper.getLastParsedAnnouncement().getUrl());
        WalkerState state;

        if (isStillOnSamePage) {
            state = new TopElementState(providerHelper);
        } else {
            state = new FetchRegistryState(providerHelper, announcementParser);
        }

        return state;
    }
}
