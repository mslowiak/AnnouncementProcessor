package parser.walker.helpers;

import org.springframework.stereotype.Component;
import parser.registry.ParsingInfoService;

@Component
public class GumtreeHelper extends ProviderHelper {

    public GumtreeHelper(ParsingInfoService parsingInfoService) {
        super(parsingInfoService);
    }

}
