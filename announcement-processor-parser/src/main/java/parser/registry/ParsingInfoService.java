package parser.registry;

import org.springframework.stereotype.Service;

@Service
public class ParsingInfoService {

    private ParsingInfoRepository parsingInfoRepository;

    public ParsingInfoService(ParsingInfoRepository parsingInfoRepository) {
        this.parsingInfoRepository = parsingInfoRepository;
    }

}
