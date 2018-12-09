package parser.walker.states;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import parser.registry.ParsingInfo;
import parser.scrapper.AnnouncementParser;
import parser.walker.helpers.ProviderHelper;

import java.util.Optional;

@Slf4j
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FetchRegistryState extends WalkerState {

    public FetchRegistryState(ProviderHelper providerHelper, AnnouncementParser announcementParser) {
        super(providerHelper, announcementParser);
    }

    @Override
    public WalkerState run() {
        Optional<ParsingInfo> parsingInfoToFind = providerHelper.fetchLatestRecord();

        if (parsingInfoToFind.isPresent()) {
            log.info("Url fetched: " + parsingInfoToFind.get().getUrl());
        } else {
            log.info("Url cannot be fetched.");
        }

        return parsingInfoToFind
                .map(parsingInfo -> new FindUrlState(providerHelper, announcementParser, parsingInfo))
                .orElseGet(() -> new FindUrlState(providerHelper, announcementParser, null));
    }
}