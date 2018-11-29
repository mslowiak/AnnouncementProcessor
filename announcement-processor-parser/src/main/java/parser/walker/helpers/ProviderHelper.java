package parser.walker.helpers;

import org.springframework.stereotype.Component;
import parser.registry.ParsingInfoService;

@Component
public abstract class ProviderHelper {
    ParsingInfoService parsingInfoService;

    public ProviderHelper(ParsingInfoService parsingInfoService) {
        this.parsingInfoService = parsingInfoService;
    }

}
