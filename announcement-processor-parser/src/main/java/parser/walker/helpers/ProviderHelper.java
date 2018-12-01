package parser.walker.helpers;

import org.springframework.stereotype.Component;
import parser.registry.ParsingInfo;
import parser.registry.ParsingInfoService;

import java.util.Optional;

@Component
public abstract class ProviderHelper {
    ParsingInfoService parsingInfoService;
    ParsingInfo lastParsedAnnouncement;

    public ProviderHelper(ParsingInfoService parsingInfoService) {
        this.parsingInfoService = parsingInfoService;
    }

    public abstract Optional<ParsingInfo> fetchLatestRecord();

}
