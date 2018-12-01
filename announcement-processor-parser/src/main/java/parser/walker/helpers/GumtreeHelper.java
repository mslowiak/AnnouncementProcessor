package parser.walker.helpers;

import org.springframework.stereotype.Component;
import parser.registry.ParsingInfo;
import parser.registry.ParsingInfoService;

import java.util.Optional;

@Component
public class GumtreeHelper extends ProviderHelper {

    public GumtreeHelper(ParsingInfoService parsingInfoService) {
        super(parsingInfoService);
    }

    @Override
    public Optional<ParsingInfo> fetchLatestRecord() {
        Optional<ParsingInfo> info = parsingInfoService.fetchLastRecordForProvider("GUMTREE", lastParsedAnnouncement);
        lastParsedAnnouncement = info.orElse(null);
        return info;
    }
}
